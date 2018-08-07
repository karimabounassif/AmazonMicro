package com.example.order;

import com.example.Order.Order;
import com.example.Order.OrderController;
import com.example.Order.OrderService;
import com.example.OrderLine.OrderLine;
import org.apache.http.protocol.HTTP;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest

public class OrderControllerTest {

    @Autowired
    OrderController orderController;
    Order order = new Order();

    @Before
    public void setup(){
        order.setAddressId(1);
        order.setAccountId(1);
        order.setTotalPrice(150.0);
        order.setOrderDate(null);
        OrderLine orderLine = new OrderLine();
        orderLine.setShipmentId(1);
        orderLine.setPrice(15.0);
        orderLine.setProductId(1);
        orderLine.setQuantity(2.0);
        orderLine.setOrder(order);
        orderLine.setTotalPrice(150.0);
        List<OrderLine> orderLineList = new ArrayList();
        orderLineList.add(orderLine);
        order.setOrderLines(orderLineList);
        orderController.addOrder(order);
    }

    @Test
    public void testGetById(){
        Order newOrder = new Order();
        orderController.addOrder(newOrder);
        Assert.assertNotNull(orderController.getById(newOrder.getOrderId()));
    }

    @Test
    public void testGetAccountOrder(){
        Assert.assertNotNull(orderController.getOrderDetails(order.getOrderId()));
    }

    @Test
    public void testGetOrderDetails(){
        Assert.assertNotNull(orderController.getOrderDetails(order.getOrderId()));
    }

    @Test
    public void testByAccount(){
        Assert.assertEquals(orderController.getByAccount(1).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testAddOrder(){
        Order newOrder = new Order();
        Assert.assertEquals(orderController.addOrder(newOrder).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testUpdateOrder(){
        Order newOrder = new Order();
        newOrder.setTotalPrice(50.0);
        orderController.addOrder(newOrder);
        Assert.assertEquals(orderController.getById(newOrder.getOrderId()).getBody()
                .getTotalPrice(), newOrder.getTotalPrice());
        newOrder.setTotalPrice(25.0);
        orderController.updateOrder(newOrder, newOrder.getOrderId());
        Assert.assertEquals(orderController.getById(newOrder.getOrderId()).getBody().getTotalPrice(),
                newOrder.getTotalPrice());
    }

    @Test
    public void testDeleteOrder(){
        Order newOrder = new Order();
        orderController.addOrder(newOrder);
        Assert.assertNotNull(orderController.getById(newOrder.getOrderId()));
        Assert.assertEquals(orderController.deleteOrder(newOrder.getOrderId()).getStatusCode(),
                HttpStatus.OK);
    }



}
