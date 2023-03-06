package com.sree.banking.transaction.exceptions;

/**
 * @author Sreenivasulu.Avula
 *
 */

public class SendEmailNotificationException extends RuntimeException {
    public SendEmailNotificationException(String message) {
        super(message);
    }
}
