package com.example.OrderLine;

import com.example.Order.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer productId;
    private Double quantity;
    private Double price;
    private Double totalPrice;
    private Double shipmentId;
    @ManyToOne
    @JsonBackReference
    private Order order;

    public OrderLine(Order order, Integer productId, Double quantity, Double price, Double shipmentId){
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = quantity * price;
        this.shipmentId = shipmentId;
    }
    public OrderLine(Integer productId, Double quantity, Double price, Double shipmentId){
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = quantity * price;
        this.shipmentId = shipmentId;
    }

}
