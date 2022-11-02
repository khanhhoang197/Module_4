package com.khanhhoang.customermanagerprovincial.service;

import com.khanhhoang.customermanagerprovincial.model.Customer;
import com.khanhhoang.customermanagerprovincial.model.Province;

public interface ICustomerService extends IGeneralService<Customer>{
    Iterable<Customer> findAllByProvince(Province province);
}

