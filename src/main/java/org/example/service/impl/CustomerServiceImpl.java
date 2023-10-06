package org.example.service.impl;

import org.example.base.service.impl.BaseEntityServiceImpl;
import org.example.domain.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;

import java.sql.SQLException;

public class CustomerServiceImpl extends BaseEntityServiceImpl<Customer,Long, CustomerRepository>
implements CustomerService {
    public CustomerServiceImpl(CustomerRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Customer login(String username){
        return baseRepository.login(username);
    }

    @Override
    public boolean login(String username, String password) {
        return baseRepository.existByUsernameAndPassword(username, password);
    }

    @Override
    public Customer findByUsername(String username) {
        return baseRepository.findByUsername(username);
    }
}
