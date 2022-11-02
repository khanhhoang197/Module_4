package com.khanhhoang.storedprocedure.repository;

import com.khanhhoang.storedprocedure.model.Customer;

public interface ICustomerService {
    boolean insertWithStoredProcedure(Customer customer);
}
