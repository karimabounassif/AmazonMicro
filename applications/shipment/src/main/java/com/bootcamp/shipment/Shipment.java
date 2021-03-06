package com.bootcamp.shipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
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
