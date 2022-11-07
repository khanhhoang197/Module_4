package com.khanhhoang.banking.formatter;

import com.khanhhoang.banking.model.Customer;
import com.khanhhoang.banking.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class CustomerFormatter implements Formatter<Customer> {
    @Autowired
    private ICustomerService customerService;

    @Override
    public Customer parse(String text, Locale locale) throws ParseException {
        Optional<Customer> customerOptional = customerService.findById(Long.parseLong(text));
        return customerOptional.orElse(null);
    }

    @Override
    public String print(Customer object, Locale locale) {
        return "[" + object.getId() + ", " +object.getFullName() + "]";
    }
}