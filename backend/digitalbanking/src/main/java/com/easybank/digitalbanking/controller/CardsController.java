package com.easybank.digitalbanking.controller;

import com.easybank.digitalbanking.model.Cards;
import com.easybank.digitalbanking.model.Customer;
import com.easybank.digitalbanking.repository.AccountTransactionsRepository;
import com.easybank.digitalbanking.repository.CardsRepository;
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
public class CardsController {
    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/mycards")
    public List<Cards> getCardDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<Cards> cards = cardsRepository.findByCustomerId(optionalCustomer.get().getId());
            if (cards != null) {
                return cards;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
