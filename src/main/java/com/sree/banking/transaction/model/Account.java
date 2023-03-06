package com.sree.banking.transaction.model;

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
public class Account {

	
	private Long number;
	
	private AccountType type;
	
	private Boolean isActive = true;
	
	private BigDecimal balance;
	
	
}
