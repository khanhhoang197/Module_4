package com.khanhhoang.service.transfer;

import com.khanhhoang.model.Transfer;
import com.khanhhoang.service.IGeneralService;

import java.math.BigDecimal;

public interface ITransferService extends IGeneralService<Transfer> {

//    List<TransferHistoryDTO> getAllHistories();

    BigDecimal getSumFeesAmount();
}
