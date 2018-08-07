package com.bootcamp.address;


import com.bootcamp.account.Account;
import com.bootcamp.account.AccountRepo;
import com.bootcamp.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/account/{id}/address")
public class AddressController {

    private final AddressRepo addressRepo;
    private final AccountService accountService;


    public AddressController(AddressRepo addressRepo, AccountService accountService) { this.addressRepo = addressRepo; this.accountService = accountService;}

    @GetMapping
    public ResponseEntity<List<Address>> getAddressByAccount(@PathVariable(name="id") Integer id){
        List<Address> addressList = addressRepo.findAllByAccountId(id);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/{address_id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(name="address_id") Integer address_id){
        Address address = addressRepo.findById(address_id).get();
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addAddress(@RequestBody Address address, @PathVariable(name="id") Integer id){
        Account account = accountService.getById(id).getBody();
        address.setAccount(account);
        addressRepo.save(address);
        return new ResponseEntity<>(address.getId().toString(), HttpStatus.CREATED);
    }

    @PutMapping("/{address_id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable(name="address_id") Integer address_id){
        Address current = getAddressById(address_id).getBody();
        current.setStreet(address.getStreet());
        current.setApt(address.getApt());
        current.setCity(address.getCity());
        current.setState(address.getState());
        current.setZip(address.getZip());
        current.setCountry(address.getCountry());
        addressRepo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @DeleteMapping("/{address_id}")
    public ResponseEntity<String> deleteAddress(@PathVariable(name="address_id") Integer id){
        addressRepo.delete(getAddressById(id).getBody());
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
