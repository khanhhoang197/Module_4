package com.khanhhoang.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositDTO {

    private long id;

    private long customerId;

    @Pattern(regexp = "^\\d+$", message = "Số tiền gửi phải là số")
    private String transactionAmount;

}