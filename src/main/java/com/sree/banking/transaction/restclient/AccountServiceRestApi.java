package com.sree.banking.transaction.restclient;

import java.math.BigDecimal;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.sree.banking.transaction.model.Account;
import com.sree.banking.transaction.model.User;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Service
public interface AccountServiceRestApi {
	
	public User getUserByAccountNumber(Long accountNumber) throws Exception;
	
	public HttpStatusCode depositAmount(Long accountNumber, BigDecimal amount) throws Exception;
	
	public HttpStatusCode withdrawAmount(Long accountNumber, BigDecimal amount) throws Exception;
	
	public Account getAccountByNumber(Long accountNumber) throws Exception;

}
