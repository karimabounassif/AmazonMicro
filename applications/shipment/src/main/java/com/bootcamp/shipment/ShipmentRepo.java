package com.bootcamp.shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepo extends JpaRepository<Shipment, Integer> {

    List<Shipment> findAllByOrderId(Integer id);
}
