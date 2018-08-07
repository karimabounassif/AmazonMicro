package com.bootcamp.UtilObjects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer productId;
    private Double quantity;
    private Double price;
    private Double totalPrice;
    private Integer shipmentId;

}
