package com.encriptacion.factory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public abstract class Encryptioner {
	
	protected final String KEY_EXTENSION = ".key";
	protected final String MESSAGE_ENCRYPT_EXTENSION = ".encript";
	
	public abstract void createKey(String name) throws Exception;
	
	public abstract void encryptMessage(String messageName, String message, String keyName) throws Exception;
	
	public abstract void decryptMessage(String messageName, String keyName) throws Exception;
	
	protected byte[] readMessageFile(String messageName, String path) throws Exception{
		File file = new File(path + messageName + MESSAGE_ENCRYPT_EXTENSION);
	    int length = (int) file.length();
	    BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
	    byte[] bytes = new byte[length];
	    reader.read(bytes, 0, length);
	    reader.close();
	    Decoder oneDecoder = Base64.getDecoder();
		return oneDecoder.decode(bytes);		
	}
}
