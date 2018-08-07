package com.bootcamp.UtilObjects;

import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.List;

@Data
public class AccountShipment {

    private Integer orderNumber;
    private Date shipmentDate;
    private Date deliveryDate;
    private DisplayOrderLine orderLines;

}
