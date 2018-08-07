package com.bootcamp.account;

import com.bootcamp.address.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    AccountController accountController;
    @Autowired
    AccountRepo accountRepo;

    List<Address> addresses = new ArrayList<>();

    ResponseEntity<String> test;




    @Before
    public void setup() throws IOException{
        Account account = new Account();
        account.setFirstName("Karim");
        account.setLastName("Nassif");
        account.setAddresses(addresses);
        accountController.addAccount(account);
    }

    @Test
    public void testAddAccount(){
        Account testAdd = new Account();
        testAdd.setFirstName("Test");
        testAdd.setLastName("Account");
        testAdd.setAddresses(addresses);
        assertEquals(accountController.addAccount(testAdd).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testReadAccount(){
        assertNotNull(accountController.getByName("Nassif"));
    }

    @Test
    public void testGetById() { assertNotNull(accountController.getById(1));}

    @Test
    public void testGetAll(){
        assertNotNull(accountController.getAll());
    }

    @Test
    public void testUpdateAccount(){
        Account newAccount = new Account();
        newAccount.setFirstName("new");
        newAccount.setLastName("last");
        newAccount.setAddresses(addresses);
        assertNotNull(accountController.updateAccount(newAccount, 1));
    }

    @Test
    public void testDeleteAccount(){
        Account test = new Account();
        test.setFirstName("Test");
        accountController.addAccount(test);
        assertEquals(accountController.deleteAccount(test.getId()).getStatusCode(), HttpStatus.OK);
    }


}
