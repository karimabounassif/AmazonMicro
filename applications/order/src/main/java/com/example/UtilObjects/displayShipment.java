package com.example.UtilObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class displayShipment {

    private Integer lineIds;
    private Date shippedDate;
    private Date deliveryDate;

    public displayShipment(Integer lineIds, Date shippedDate, Date deliveryDate){
        this.lineIds = lineIds;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
    }
}
