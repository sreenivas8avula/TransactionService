package com.sree.banking.transaction.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sreenivasulu.Avula
 *
 */

public class EncryptDecrypt {
	private static final Logger logger = LogManager.getLogger(EncryptDecrypt.class);
	
	private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";

    public void prepareSecreteKey() throws Exception {
        MessageDigest sha = null;
        final String myKey = "secrete";

        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (Exception e) {
        	logger.error("Error while prepare secrete key in EncryptDecrypt -> "+e.getMessage());
        	throw new Exception("Error while prepare secrete key in EncryptDecrypt: "+e.getMessage());
        }
    }

    public String encrypt(String strToEncrypt) throws Exception {
        try {
            prepareSecreteKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
        	logger.error("Error while encrypting: " + e.getMessage());
        	throw new Exception("Error while encrypting: " + e.getMessage());
        }
    }

    public String decrypt(String strToDecrypt) throws Exception {
        try {
            prepareSecreteKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
        	logger.error("Error while decrypting: " + e.toString());
        	throw new Exception("Error while decrypting: " + e.getMessage());
        }
    }
    
 
    public static String getBasicAuthToken(String user, String pwd) throws Exception {
    	
    	//String decryptedPwd = new EncryptDecrypt().decrypt(encryptedPwd);
    	
    	String encoding = Base64.getEncoder().encodeToString((user+":"+pwd).getBytes());
    	
    	return "Basic "+encoding;
    }

    
}
