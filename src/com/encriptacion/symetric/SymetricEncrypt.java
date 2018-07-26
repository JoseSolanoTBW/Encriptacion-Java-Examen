package com.encriptacion.symetric;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.encriptacion.common.FileReaderAndWritter;
import com.encriptacion.factory.Encryptioner;

public class SymetricEncrypt extends Encryptioner {

	private final int KEYSIZE = 8;
	private final String PATH = "C:/encrypt/symetric/";

	public void createKey(String name) throws Exception {
		byte[] key = generatedSequenceOfBytes();
		FileReaderAndWritter.writeBytesFile(name, key, KEY_EXTENSION, PATH);
	}

	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		byte[] key = FileReaderAndWritter.readKeyFile(keyName, PATH, KEY_EXTENSION);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		Encoder oneEncoder = Base64.getEncoder();
		encryptedData = oneEncoder.encode(encryptedData);
		FileReaderAndWritter.writeBytesFile(messageName, encryptedData, MESSAGE_ENCRYPT_EXTENSION, PATH);
	}

	public void decryptMessage(String messageName, String keyName) throws Exception {
		byte[] key = FileReaderAndWritter.readKeyFile(keyName, PATH, KEY_EXTENSION);
		byte[] encryptedMessage = readMessageFile(messageName, PATH);
		System.out.println(encryptedMessage.length);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, k);
		byte[] DecryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(DecryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: ");
		System.out.println(message);
	}

	private byte[] generatedSequenceOfBytes() throws Exception {
		StringBuilder randomkey = new StringBuilder();
		for (int i = 0; i < KEYSIZE; i++) {
			randomkey.append(Integer.parseInt(Double.toString((Math.random() + 0.1) * 1000).substring(0, 2)));
		}
		return randomkey.toString().getBytes("UTF-8");
	}
}
