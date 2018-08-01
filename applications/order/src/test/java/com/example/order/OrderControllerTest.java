//package com.example.order;
//
//import com.example.Order.Order;
//import com.example.Order.OrderController;
//import com.example.Order.OrderService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//
//public class OrderControllerTest {
//
//    @Autowired
//    OrderController orderController;
//
//    @Before
//    public void setup(){
//        Order order = new Order();
//        order.setAddressId(1);
//        order.setAccountId(1);
//        order.setTotalPrice(150.0);
//        order.setOrderDate(null);
//        order.setOrderLines(null);
//        orderController.addOrder(order);
//    }
//
//    @Test
//    public void testGetById(){
//        Order newOrder = new Order();
//        orderController.addOrder(newOrder);
//        Assert.assertEquals(orderController.getById(newOrder.getOrderId()).getBody().getOrderId(),
//        newOrder.getOrderId());
//    }
//
//    @Test
//    public void testGetAll(){
//        Assert.assertTrue(!orderController.getAll().getBody().isEmpty());
//    }
//
//}
