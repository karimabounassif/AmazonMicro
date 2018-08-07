package com.bootcamp.shipment;

import com.bootcamp.UtilObjects.AccountShipment;
import com.bootcamp.UtilObjects.DisplayOrderLine;
import com.bootcamp.UtilObjects.OrderLine;
import com.bootcamp.UtilObjects.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShipmentService {

    private final ShipmentRepo shipmentRepo;

    RestTemplate restTemplate;

    HystrixService hystrixService;

    public ShipmentService(ShipmentRepo shipmentRepo, RestTemplate restTemplate, HystrixService hystrixService) {
        this.shipmentRepo = shipmentRepo;
        this.restTemplate = restTemplate;
        this.hystrixService = hystrixService;
    }

    public ResponseEntity<Shipment> addShipment(Shipment shipment) {
        String lineId = shipment.getOrderLineId().toString();
        Integer orderId = hystrixService.getOrderId(lineId);
        shipment.setOrderId(orderId);
        shipmentRepo.save(shipment);
        return new ResponseEntity<>(shipment, HttpStatus.CREATED);
    }

    public ResponseEntity<List<AccountShipment>> accountShipment(Integer id){
        List<Shipment> shipments = shipmentRepo.findAllByAccountId(id);
        List<AccountShipment> displayShipments = new ArrayList<>();
        for (Shipment s : shipments) {
            OrderLine orderLine = hystrixService.getOrderLine(s);
            DisplayOrderLine dol = new DisplayOrderLine();
            Product productName = hystrixService.getProduct(orderLine.getProductId());
            dol.setProductName(productName.getName());
            dol.setQuantity(orderLine.getQuantity());
            AccountShipment as = new AccountShipment();
            as.setOrderNumber(s.getOrderId());
            as.setShipmentDate(s.getShippedDate());
            as.setDeliveryDate(s.getDeliveryDate());
            as.setOrderLines(dol);
            displayShipments.add(as);
        }

        return new ResponseEntity<>(displayShipments, HttpStatus.OK);
    }

    public ResponseEntity<Shipment> getShipment(Integer id) {
        return new ResponseEntity<>(shipmentRepo.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Shipment>> getAll(){
        return new ResponseEntity<>(shipmentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Shipment> updateShipment(Shipment shipment, Integer id){
        Shipment current = getShipment(id).getBody();
        current.setAccountId(shipment.getAccountId());
        current.setAddressId(shipment.getAddressId());
        current.setDeliveryDate(shipment.getDeliveryDate());
        current.setShippedDate(shipment.getShippedDate());
        current.setOrderLineId(shipment.getOrderLineId());
        shipmentRepo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteShipment(Integer id){
        Shipment shipment = getShipment(id).getBody();
        if(shipment != null) {
            shipmentRepo.delete(shipment);
        }
        return new ResponseEntity<>("Deleted.", HttpStatus.NO_CONTENT);
    }
}
