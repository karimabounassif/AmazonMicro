package com.example.UtilObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer accountId;
    private Integer addressId;
    private Integer orderLineId;
    private Date shippedDate;
    private Integer orderId;
    private Date deliveryDate;
}
