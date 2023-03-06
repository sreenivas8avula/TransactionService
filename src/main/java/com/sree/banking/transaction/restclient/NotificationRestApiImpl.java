package com.sree.banking.transaction.restclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sree.banking.transaction.exceptions.SendEmailNotificationException;
import com.sree.banking.transaction.model.EmailDetails;
import com.sree.banking.transaction.util.EncryptDecrypt;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Service
public class NotificationRestApiImpl implements NotificationRestAPI {

	private static final Logger logger = LogManager.getLogger(NotificationRestApiImpl.class);
	
	@Value("${notification.service.url.path}")
	private String notificationURL;
	
	@Value("${notification.service.user}")
	private String user;
	
	@Value("${notification.service.secret}")
	private String secret;
	
	@Autowired
	private RestTemplate restTemplate;


	
	public void sendNotification(String toEmail, String subject, String body)  {
		
		try {
			 
			//EmailDetails requestBody = new EmailDetails(toEmail,body,subject);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			
			httpHeaders.add(HttpHeaders.AUTHORIZATION, EncryptDecrypt.getBasicAuthToken(user, secret));
			
			HttpEntity<?> entity = new HttpEntity<>(new EmailDetails(toEmail,body,subject) , httpHeaders);
			 
			new Thread(()->{ 
				
				try {
					
					restTemplate.exchange(notificationURL, HttpMethod.POST, entity, String.class);
				
				} catch (Exception e) {
					
					logger.error("Error while sending an email notification ->"+e.getMessage()); 
					
					throw new SendEmailNotificationException("Error while sending an email notification ->"+e.getMessage());
				}
			
			}).start();
			 
			 
		 }catch(Exception ex) {
				
			 logger.error("Error while sending an email notification ->"+ex.getMessage());
				
			 throw new SendEmailNotificationException("Error while sending an email notification ->"+ex.getMessage());
			
		 }
		 
	 
	}
}
