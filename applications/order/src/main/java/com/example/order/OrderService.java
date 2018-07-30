package com.example.Order;

import com.example.OrderLine.OrderLine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {this.orderRepo = orderRepo;}

    public ResponseEntity<Order> addOrder(Order order){
        orderRepo.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderRepo.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Order> getById(Integer id){
        Order order = orderRepo.findById(id).get();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public ResponseEntity<Order> updateOrder(Order order, Integer id){
        Order current = getById(id).getBody();
        current.setAccountId(order.getAccountId());
        current.setAddressId(order.getAddressId());
        current.setOrderDate(order.getOrderDate());
        current.setOrderLines(order.getOrderLines());
        current.setTotalPrice(order.getTotalPrice());
        orderRepo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteOrder(Integer id){
        orderRepo.delete(getById(id).getBody());
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

}
