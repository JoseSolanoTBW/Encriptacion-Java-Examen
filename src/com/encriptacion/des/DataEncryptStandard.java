package com.encriptacion.des;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.encriptacion.common.FileReaderAndWritter;
import com.encriptacion.common.RandomBytes;
import com.encriptacion.factory.Encryptioner;

public class DataEncryptStandard extends Encryptioner{
	
	private final int KEYSIZE = 17;
	private final String PATH = "C:/encrypt/standard/";
	
	@Override
	public void createKey(String keyName) throws Exception {
		byte[] key = RandomBytes.generatedSequenceOfBytes(KEYSIZE);
		FileReaderAndWritter.writeBytesFile(keyName, key, KEY_EXTENSION, PATH);		
	}

	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		SecretKey secretKeyCreated = getSecretKey(keyName);
		
		Cipher cipher = Cipher.getInstance("DES");		 
		cipher.init(Cipher.ENCRYPT_MODE, secretKeyCreated);
		
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		Encoder oneEncoder = Base64.getEncoder();
		encryptedData = oneEncoder.encode(encryptedData);
		FileReaderAndWritter.writeBytesFile(messageName, encryptedData, MESSAGE_ENCRYPT_EXTENSION, PATH);
		
	}

	@Override
	public void decryptMessage(String messageName, String keyName) throws Exception {
		byte[] encryptedMessage = readMessageFile(messageName, PATH);
		SecretKey secretKeyCreated = getSecretKey(keyName);
		
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeyCreated);
		
		byte[] DecryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(DecryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: ");
		System.out.println(message);		
	}	
	
	private SecretKey getSecretKey(String keyName) throws Exception {
		byte[] key = FileReaderAndWritter.readKeyFile(keyName, PATH, KEY_EXTENSION);
		KeySpec keySpec = new DESKeySpec(key);
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance("DES");
		return keyFac.generateSecret(keySpec); 
	}
	


}
