package com.example.UtilObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class displayLines {

    private String productName;
    private Double quantity;

    public displayLines(String productName, Double quantity){
        this.productName = productName;
        this.quantity =quantity;
    }
}
