package org.example.repository;

import org.example.base.repository.BaseEntityRepository;
import org.example.domain.Customer;

import java.sql.SQLException;


public interface CustomerRepository extends BaseEntityRepository<Customer,Long> {
    public Customer login(String userName);
    boolean existByUsernameAndPassword(String username, String password);
    Customer findByUsername(String username);

}
