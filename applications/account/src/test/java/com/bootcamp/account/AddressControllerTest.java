package com.bootcamp;

import com.bootcamp.account.Account;
import com.bootcamp.account.AccountController;
import com.bootcamp.account.AccountRepo;
import com.bootcamp.account.AccountService;
import com.bootcamp.address.Address;
import com.bootcamp.address.AddressController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class AddressControllerTest {

    @Autowired
    AddressController addressController;
    @Autowired
    AccountController accountController;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    AccountService accountService;

    List<Address> addressList = new ArrayList<>();
    Account account = new Account(1, "Karim","Nassif", addressList);
    Address address = new Address();

    @Before
    public void setup(){
        accountController.addAccount(account);
        address.setStreet("Test");
        addressController.addAddress(address, 1);
    }

    @Test
    public void testGetAddressByAccount(){

        Assert.assertEquals(addressController.getAddressByAccount(address.getAccount()
                        .getId()).getBody().get(0).getStreet()
                        , address.getStreet());
    }

    @Test
    public void testGetAddressById(){
        Assert.assertEquals(addressController.getAddressById(address.getId()).getBody().getStreet(),
                address.getStreet());
    }

    @Test
    public void testAddAddress(){
        Assert.assertEquals(addressController.addAddress(address, 1).getStatusCode(),
                HttpStatus.CREATED);
    }

    @Test
    public void testUpdateAddress(){
        Address newAddress = new Address();
        addressController.addAddress(newAddress, 1);
        Assert.assertEquals(addressController.getAddressById(newAddress.getId()).getBody().getStreet(), null);
        newAddress.setStreet("NewTest");
        newAddress.setApt("1");
        newAddress.setCity("Caracas");
        newAddress.setState("lol");
        newAddress.setCountry("lmao");
        addressController.updateAddress(newAddress, newAddress.getId());
        Assert.assertEquals(addressController.getAddressById(newAddress.getId()).getBody().getStreet(), "NewTest");
    }

    @Test
    public void testDeleteAddress(){
        Address newAddress = new Address();
        addressController.addAddress(newAddress, 1);
        Assert.assertEquals(addressController.deleteAddress(newAddress.getId()).getStatusCode()
        , HttpStatus.OK);
    }

}
