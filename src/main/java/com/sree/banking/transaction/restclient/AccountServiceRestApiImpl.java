package com.sree.banking.transaction.restclient;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sree.banking.transaction.model.Account;
import com.sree.banking.transaction.model.User;
import com.sree.banking.transaction.model.request.DepositRequest;
import com.sree.banking.transaction.model.request.WithdrawRequest;
import com.sree.banking.transaction.model.response.DepositResponse;
import com.sree.banking.transaction.model.response.WithdrawResponse;
import com.sree.banking.transaction.util.EncryptDecrypt;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Service
public class AccountServiceRestApiImpl implements AccountServiceRestApi{

	private static final Logger logger = LogManager.getLogger(AccountServiceRestApiImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${accountservice.url.path}")
	private String accountServiceURL; 
	
	@Value("${accountservic.user}")
	private String user_name;
	
	@Value("${accountservic.secret}")
	private String secret;

	public User getUserByAccountNumber(Long accountNumber) throws Exception {

		try {
			 
			 HttpHeaders httpHeaders = new HttpHeaders();
			 
			 httpHeaders.add(HttpHeaders.AUTHORIZATION, EncryptDecrypt.getBasicAuthToken(user_name, secret));
				
			 HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
			
			 ResponseEntity<User> responseEntity = restTemplate.exchange(accountServiceURL+"/getUserByAccountNumber/"+accountNumber,HttpMethod.GET, entity, User.class);
			
			 return responseEntity.getBody();
			
		}catch(Exception ex) {
			
			logger.error(""+ex.getMessage());
			
			throw new Exception(ex.getMessage());
		}
	}
	
	public HttpStatusCode depositAmount(Long accountNumber, BigDecimal amount) throws Exception {
		try {
			
			DepositRequest depositReq = new DepositRequest(accountNumber, amount);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			 
			httpHeaders.add(HttpHeaders.AUTHORIZATION, EncryptDecrypt.getBasicAuthToken(user_name, secret));
			 
			HttpEntity<?> entity = new HttpEntity<>(depositReq, httpHeaders);
			
			 ResponseEntity<DepositResponse> responseEntity = restTemplate.exchange(accountServiceURL+"/account/deposit",HttpMethod.POST, entity , DepositResponse.class);
			
			return responseEntity.getStatusCode();
			
		}catch(Exception ex) {
			
			logger.error(""+ex.getMessage());
			
			throw new Exception(ex.getMessage());
		}
	}
	

	public HttpStatusCode withdrawAmount(Long accountNumber, BigDecimal amount) throws Exception {
		try {
			
			WithdrawRequest withdrawRequest = new WithdrawRequest(accountNumber, amount);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			 
			httpHeaders.add(HttpHeaders.AUTHORIZATION, EncryptDecrypt.getBasicAuthToken(user_name, secret));
			
			HttpEntity<?> entity = new HttpEntity<>(withdrawRequest, httpHeaders);
			
			ResponseEntity<WithdrawResponse> responseEntity = restTemplate.exchange(accountServiceURL+"/account/withdraw",HttpMethod.POST, entity , WithdrawResponse.class);
			
			return responseEntity.getStatusCode();
			
		}catch(Exception ex) {
			
			logger.error(""+ex.getMessage());
			
			throw new Exception(ex.getMessage());
		}
	}
	
	public Account getAccountByNumber(Long accountNumber) throws Exception {
		try {
			
			 HttpHeaders httpHeaders = new HttpHeaders();
			 
			 httpHeaders.add(HttpHeaders.AUTHORIZATION, EncryptDecrypt.getBasicAuthToken(user_name, secret));
				
			 HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
			 
			 ResponseEntity<Account> responseEntity = restTemplate.exchange(accountServiceURL+"/account/"+accountNumber, HttpMethod.GET, entity, Account.class);
			
			return responseEntity.getBody();
		}catch(Exception ex) {
			
			logger.error(""+ex.getMessage());
			
			throw new Exception(ex.getMessage());
		}
	}
}
