package com.sree.banking.transaction.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sreenivasulu.Avula
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferResponse {

	private String message;
	private String transactionId;
	
}
