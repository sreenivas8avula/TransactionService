package com.sree.banking.transaction.model;

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
public class EmailDetails {

	private String recipients;
	private String msgBody;
	private String subject;
	
}
