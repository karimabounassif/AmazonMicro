package com.bootcamp.account;

import com.bootcamp.address.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        test = accountController.addAccount(new Account(1, "Karim", "Nassif", addresses));
    }

    @Test
    public void testAddAccount(){
        assertEquals(test.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testReadAccount(){
        assertNotNull(accountController.getByName("Nassif"));
    }

    @Test
    public void testGetAll(){
        assertNotNull(accountController.getAll());
    }

    @Test
    public void testUpdateAccount(){
        assertNotNull(accountController.updateAccount(new Account(1, "New", "Account", addresses)));
    }

    @Test
    public void testDeleteAccount(){
        assertEquals(accountController.deleteAccount(new Account(1, "", "", addresses)).getStatusCode(), HttpStatus.OK);
    }


}