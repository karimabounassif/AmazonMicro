package com.example.UtilObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetails {

    private Integer orderNumber;
    private Double totalPrice;
    private Address shippingAddress;
    @ElementCollection
    private List<displayLines> orderLines;
    @ElementCollection
    private List<displayShipment> shipments;

}
