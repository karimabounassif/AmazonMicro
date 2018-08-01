package com.example.Order;

import com.example.OrderLine.OrderLine;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer accountId;
    private Date orderDate;
    private Integer addressId;
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderLine> orderLines;
    private Double totalPrice;

}
