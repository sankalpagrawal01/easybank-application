package com.easybank.digitalbanking.repository;

import com.easybank.digitalbanking.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
    Accounts findByCustomerId(long customerId);
}
