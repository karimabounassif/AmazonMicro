package com.bootcamp.shipment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentRepo shipmentRepo;

    public ShipmentService(ShipmentRepo shipmentRepo) {
        this.shipmentRepo = shipmentRepo;
    }

    public ResponseEntity<Shipment> addShipment(Shipment shipment) {
        shipmentRepo.save(shipment);
        return new ResponseEntity<>(shipment, HttpStatus.CREATED);
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
        shipmentRepo.delete(getShipment(id).getBody());
        return new ResponseEntity<>("Deleted.", HttpStatus.NO_CONTENT);
    }
}
