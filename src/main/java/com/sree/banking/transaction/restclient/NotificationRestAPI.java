package com.sree.banking.transaction.restclient;

import org.springframework.stereotype.Service;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Service
public interface NotificationRestAPI {

	public void sendNotification(String toEmail, String subject, String body);
}
