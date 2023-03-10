package com.sree.banking.transaction.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.sree.banking.transaction.exceptions.DatabaseException;
import com.sree.banking.transaction.model.TransactionEntity;
import com.sree.banking.transaction.service.TransactionServiceImpl;
import com.sree.banking.transaction.util.EncryptDecrypt;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@Autowired
	private MockMvc mocMvc;
	
	@Autowired
	private TransactionController transactionController;
	
	@MockBean
	private TransactionServiceImpl transactionService;
	

	private static HttpHeaders httpHeaders;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
			
			 httpHeaders = new HttpHeaders();
			 httpHeaders.add(HttpHeaders.AUTHORIZATION, EncryptDecrypt.getBasicAuthToken("sree", "eers"));
	}

	@Test
	public void testGetTransactionById_200() throws Exception {
		
		long transactionId = 1L;
		when(transactionService.getTransaction(transactionId)).thenReturn(new TransactionEntity());
		
		ResultActions resultActions=this.mocMvc.perform(get("/transaction-service/v1/transaction/"+transactionId)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.headers(httpHeaders));
		
		resultActions.andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetTransactionById_404() throws Exception {
		
		long transactionId = -1L;
		when(transactionService.getTransaction(transactionId)).thenThrow(new DatabaseException("Transaction not found in DB."));
		
		ResultActions resultActions=this.mocMvc.perform(get("/transaction-service/v1/transaction/"+transactionId)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.headers(httpHeaders));
		
		resultActions.andExpect(status().is5xxServerError());
		
	}

	/*
	@Test
	void testGetAllTransactions() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCreateTransaction() {
		fail("Not yet implemented");
	}

	@Test
	void testFundTransfer() {
		fail("Not yet implemented");
	}

	@Test
	void testPing() {
		fail("Not yet implemented");
	}

*/
}
