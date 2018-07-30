package com.bootcamp.shipment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {this.shipmentService = shipmentService;}

    @PostMapping
    public ResponseEntity<Shipment> addShipment(@RequestBody Shipment shipment){
        return shipmentService.addShipment(shipment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipment(@PathVariable(name="id") Integer id){
        return shipmentService.getShipment(id);
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAll(){
        return shipmentService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@RequestBody Shipment shipment, @PathVariable(name="id") Integer id){
        return shipmentService.updateShipment(shipment, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable(name="id") Integer id){
        return shipmentService.deleteShipment(id);
    }
}
