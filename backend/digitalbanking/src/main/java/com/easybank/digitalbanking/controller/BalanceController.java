package com.easybank.digitalbanking.controller;

import com.easybank.digitalbanking.model.AccountTransactions;
import com.easybank.digitalbanking.model.Customer;
import com.easybank.digitalbanking.repository.AccountTransactionsRepository;
import com.easybank.digitalbanking.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/easybank")
@RequiredArgsConstructor
public class BalanceController {
    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/mybalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
            Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
            if (optionalCustomer.isPresent()) {
                List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                        findByCustomerIdOrderByTransactionDtDesc(optionalCustomer.get().getId());
                if (accountTransactions != null) {
                    return accountTransactions;
                } else {
                    return null;
                }
            } else {
                return null;
            }
    }
}
