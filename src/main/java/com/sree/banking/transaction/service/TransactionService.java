package com.sree.banking.transaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sree.banking.transaction.model.TransactionEntity;
import com.sree.banking.transaction.model.request.FundTransferRequest;
import com.sree.banking.transaction.model.response.FundTransferResponse;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Service
public interface TransactionService {
	
	public TransactionEntity createTransaction(TransactionEntity transaction);
	public TransactionEntity getTransaction(Long id);
	public List<TransactionEntity> getAllTransactions();
	
	public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) throws Exception;
	
	
}
