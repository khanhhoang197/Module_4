package com.khanhhoang.banking.service.transfer;

import com.khanhhoang.banking.model.Transfer;
import com.khanhhoang.banking.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceImpl implements ITransferService{
    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer getById(Long id) {
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public BigDecimal getSumFeesAmount() {
        return transferRepository.getSumFeesAmount();
    }

    @Override
    public void remove(Long id) {
        //Chưa code
    }
}