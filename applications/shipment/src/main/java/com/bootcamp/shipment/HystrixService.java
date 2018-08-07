package com.bootcamp.shipment;

import com.bootcamp.UtilObjects.OrderLine;
import com.bootcamp.UtilObjects.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Component
public class HystrixService {

    private RestOperations restTemplate;

    public HystrixService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getOrderIdFallback")
    public Integer getOrderId(String lineId){
        return restTemplate.getForObject("http://Order/orders/1/lines/orderId/" + lineId, Integer.class);
    }

    public Integer getOrderIdFallback(String lineId, Throwable t){
        return null;
    }

    @HystrixCommand(fallbackMethod = "getOrderLineFallBack")
    public OrderLine getOrderLine(Shipment s) {
        return restTemplate.getForObject("http://Order/orders/" + s.getOrderId() + "/lines/" + s.getOrderLineId(), OrderLine.class);
    }

    public OrderLine getOrderLineFallBack(Shipment s){
        return new OrderLine();
    }

    @HystrixCommand(fallbackMethod = "getProductFallback")
    public Product getProduct(Integer productId){
        return restTemplate.getForObject("http://Product/product/" + productId, Product.class);
    }

    public Product getProductFallback(Integer productId){
        Product fallback = new Product();
        if(productId!=null) {
            fallback.setName(productId.toString());
        }
        return fallback;
    }
}
