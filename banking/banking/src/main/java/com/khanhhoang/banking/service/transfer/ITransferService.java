package com.khanhhoang.banking.service.transfer;

import com.khanhhoang.banking.model.Transfer;
import com.khanhhoang.banking.service.IGeneralService;

import java.math.BigDecimal;

public interface ITransferService extends IGeneralService<Transfer> {
    BigDecimal getSumFeesAmount();
}