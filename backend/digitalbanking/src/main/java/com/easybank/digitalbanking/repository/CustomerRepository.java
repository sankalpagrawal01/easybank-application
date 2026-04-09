package com.easybank.digitalbanking.repository;

import com.easybank.digitalbanking.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);
}
