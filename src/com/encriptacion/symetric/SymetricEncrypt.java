package com.encriptacion.symetric;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.encriptacion.common.FileReaderAndWritter;
import com.encriptacion.common.RandomBytes;
import com.encriptacion.factory.Encryptioner;

public class SymetricEncrypt extends Encryptioner {

	private final int KEYSIZE = 8;
	private final String PATH = "C:/encrypt/symetric/";

	public void createKey(String name) throws Exception {
		byte[] key = RandomBytes.generatedSequenceOfBytes(KEYSIZE);
		FileReaderAndWritter.writeBytesFile(name, key, KEY_EXTENSION, PATH);
	}

	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		SecretKeySpec k = getSecretKey(keyName);
		Cipher cipher = Cipher.getInstance("AES");		
		cipher.init(Cipher.ENCRYPT_MODE, k);
		
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		Encoder oneEncoder = Base64.getEncoder();
		encryptedData = oneEncoder.encode(encryptedData);
		FileReaderAndWritter.writeBytesFile(messageName, encryptedData, MESSAGE_ENCRYPT_EXTENSION, PATH);
	}

	public String decryptMessage(String messageName, String keyName) throws Exception {
		byte[] encryptedMessage = readMessageFile(messageName, PATH);
		SecretKeySpec k = getSecretKey(keyName);
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, k);
		
		byte[] DecryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(DecryptedData, StandardCharsets.UTF_8);
		return message;
	}
	
	private SecretKeySpec getSecretKey(String keyName) throws Exception{
		byte[] key = FileReaderAndWritter.readKeyFile(keyName, PATH, KEY_EXTENSION);
		return new SecretKeySpec(key, "AES");
	}

	
}
