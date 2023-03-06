package com.sree.banking.transaction.model;

import java.util.List;

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
public class User {
	

	private Long id;
	
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	

	private String phoneNumber;

	private String password;

	private List<Account> accounts;

	
	
}
