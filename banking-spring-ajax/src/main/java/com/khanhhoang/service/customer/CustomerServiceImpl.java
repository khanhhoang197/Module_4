package com.khanhhoang.service.customer;

import com.khanhhoang.model.Customer;
import com.khanhhoang.model.Deposit;
import com.khanhhoang.model.Transfer;
import com.khanhhoang.model.Withdraw;
import com.khanhhoang.model.dto.CustomerDTO;
import com.khanhhoang.repository.CustomerRepository;
import com.khanhhoang.repository.DepositRepository;
import com.khanhhoang.repository.TransferRepository;
import com.khanhhoang.repository.WithdrawRepository;
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
    public List<CustomerDTO> getAllCustomerDTO() {
        return customerRepository.getAllCustomerDTO();
    }

    @Override
    public List<CustomerDTO> findAllICustomerDTOByDeletedIsFalse(){
        return customerRepository.getAllICustomerDTOByDeletedIsFalse();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllByIdNot(long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByEmailAndIdIsNot(String email, Long id) {
        return customerRepository.findByEmailAndIdIsNot(email, id);
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Boolean existsByIdEquals(long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public Customer deposit(Customer customer, Deposit deposit) {
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();

        try {
            customerRepository.incrementBalance(transactionAmount, customer.getId());

            deposit.setId(0L);
            deposit.setCustomer(customer);
            depositRepository.save(deposit);

            Optional<Customer> newCustomer = customerRepository.findById(customer.getId());
            return newCustomer.get();
        } catch (Exception e) {
            e.printStackTrace();
            customer.setBalance(currentBalance);
            return customer;
        }
    }

    @Override
    public Customer withdraw(Customer customer, Withdraw withdraw) {
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();

        try {
            customerRepository.reduceBalance(transactionAmount, customer.getId());

            withdraw.setId(0L);
            withdraw.setCustomer(customer);
            withdrawRepository.save(withdraw);

            Optional<Customer> newCustomer = customerRepository.findById(customer.getId());

            return newCustomer.get();
        } catch (Exception e) {
            e.printStackTrace();
            customer.setBalance(currentBalance);
            return customer;
        }
    }

    @Override
    public Customer transfer(Transfer transfer) {

        customerRepository.reduceBalance(transfer.getTransactionAmount(), transfer.getSender().getId());

        customerRepository.incrementBalance(transfer.getTransferAmount(), transfer.getRecipient().getId());

        transferRepository.save(transfer);

        Optional<Customer> newSender = customerRepository.findById(transfer.getSender().getId());

        return newSender.get();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(long customerId) {
        customerRepository.softDelete(customerId);
    }

//    @Override
//    public List<RecipientDTO> getAllRecipientDTO(long senderId) {
//        return customerRepository.getAllRecipientDTO(senderId);
//    }
}