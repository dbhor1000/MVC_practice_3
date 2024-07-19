package MVC_OnJC.MVC_practice_3.service;

import MVC_OnJC.MVC_practice_3.DTO.OrderDTO;
import MVC_OnJC.MVC_practice_3.DTO.ProductNameAndQuantityDTO;
import MVC_OnJC.MVC_practice_3.model.OrderEntity;
import MVC_OnJC.MVC_practice_3.model.OrderStatus;
import MVC_OnJC.MVC_practice_3.model.ProductEntity;
import MVC_OnJC.MVC_practice_3.repository.CustomerRepository;
import MVC_OnJC.MVC_practice_3.repository.OrderRepository;
import MVC_OnJC.MVC_practice_3.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderEntity registerNewOrder(OrderDTO newOrder) {

        //Весь зал встаёт и апплодирует стоя.

        if(customerRepository.getReferenceByEmail(newOrder.getCustomerEmail()) == null) {
            return null;
        }

        List<ProductEntity> products = productRepository.findAll();
        Map<String, Integer> productLeftInStock = products.stream()
                .collect(Collectors.toMap(
                        ProductEntity::getProductName,
                        ProductEntity::getQuantityInStock
                ));

        Map<String, BigDecimal> productPrices = products.stream()
                .collect(Collectors.toMap(
                        ProductEntity::getProductName,
                        ProductEntity::getPrice
                ));

        Map<String, Integer> productsOrdered = newOrder.getProducts();

        for (Map.Entry<String, Integer> entry : productsOrdered.entrySet()) {
            String key = entry.getKey();
            Integer value2 = entry.getValue();

            if (!productLeftInStock.containsKey(key)) {
                return null;
            }

            Integer value1 = productLeftInStock.get(key);
            if (value1 <= value2) {
                return null;
            }
        }

        for (Map.Entry<String, Integer> entry : productsOrdered.entrySet()) {
            String productName = entry.getKey();
            Integer quantityOrdered = entry.getValue();

            ProductEntity product = productRepository.getReferenceByProductName(productName);
            if (product != null) {
                int newQuantityInStock = product.getQuantityInStock() - quantityOrdered;
                if (newQuantityInStock < 0) {
                    return null;
                }
                product.setQuantityInStock(newQuantityInStock);
                productRepository.save(product);
            } else {
                return null;
            }
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (Map.Entry<String, Integer> entry : productsOrdered.entrySet()) {
            String productName = entry.getKey();
            Integer quantity = entry.getValue();
            BigDecimal price = productPrices.get(productName);
            BigDecimal cost = price.multiply(BigDecimal.valueOf(quantity));
            totalCost = totalCost.add(cost);
        }

        OrderEntity modifiableEntity = new OrderEntity(customerRepository.getReferenceByEmail(newOrder.getCustomerEmail()
        ), LocalDate.now(), 0, OrderStatus.REGISTERED, newOrder.getProducts(), newOrder.getShippingAddress(),
                totalCost);

        orderRepository.save(modifiableEntity);
        return modifiableEntity;
    }

    public OrderEntity retrieveOrderInformationById(Long orderId) {

        return orderRepository.getReferenceByOrderId(orderId);
    }
}
