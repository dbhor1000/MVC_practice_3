package MVC_OnJC.MVC_practice_3.service;

import MVC_OnJC.MVC_practice_3.DTO.OrderDTO;
import MVC_OnJC.MVC_practice_3.model.OrderEntity;

public interface OrderService {

    public OrderEntity registerNewOrder(OrderDTO newOrder);
    public OrderEntity retrieveOrderInformationById(Long orderId);

}
