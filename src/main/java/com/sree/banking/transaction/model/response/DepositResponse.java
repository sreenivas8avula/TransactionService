package com.sree.banking.transaction.model.response;

import java.math.BigDecimal;

import com.sree.banking.transaction.model.request.WithdrawRequest;

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
public class DepositResponse {

	private String message;
	private BigDecimal availableBalance;
	
	/*
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public DepositResponse() {}
	public DepositResponse(String message, BigDecimal availableBalance) {
		super();
		this.message = message;
		this.availableBalance = availableBalance;
	}
	
	*/
}
