package com.khanhhoang.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String balance;

    public CustomerDTO(long id, String name, String email, String phone, String address, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance.toString();
    }
}
