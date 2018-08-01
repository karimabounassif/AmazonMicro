package com.example.OrderLine;

import com.example.Order.Order;
import com.example.Order.OrderService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/{id}/lines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineRepo orderLineRepo, OrderService orderService, OrderLineService orderLineService){ this.orderLineService = orderLineService;}

    @GetMapping("/{orderLineId}")
    public ResponseEntity<OrderLine> getOrderLine(@PathVariable(name="orderLineId") Integer orderLineId){
        return orderLineService.getOrderLine(orderLineId);
    }

    @GetMapping("/orderId/{orderLineId}")
    public Integer getOrderId(@PathVariable(name="orderLineId") Integer orderLineId){
        return orderLineService.getOrderId(orderLineId);
    }

    @PostMapping
    public ResponseEntity<String> addOrderLine(@RequestBody OrderLine orderLine, @PathVariable(name="id") Integer id){
        return orderLineService.addOrderLine(orderLine, id);
    }

    @PutMapping("/{orderLineId}")
    public ResponseEntity<OrderLine> updateOrderLine(@RequestBody OrderLine orderLine, @PathVariable(name="orderLineId") Integer orderLineId){
        return orderLineService.updateOrderLine(orderLine, orderLineId);
    }

    @DeleteMapping("/{orderLineId}")
    public ResponseEntity<String> deleteOrderLine(@PathVariable(name="orderLineId") Integer orderLineId){
        return orderLineService.deleteOrderLine(orderLineId);
    }
}
