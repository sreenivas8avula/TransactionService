package com.sree.banking.transaction.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.sree.banking.transaction.model.Account;
import com.sree.banking.transaction.model.TransactionEntity;
import com.sree.banking.transaction.model.User;
import com.sree.banking.transaction.model.request.FundTransferRequest;
import com.sree.banking.transaction.model.response.FundTransferResponse;
import com.sree.banking.transaction.repository.TransactionRepository;
import com.sree.banking.transaction.util.EncryptDecrypt;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	static HttpHeaders httpHeaders; 
	
	private TransactionEntity deposit_Txn;
	private TransactionEntity withdraw_Txn;
	private TransactionEntity createdTxn;
	
	private User user1, user2;
	private Account account1, account2;
	

	@Autowired
	private  TransactionRepository transactionRepository;
	
	@BeforeAll
	public static void init() throws Exception {
		restTemplate = new RestTemplate();
		
		 httpHeaders = new HttpHeaders();
		 httpHeaders.add(HttpHeaders.AUTHORIZATION, EncryptDecrypt.getBasicAuthToken("sree", "eers"));
		
		 /*
		 user1.setFirstName("junit_integ_test1");
		 user1.setLastName("IntegTest");
		 user1.setEmail("junit.test@gmail.com");
		 user1.setPassword("junitpassword");
		 user1.setPhoneNumber("8899665544");
		 
		 account1.setType(AccountType.FIXED_DEPOSIT);
		 account1.setBalance(new BigDecimal(20000));
		 List<Account> accounts = new ArrayList<Account>();
		 accounts.add(account1);
		 user1.setAccounts(accounts);
		 
		 user2.setFirstName("junit_integ_test2");
		 user2.setLastName("IntegTest");
		 user2.setEmail("junit.test2@gmail.com");
		 user2.setPassword("junitpassword2");
		 user2.setPhoneNumber("8899665522");
		 
		 account2.setType(AccountType.FIXED_DEPOSIT);
		 account2.setBalance(new BigDecimal(20000));
		 List<Account> accounts2 = new ArrayList<Account>();
		 accounts.add(account2);
		 user2.setAccounts(accounts2);
			*/
			
	}
	
	 @BeforeEach 
	 public void beforeSetup() {
		  baseUrl = baseUrl + ":" +port + "/transaction-service/v1";
		 
		  deposit_Txn=TransactionEntity.builder()
				  .accountNumber(1555L)
				  .amount(new BigDecimal(100))
				  .referenceNumber(""+1553)
				  .build();
		  
		  deposit_Txn=transactionRepository.save(deposit_Txn);
		  
		  withdraw_Txn=TransactionEntity.builder()
				  .accountNumber(1555L)
				  .amount(new BigDecimal(100))
				  .referenceNumber(""+1533)
				  .build();
		  
		  //  withdraw_Txn=transactionRepository.save(withdraw_Txn);
	  
	  }
	 
	
	@AfterEach
	public void afterSetup() {
		
		//transactionRepository.deleteById(withdraw_Txn.getId());
		transactionRepository.deleteById(deposit_Txn.getId());
		
		if(createdTxn != null )
		   transactionRepository.deleteById(createdTxn.getId());
		
	}
	
	
	@Test
	public void createTransactionTest() throws Exception {
		
		TransactionEntity create_txnEntity=TransactionEntity.builder()
				.accountNumber(1555L)
				.amount(new BigDecimal(100))
				.referenceNumber(""+1553)
				.transactionId("JunitIntegTest")
				.build();
		
		
		 HttpEntity<?> entity = new HttpEntity<>(create_txnEntity, httpHeaders);
		 
		createdTxn= restTemplate.exchange(baseUrl+"/transaction/create", HttpMethod.POST, entity, TransactionEntity.class).getBody();
		
		assertNotNull(createdTxn);
		assertThat(createdTxn.getId()).isNotNull();
		
		//transactionRepository.deleteById(createdTxn.getId());
	}
	
	
	@Test
	public void fundTransferTet() {
		
		FundTransferRequest fundRequest =FundTransferRequest.builder()
				.amount(new BigDecimal(100))
				.fromAccount(1533L)
				.toAccount(1555L)
				.build();
		
		HttpEntity<?> entity = new HttpEntity<>(fundRequest, httpHeaders);
		 
		FundTransferResponse fundRespone= restTemplate.exchange(baseUrl+"/transaction/fundTransfer", HttpMethod.POST, entity, FundTransferResponse.class).getBody();
		
		assertNotNull(fundRespone);
		assertThat(fundRespone.getTransactionId()).isNotNull();
	}
	
	@Test
	public void getTransactionByIdTest() {
		
		 HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
		 
		 TransactionEntity transactionObj= restTemplate.exchange(baseUrl+"/transaction/"+deposit_Txn.getId(), HttpMethod.GET, entity, TransactionEntity.class).getBody();
			
			assertNotNull(transactionObj);
			assertThat(transactionObj.getId()).isNotNull();
		
	}
	
	@Test
	public void getAllTransactionsTest() {
		
		httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
		 
		List<TransactionEntity> listTransactions= restTemplate.exchange(baseUrl+"/transactions", HttpMethod.GET, entity, new ParameterizedTypeReference<List<TransactionEntity>>() {}).getBody();
		
		assertNotNull(listTransactions);
		assertThat(listTransactions.size()).isGreaterThan(0);
		assertThat(listTransactions.get(0).getId()).isNotNull();
		
	}
}
