package MVC_OnJC.MVC_practice_3.controller;

import MVC_OnJC.MVC_practice_3.DTO.OrderDTO;
import MVC_OnJC.MVC_practice_3.model.OrderEntity;
import MVC_OnJC.MVC_practice_3.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> fetchOneOrder(@PathVariable Long id) {

        OrderEntity orderFound = orderService.retrieveOrderInformationById(id);
        if(orderFound == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderFound);
    }

    @PostMapping("")
    public ResponseEntity<OrderEntity> addOrder(@RequestBody OrderDTO orderDTO) {

        OrderEntity order = orderService.registerNewOrder(orderDTO);

        if(order == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(order);
    }

}
