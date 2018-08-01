package com.example.OrderLine;

import com.example.Order.Order;
import com.example.Order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders/{id}/lines")
public class OrderLineService {

    private final OrderLineRepo orderLineRepo;
    private final OrderService orderService;

    private RestTemplate restTemplate = new RestTemplate();

    public OrderLineService(OrderLineRepo orderLineRepo, OrderService orderService){ this.orderLineRepo = orderLineRepo; this.orderService = orderService; }

    public ResponseEntity<OrderLine> getOrderLine(Integer orderLineId){
        OrderLine orderLine = orderLineRepo.findById(orderLineId).get();
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    public Integer getOrderId(Integer orderLineId){
        OrderLine orderLine = orderLineRepo.findById(orderLineId).get();
        return orderLine.getOrder().getOrderId();
    }

    public ResponseEntity<String> addOrderLine(OrderLine orderLine, Integer id){
        Order order = orderService.getById(id).getBody();
        orderLine.setOrder(order);
        orderLine.setTotalPrice(orderLine.getPrice() * orderLine.getQuantity());
        Double total;
        if(order.getTotalPrice() != null) {
            total = order.getTotalPrice();
        } else {
            total = 0.0;
        }
        total += orderLine.getTotalPrice();
        order.setTotalPrice(total);
        orderService.updateOrder(order, order.getOrderId());
        orderLineRepo.save(orderLine);
        return new ResponseEntity<>("Saved.", HttpStatus.CREATED);
    }

    public ResponseEntity<OrderLine> updateOrderLine(OrderLine orderLine, Integer orderLineId){
        OrderLine current = getOrderLine(orderLineId).getBody();
        current.setPrice(orderLine.getPrice());
        current.setProductId(orderLine.getProductId());
        current.setQuantity(orderLine.getQuantity());
        current.setTotalPrice(orderLine.getTotalPrice());
        current.setShipmentId(orderLine.getShipmentId());
        orderLineRepo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteOrderLine(Integer orderLineId){
        orderLineRepo.delete(getOrderLine(orderLineId).getBody());
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
