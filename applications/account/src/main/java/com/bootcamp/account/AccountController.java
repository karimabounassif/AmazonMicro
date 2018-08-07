package com.bootcamp.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable(name="id") Integer id) {
        return accountService.getById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Account>> getByName(@PathVariable(name = "name") String lastName) {
        return accountService.getByName(lastName);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAll() {
        return accountService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable(name="id") Integer id){
        return accountService.updateAccount(account, id);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAccount(@RequestParam Integer id){
        return accountService.deleteAccount(id);
    }
}
