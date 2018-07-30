package com.bootcamp.shipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private List<Integer> orderLineId;
    private Date shippedDate;
    private Date deliveryDate;
}
