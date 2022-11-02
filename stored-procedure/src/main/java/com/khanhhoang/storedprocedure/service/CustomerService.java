package com.khanhhoang.storedprocedure.service;

import com.khanhhoang.storedprocedure.model.Customer;
import com.khanhhoang.storedprocedure.repository.ICustomerRepository;
import com.khanhhoang.storedprocedure.repository.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        return customerRepository.insertWithStoredProcedure(customer);
    }
}
