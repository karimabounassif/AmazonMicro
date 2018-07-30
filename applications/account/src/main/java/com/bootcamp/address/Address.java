package com.bootcamp.address;

import com.bootcamp.account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer accountId;
    private String street;
    private String apt;
    private String city;
    private String state;
    private String zip;
    private String country;
    @ManyToOne
    @JsonBackReference
    private Account account;
}
