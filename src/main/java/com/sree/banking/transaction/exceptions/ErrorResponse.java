package com.sree.banking.transaction.exceptions;

/**
 * @author Sreenivasulu.Avula
 *
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp;
    private int code;
    private String message;
    
    
}
