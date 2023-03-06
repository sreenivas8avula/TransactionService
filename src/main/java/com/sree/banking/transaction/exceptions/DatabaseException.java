package com.sree.banking.transaction.exceptions;

/**
 * @author Sreenivasulu.Avula
 *
 */

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
