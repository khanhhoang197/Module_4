package com.khanhhoang.banking.service.customer;


import com.khanhhoang.banking.model.Customer;
import com.khanhhoang.banking.model.Deposit;
import com.khanhhoang.banking.model.Transfer;
import com.khanhhoang.banking.model.Withdraw;
import com.khanhhoang.banking.repository.CustomerRepository;
import com.khanhhoang.banking.repository.DepositRepository;
import com.khanhhoang.banking.repository.TransferRepository;
import com.khanhhoang.banking.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }


    @Override
    public List<Customer> findAllByFullNameLikeOrEmailLikeAndDeletedIsFalse(String valueSearch) {
        return null;
//        return customerRepository.findAllByFullNameLikeOrEmailLikeAndDeletedIsFalse(valueSearch, valueSearch);
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

//    @Override
//    public List<Customer> findAllByDeletedIsFalse() {
//        return customerRepository.findAllByDeletedIsFalse();
//    }

    @Override
    public void deposit(Deposit deposit, Customer customer) {
        deposit.setId(0L);
        deposit.setCustomer(customer);
        depositRepository.save(deposit);

        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        BigDecimal newBalance = currentBalance.add(transactionAmount);
        customerRepository.setBalance(customer.getId(), newBalance);
    }

    @Override
    public boolean withdraw(Withdraw withdraw, Customer customer) {
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        BigDecimal newBalance = currentBalance.subtract(transactionAmount);
        BigDecimal big0 = new BigDecimal(0);
        if (newBalance.compareTo(big0) < 0) {
            return false;
        }
        customerRepository.setBalance(customer.getId(), newBalance);

        withdraw.setId(0L);
        withdraw.setCustomer(customer);
        withdrawRepository.save(withdraw);
        return true;
    }

    @Override
    public Customer transfer(Transfer transfer) {

        customerRepository.reduceBalance(transfer.getTransactionAmount(), transfer.getSender().getId());

        customerRepository.incrementBalance(transfer.getTransferAmount(), transfer.getRecipient().getId());

        transferRepository.save(transfer);

        Optional<Customer> sender = customerRepository.findById(transfer.getSender().getId());

        return sender.get();
    }


    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Customer> findAllByIdNot(Long id) {
        return customerRepository.findAllByIdIsNot(id);
    }


}
