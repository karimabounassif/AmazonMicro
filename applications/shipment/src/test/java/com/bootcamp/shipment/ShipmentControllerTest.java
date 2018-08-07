package com.bootcamp.shipment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipmentControllerTest {

    @Autowired
    ShipmentController shipmentController;

    Shipment shipment = new Shipment();

    @Before
    public void setup() throws ParseException {
        shipment.setOrderId(1);
        shipment.setShippedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-22"));
        shipment.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-22"));
        shipment.setAddressId(1);
        shipment.setOrderLineId(1);
        shipment.setAccountId(1);
        Assert.assertEquals(shipmentController.addShipment(shipment).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testGetId(){
        Assert.assertEquals(shipmentController.getShipment(shipment.getId()).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetAll(){
        List<Shipment> shipmentList = shipmentController.getAll().getBody();
        Assert.assertNotNull(shipmentList);
    }

    @Test
    public void testUpdateShipment(){
        Shipment newShipment = new Shipment();
        newShipment.setOrderLineId(1);
        shipmentController.addShipment(newShipment);
        Assert.assertEquals(shipmentController.getShipment(newShipment.getId()).getBody().getOrderLineId(),
                newShipment.getOrderLineId());
        newShipment.setOrderLineId(2);
        shipmentController.addShipment(newShipment);
        Assert.assertEquals(shipmentController.getShipment(newShipment.getId()).getBody().getOrderLineId(),
                newShipment.getOrderLineId());
    }

    @Test
    public void test(){
        Assert.assertNotNull(shipmentController.getByAccount(shipment.getAccountId()));
    }

    @Test
    public void testDeleteShipment(){
        Shipment newShipment = new Shipment();
        newShipment.setOrderLineId(1);
        shipmentController.addShipment(newShipment);
        Assert.assertEquals(shipmentController.deleteShipment(newShipment.getId()).getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
