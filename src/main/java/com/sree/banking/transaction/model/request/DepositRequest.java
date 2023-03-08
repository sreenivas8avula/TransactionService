package com.sree.banking.transaction.model.request;

import java.math.BigDecimal;

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
public class DepositRequest {

	private Long accountNumber;
	private BigDecimal amount;
	
	
}
