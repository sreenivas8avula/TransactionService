package com.sree.banking.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sree.banking.transaction.model.TransactionEntity;
import com.sree.banking.transaction.model.request.FundTransferRequest;
import com.sree.banking.transaction.model.response.FundTransferResponse;
import com.sree.banking.transaction.service.TransactionServiceImpl;

/**
 * @author Sreenivasulu.Avula
 *
 */

@RestController
@RequestMapping("/transaction-service/v1")
public class TransactionnController {
	
	
	@Autowired
	private TransactionServiceImpl transactionService;
	
	
	@PostMapping(value="/transaction/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionEntity createTransaction(@RequestBody TransactionEntity transaction) throws Exception {
		return transactionService.createTransaction(transaction);
	}
	
	@PostMapping(value="/transaction/fundTransfer",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public FundTransferResponse fundTransfer(@RequestBody FundTransferRequest fundRequest) throws Exception {
		return transactionService.fundTransfer(fundRequest);
	}
	
	@GetMapping(value="/transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionEntity getTransaction(@PathVariable Long id) {
		return transactionService.getTransaction(id);
	}
	
	@GetMapping(value="/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TransactionEntity> getAllTransactions(){
		return transactionService.getAllTransactions();
	}
		
	
	@GetMapping("/transaction/ping")
	public String ping() {
		return "Health Check is Success";
	}

}
