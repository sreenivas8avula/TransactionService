package com.sree.banking.transaction.model.request;

import java.math.BigDecimal;

/**
 * @author Sreenivasulu.Avula
 *
 */


public class FundTransferRequest {

	private Long fromAccount;
	private Long toAccount;
	private BigDecimal amount;
	
	
	
	public Long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Long getToAccount() {
		return toAccount;
	}
	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
	
}
