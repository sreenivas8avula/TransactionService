package com.sree.banking.transaction.model.response;

import java.math.BigDecimal;

import com.sree.banking.transaction.model.request.WithdrawRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferResponse {

	private String message;
	private String transactionId;
	
	
	/*
	
	public FundTransferResponse() {}
	public FundTransferResponse(String message, String transactionId) {
		super();
		this.message = message;
		this.transactionId = transactionId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	*/
	
}
