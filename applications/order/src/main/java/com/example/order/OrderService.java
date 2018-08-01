package com.example.Order;

import com.example.OrderLine.OrderLine;
import com.example.UtilObjects.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private RestTemplate restTemplate;

    public OrderService(OrderRepo orderRepo, RestTemplate restTemplate) {this.orderRepo = orderRepo; this.restTemplate = restTemplate;}

    public ResponseEntity<Order> addOrder(Order order){
        orderRepo.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderRepo.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Order> getById(Integer id){
        Order order = orderRepo.findById(id).get();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    public ResponseEntity<List<Order>> getOrdersId(Integer id){
        List<Order> orders = orderRepo.findAllByAccountIdOrderByOrderDateDesc(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<OrderDetails> getOrderDetails(Integer id){

        Order order = getById(id).getBody();

        //Get Shipments from the orderLines and convert them into displayShipments.
        List<Shipment> shipments = new ArrayList<>();
        List<displayShipment> displayShipments;
        for(OrderLine ol : order.getOrderLines()){
            Shipment shipment = restTemplate.getForObject("http://Shipment/shipment/" + ol.getShipmentId(), Shipment.class);
            shipments.add(shipment);
        }
        displayShipments = convert(shipments);

        //Get Orderlines and convert them into displayLines.
        List<displayLines> displayLines = new ArrayList<>();
        List<OrderLine> orderLines = order.getOrderLines();
        for(OrderLine ol:orderLines){
            String productName = restTemplate.getForObject("http://Product/product/" + ol.getProductId() + "/name", String.class);
            displayLines.add(new displayLines(productName, ol.getQuantity()));
        }

        //Set all Orderdetails fields.
        Address address = restTemplate.getForObject("http://Account/account/" + order.getAccountId() + "/address/" + order.getAddressId(), Address.class);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderNumber(order.getOrderId());
        orderDetails.setShippingAddress(address);
        orderDetails.setTotalPrice(order.getTotalPrice());
        orderDetails.setOrderLines(displayLines);
        orderDetails.setShipments(displayShipments);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    public ResponseEntity<Order> updateOrder(Order order, Integer id){

        Order current = getById(id).getBody();
        current.setAccountId(order.getAccountId());
        current.setAddressId(order.getAddressId());
        current.setOrderDate(order.getOrderDate());
        current.setOrderLines(order.getOrderLines());
        current.setTotalPrice(order.getTotalPrice());
        orderRepo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }



    public ResponseEntity<String> deleteOrder(Integer id){
        orderRepo.delete(getById(id).getBody());
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    public List<displayShipment> convert(List<Shipment> shipments){
        List<displayShipment> displayShipments = new ArrayList<>();
        for(Shipment s:shipments){
            displayShipments.add(new displayShipment(s.getOrderLineId(), s.getShippedDate(), s.getDeliveryDate()));
        }
        return displayShipments;
    }
}
