package com.example.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable(name="orderId") Integer id){
        return orderService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll(){
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable(name="orderId") Integer orderId){
        return orderService.updateOrder(order, orderId);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name="orderId") Integer id){
        return orderService.deleteOrder(id);
    }
}
