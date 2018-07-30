package com.bootcamp.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }

    public ResponseEntity<String> addAccount(Account account) {
        accountRepo.save(account);
        return new ResponseEntity<>("Saved.", HttpStatus.CREATED);
    }

    public ResponseEntity<Account> getById(Integer id) {
        Account account = accountRepo.findById(id).get();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    public ResponseEntity<List<Account>> getByName(String lastName) {
        List<Account> accounts = accountRepo.findAccountByLastName(lastName);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    public ResponseEntity<List<Account>> getAll() {
        List<Account> accounts = accountRepo.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    public ResponseEntity<Account> updateAccount(Account account, Integer accountId){
        Account current = getById(accountId).getBody();
        current.setFirstName(account.getFirstName());
        current.setLastName(account.getLastName());
        current.setAddresses(account.getAddresses());
        accountRepo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAccount(Account account){
        accountRepo.deleteById(account.getId());
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
