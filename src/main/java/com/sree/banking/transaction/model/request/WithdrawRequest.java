package com.sree.banking.transaction.model.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

	private Long accountNumber;
	private BigDecimal amount;
	
	
	/*
	public WithdrawRequest() {}
	public WithdrawRequest(Long accountNumber, BigDecimal amount) {
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
	*/
}
