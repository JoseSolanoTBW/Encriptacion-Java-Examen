package com.encriptacion.asymetric;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;

import com.encriptacion.common.FileReaderAndWritter;
import com.encriptacion.factory.Encryptioner;

public class AsymetricEncrypt extends Encryptioner {

	private final String PUBLIC = "public";
	private final String PRIVATE = "private";
	private final String PATH = "C:/encrypt/asymetric/";

	public void createKey(String name) throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		KeyFactory fact = KeyFactory.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.genKeyPair();
		RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(), RSAPublicKeySpec.class);
		RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(), RSAPrivateKeySpec.class);

		FileReaderAndWritter.saveToFile(PATH + name + "public.key", pub.getModulus(), pub.getPublicExponent());
		FileReaderAndWritter.saveToFile(PATH + name + "private.key", priv.getModulus(), priv.getPrivateExponent());
	}

	public void encryptMessage(String messageName, String message, String keyName) throws Exception {

		PublicKey pubKey = (PublicKey) FileReaderAndWritter.readKeyFromFile(keyName, PUBLIC, PATH, KEY_EXTENSION);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		Encoder oneEncoder = Base64.getEncoder();
		encryptedData = oneEncoder.encode(encryptedData);
		FileReaderAndWritter.writeBytesFile(messageName, encryptedData, MESSAGE_ENCRYPT_EXTENSION, PATH);
	}

	public void decryptMessage(String messageName, String keyName) throws Exception {
		PrivateKey privKey = (PrivateKey) FileReaderAndWritter.readKeyFromFile(keyName, PRIVATE, PATH, KEY_EXTENSION);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		byte[] encryptedMessage = readMessageFile(messageName, PATH);
		byte[] decryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(decryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: ");
		System.out.println(message);
	}

}
