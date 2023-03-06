package com.sree.banking.transaction.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name="transaction_tbl")
public class TransactionEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="transaction_id")
	private String transactionId;
	
	private BigDecimal amount;
	
	@Column(name="transaction_type")
	private TransactionType transactionType;
	
	@Column(name="reference_number")
	private String referenceNumber;
	
	@Column(name="account_number")
	private Long accountNumber;

	

}
