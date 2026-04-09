package com.easybank.digitalbanking.controller;

import com.easybank.digitalbanking.model.Accounts;
import com.easybank.digitalbanking.model.Customer;
import com.easybank.digitalbanking.repository.AccountsRepository;
import com.easybank.digitalbanking.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/easybank")
@RequiredArgsConstructor
public class AccountController {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myaccount")
    public Accounts getAccountDetails(@RequestParam String email){
        Optional<Customer> optionalCustomer=customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            Accounts accounts = accountsRepository.findByCustomerId(optionalCustomer.get().getId());
            if (accounts != null) {
                return accounts;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
