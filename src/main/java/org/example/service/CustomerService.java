package org.example.service;

import org.example.base.service.BaseEntityService;
import org.example.domain.Customer;

import java.sql.SQLException;

public interface CustomerService extends BaseEntityService<Customer,Long> {
    Customer login(String username);
    boolean login(String username, String password);
    Customer findByUsername(String username);
}
