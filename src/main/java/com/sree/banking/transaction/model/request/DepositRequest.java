package com.sree.banking.transaction.model.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sreenivasulu.Avula
 *
 */

public class DepositRequest {

	private Long accountNumber;
	private BigDecimal amount;
	
	
	
	public DepositRequest() {}
	public DepositRequest(Long accountNumber, BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
}
