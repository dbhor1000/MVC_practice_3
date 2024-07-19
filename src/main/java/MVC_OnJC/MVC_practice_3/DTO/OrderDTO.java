package MVC_OnJC.MVC_practice_3.DTO;

import java.time.LocalDate;
import java.util.Map;

public class OrderDTO {

    private Map<String, Integer> products;
    private String shippingAddress;
    private String customerEmail;

    public OrderDTO(String customerEmail, Map<String, Integer> products, String shippingAddress) {
        this.customerEmail = customerEmail;
        this.products = products;
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
