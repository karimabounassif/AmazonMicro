package com.example.UtilObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Address> addresses;

    public Account(Integer id, String firstName, String lastName, List<Address> addresses){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

}
