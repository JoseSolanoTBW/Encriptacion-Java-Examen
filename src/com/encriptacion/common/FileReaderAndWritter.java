package com.encriptacion.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class FileReaderAndWritter {

	public static Key readKeyFromFile(String keyFileName, String type, String path, String keyExtension)
			throws IOException {
		InputStream in = new FileInputStream(path + keyFileName + type + keyExtension);
		ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));
		try {
			BigInteger m = (BigInteger) oin.readObject();
			BigInteger e = (BigInteger) oin.readObject();
			if (type.equals("public")) {
				RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				PublicKey pubKey = fact.generatePublic(keySpec);
				return pubKey;
			} else {
				RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				PrivateKey pubKey = fact.generatePrivate(keySpec);
				return pubKey;
			}
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialisation error", e);
		} finally {
			oin.close();
		}
	}

	public static void writeBytesFile(String name, byte[] content, String type, String path)
			throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(path + name + type);
		fos.write(content);
		fos.close();
	}

	public static void saveToFile(String fileName, BigInteger mod, BigInteger exp) throws IOException {
		ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		try {
			oout.writeObject(mod);
			oout.writeObject(exp);
		} catch (Exception e) {
			throw new IOException("Unexpected error", e);
		} finally {
			oout.close();
		}
	}

	public static byte[] readKeyFile(String keyName, String path, String keyExtension)
			throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(path + keyName + keyExtension));
		String everything = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			everything = sb.toString();
		} finally {
			br.close();
		}
		return everything.getBytes(StandardCharsets.UTF_8);
	}

}
