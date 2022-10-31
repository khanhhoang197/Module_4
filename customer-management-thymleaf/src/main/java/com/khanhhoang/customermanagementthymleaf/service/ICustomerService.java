package com.khanhhoang.customermanagementthymleaf.service;

import com.khanhhoang.customermanagementthymleaf.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    void save (Customer customer);
    void update(int id, Customer customer);
    void remove(int id);
    Customer findById(int id);
}
