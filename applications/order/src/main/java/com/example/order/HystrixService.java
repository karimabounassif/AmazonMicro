package com.example.Order;

import com.example.UtilObjects.Address;
import com.example.UtilObjects.Shipment;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Component
public class HystrixService {

    RestOperations restTemplate;

    public HystrixService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getShipmentFallback")
    public Shipment getShipment(Integer shipmentId){
        return restTemplate.getForObject("http://Shipment/shipment/" + shipmentId, Shipment.class);
    }

    public Shipment getShipmentFallback(Integer shipmentId){
        Shipment fallback = new Shipment();
        fallback.setId(shipmentId);
        return fallback;
    }

    @HystrixCommand(fallbackMethod = "getProductNameFallback")
    public String getProductName(Integer productId){
        return restTemplate.getForObject("http://Product/product/" + productId + "/name", String.class);
    }

    public String getProductNameFallback(Integer productId){
        return productId.toString();
    }

    @HystrixCommand(fallbackMethod = "getAddressFallback")
    public Address getAddress(Integer accountId, Integer addressId) {
        return restTemplate.getForObject("http://Account/account/" + accountId + "/address/" + addressId, Address.class);
    }

    public Address getAddressFallback(Integer accountId, Integer addressId) {
        Address fallback = new Address();
        fallback.setStreet(addressId.toString());
        return fallback;
    }
}
