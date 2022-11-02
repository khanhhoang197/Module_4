package com.khanhhoang.storedprocedure.repository;

import com.khanhhoang.storedprocedure.model.Customer;

public interface ICustomerRepository {
    boolean insertWithStoredProcedure(Customer customer);
}
