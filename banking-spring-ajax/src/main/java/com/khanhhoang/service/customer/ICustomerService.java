package com.khanhhoang.service.customer;

import com.khanhhoang.model.Customer;
import com.khanhhoang.model.Deposit;
import com.khanhhoang.model.Transfer;
import com.khanhhoang.model.Withdraw;
import com.khanhhoang.model.dto.CustomerDTO;
import com.khanhhoang.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    List<CustomerDTO> getAllCustomerDTO();

    List<CustomerDTO> findAllICustomerDTOByDeletedIsFalse();

    List<Customer> findAllByIdNot(long id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    Boolean existsByIdEquals(long id);

    Customer deposit(Customer customer, Deposit deposit);

    Customer withdraw(Customer customer, Withdraw withdraw);

    Customer transfer(Transfer transfer);

    void softDelete(@Param("customerId") long customerId);

//    List<RecipientDTO> getAllRecipientDTO(long senderId);

}
