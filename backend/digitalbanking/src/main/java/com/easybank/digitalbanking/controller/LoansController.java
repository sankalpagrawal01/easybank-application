package com.easybank.digitalbanking.controller;

import com.easybank.digitalbanking.model.Customer;
import com.easybank.digitalbanking.model.Loans;
import com.easybank.digitalbanking.repository.CustomerRepository;
import com.easybank.digitalbanking.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/easybank")
@RequiredArgsConstructor
public class LoansController {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myloans")
//    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(optionalCustomer.get().getId());
            if (loans != null) {
                return loans;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
