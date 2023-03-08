package com.sree.banking.transaction.model.response;

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
public class WithdrawResponse {

	private String message;
	private BigDecimal availableBalance;
	
	
}
