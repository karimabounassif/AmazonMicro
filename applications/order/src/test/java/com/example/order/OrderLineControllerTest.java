package com.example.order;


import com.example.Order.Order;
import com.example.Order.OrderService;
import com.example.OrderLine.OrderLine;
import com.example.OrderLine.OrderLineController;
import com.example.OrderLine.OrderLineService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderLineControllerTest {

    @Autowired
    private OrderLineController orderLineController;
    @Autowired
    private OrderService orderService;

    private Order order = new Order();
    OrderLine orderLine = new OrderLine();

    @Before
    public void setup(){

        orderService.addOrder(order);
        orderLine.setTotalPrice(150.0);
        orderLine.setQuantity(5.0);
        orderLine.setProductId(1);
        orderLine.setPrice(5.0);
        orderLine.setShipmentId(3);
        orderLineController.addOrderLine(orderLine, order.getOrderId());
    }

    @Test
    public void testGetOrderLine(){
        Assert.assertEquals(orderLineController.getOrderLine(orderLine.getId()).getStatusCode(),
                HttpStatus.OK);
    }

    @Test
    public void testGetOrderId(){
        Assert.assertNotNull(orderLineController.getOrderId(orderLine.getId()));
    }

    @Test
    public void testAddOrderLine(){
        Assert.assertEquals(orderLineController.addOrderLine(orderLine, order.getOrderId()).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testUpdateOrderLine(){
        Assert.assertEquals(orderLineController.getOrderLine(orderLine.getId()).getBody().getShipmentId()
                , orderLine.getShipmentId());
        orderLine.setShipmentId(1);
        orderLineController.updateOrderLine(orderLine, orderLine.getId());
        Assert.assertEquals(orderLineController.getOrderLine(orderLine.getId()).getBody().getShipmentId()
                , orderLine.getShipmentId());
    }

    @Test
    public void testDeleteOrderLine() {
        orderLineController.addOrderLine(orderLine, order.getOrderId());
        Assert.assertEquals(orderLineController.deleteOrderLine(orderLine.getId()).getStatusCode()
                , HttpStatus.OK);
    }
}
