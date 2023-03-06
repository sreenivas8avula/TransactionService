package com.sree.banking.transaction.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.sree.banking.transaction.exceptions.DatabaseException;
import com.sree.banking.transaction.model.Account;
import com.sree.banking.transaction.model.TransactionEntity;
import com.sree.banking.transaction.model.TransactionType;
import com.sree.banking.transaction.model.User;
import com.sree.banking.transaction.model.request.FundTransferRequest;
import com.sree.banking.transaction.model.response.FundTransferResponse;
import com.sree.banking.transaction.repository.TransactionRepository;
import com.sree.banking.transaction.restclient.AccountServiceRestApiImpl;
import com.sree.banking.transaction.restclient.NotificationRestApiImpl;
import com.sree.banking.transaction.util.TransactionConstants;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private NotificationRestApiImpl notificationRestApi;
	
	@Autowired
	private AccountServiceRestApiImpl accountServiceRestApi;
	
		
	public TransactionEntity createTransaction(TransactionEntity transaction) {
		try {
			
			return transactionRepository.save(transaction);
		
		}catch(Exception ex) {
			logger.error(""+ex.getMessage());
			throw new DatabaseException(ex.getMessage());
		}
	}
	
	public TransactionEntity getTransaction(Long id) {

		try {
			
			return transactionRepository.findById(id).get();
		
		}catch(Exception ex) {
			logger.error(""+ex.getMessage());
			throw new DatabaseException(ex.getMessage());
		}
	}
	
	public List<TransactionEntity> getAllTransactions(){
		try {
			
			return transactionRepository.findAll();
		
		}catch(Exception ex) {
			
			logger.error(""+ex.getMessage());
			
			throw new DatabaseException(ex.getMessage());
		}
	}
	
	
	public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) throws Exception {
		
		try {
			
			Account fromAccount = accountServiceRestApi.getAccountByNumber(fundTransferRequest.getFromAccount());
			
			boolean valdiateBalanceFlag = validateBalance(fromAccount.getBalance(), fundTransferRequest.getAmount());
			
			if(valdiateBalanceFlag) {
				
				String transactionId = internalFundTransfer(fundTransferRequest.getFromAccount(), fundTransferRequest.getToAccount(), fundTransferRequest.getAmount());
				
				User user = accountServiceRestApi.getUserByAccountNumber(fromAccount.getNumber());
				
				notificationRestApi.sendNotification(user.getEmail(),TransactionConstants.FUND_TRANSFER_SUBJECT, String.format(TransactionConstants.FUND_TRANSFER_BODY, user.getFirstName(),fundTransferRequest.getAmount(), fundTransferRequest.getToAccount()) );
				
				return new FundTransferResponse("Transaction successfully completed",transactionId);
				
			}else {
				
				return new FundTransferResponse("Transaction Failed due to insufficient funds in account.","");
			}
		}catch(Exception ex) {
			
			logger.error(""+ex.getMessage());
			
			throw new Exception(ex.getMessage());
		}
		
	}
	
	private String internalFundTransfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal transferAmount) throws Exception {
		
		String transactionId = UUID.randomUUID().toString();
		
		try {
			
			HttpStatusCode withdrawResponseStatusCode = accountServiceRestApi.withdrawAmount(fromAccountNumber, transferAmount);
			
			logger.info("withdrawResponseStatusCode "+withdrawResponseStatusCode);
			
			if(withdrawResponseStatusCode.is2xxSuccessful()) {
				
				withdrawProcessTransaction(fromAccountNumber, toAccountNumber, transferAmount, transactionId);
				
				HttpStatusCode depositResponseStatusCode = accountServiceRestApi.depositAmount(toAccountNumber, transferAmount);
				
				if(!depositResponseStatusCode.is2xxSuccessful()) {
					//Refund to the acccount if fails while transfer
					HttpStatusCode refundResponseStatusCode = accountServiceRestApi.depositAmount(fromAccountNumber, transferAmount);
				}
				
				depositProcessTransaction( toAccountNumber, transferAmount, transactionId);
			}
		}catch(Exception ex) {
			logger.error(""+ex.getMessage());
			throw new Exception(ex.getMessage());
		}
		
		return transactionId;
	}
	
	private void withdrawProcessTransaction(Long fromAccountNumber, Long toAccountNumber, BigDecimal transferAmount, String transactionId) {
		
		try {
			
			TransactionEntity withdrawTransactionEntity = new TransactionEntity();
			
			withdrawTransactionEntity.setTransactionType(TransactionType.FUND_TRANSFER);
			withdrawTransactionEntity.setReferenceNumber(""+toAccountNumber);
			withdrawTransactionEntity.setTransactionId(transactionId);
			withdrawTransactionEntity.setAccountNumber(fromAccountNumber);
			withdrawTransactionEntity.setAmount(transferAmount.negate());
			
			TransactionEntity transactionEntity=transactionRepository.save(withdrawTransactionEntity);
			
		}catch(Exception ex) {
			logger.error(""+ex.getMessage());
			throw new DatabaseException(ex.getMessage());
		}
	}
	

	private void depositProcessTransaction(Long toAccountNumber, BigDecimal transferAmount, String transactionId) {
		

		try {
			
			TransactionEntity withdrawTransactionEntity = new TransactionEntity();
			
			withdrawTransactionEntity.setTransactionType(TransactionType.FUND_TRANSFER);
			withdrawTransactionEntity.setReferenceNumber(""+toAccountNumber);
			withdrawTransactionEntity.setTransactionId(transactionId);
			withdrawTransactionEntity.setAccountNumber(toAccountNumber);
			withdrawTransactionEntity.setAmount(transferAmount);
			
			transactionRepository.save(withdrawTransactionEntity);
		}catch(Exception ex) {
			logger.error(""+ex.getMessage());
			throw new DatabaseException(ex.getMessage());
		}
		
	}
	private boolean validateBalance(BigDecimal actualAmount, BigDecimal withdrawAmount) {
        if (actualAmount.compareTo(BigDecimal.ZERO) < 0 || actualAmount.compareTo(withdrawAmount) < 0)
        	return false;
        else return true;
    }
	
	
}
