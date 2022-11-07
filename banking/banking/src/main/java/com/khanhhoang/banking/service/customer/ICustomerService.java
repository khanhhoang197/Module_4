package com.khanhhoang.banking.service.customer;

import com.khanhhoang.banking.model.Customer;
import com.khanhhoang.banking.model.Deposit;
import com.khanhhoang.banking.model.Transfer;
import com.khanhhoang.banking.model.Withdraw;
import com.khanhhoang.banking.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {

    List<Customer> findAllByDeletedIsFalse();


    List<Customer> findAllByFullNameLikeOrEmailLikeAndDeletedIsFalse(String valueSearch);

    void deposit(Deposit deposit, Customer customer);

    boolean withdraw(Withdraw withdraw, Customer customer);

    Customer transfer(Transfer transfer);

    List<Customer> findAllByIdNot(Long id);

}
