package com.khanhhoang.banking.repository;

import com.khanhhoang.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByIdIsNot(Long id);

    List<Customer> findAllByDeletedIsFalse();

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance + :transactionAmount " +
            "WHERE c.id = :customerId"
    )
    void incrementBalance(@Param("transactionAmount") BigDecimal transactionAmount, @Param("customerId") long customerId);


    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance - :transactionAmount " +
            "WHERE c.id = :customerId"
    )
    void reduceBalance(@Param("transactionAmount") BigDecimal transactionAmount, @Param("customerId") long customerId);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = :balance " +
            "WHERE c.id = :customerId")
    void setBalance(@Param("customerId") Long customerId, @Param("balance") BigDecimal balance);

    List<Customer> findAllByFullNameLikeOrEmailLikeAndDeletedIsFalse(String fullName, String email);

}
