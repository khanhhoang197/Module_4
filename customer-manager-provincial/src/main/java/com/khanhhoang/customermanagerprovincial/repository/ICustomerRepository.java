package com.khanhhoang.customermanagerprovincial.repository;

import com.khanhhoang.customermanagerprovincial.model.Customer;
import com.khanhhoang.customermanagerprovincial.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);
}
