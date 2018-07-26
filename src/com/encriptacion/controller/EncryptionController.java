package com.encriptacion.controller;

import com.encriptacion.common.EncryptType;
import com.encriptacion.factory.EncryptFactory;
import com.encriptacion.factory.Encryptioner;

public class EncryptionController {

	private Encryptioner encryptionManager;
	
	public void createKey(EncryptType encryptionType, String keyName) throws Exception {
		encryptionManager = EncryptFactory.getEncriptioner(encryptionType);
		encryptionManager.createKey(keyName);
	}
	
	public void encryptMessage(EncryptType encryptionType, String messageName, String message, String name) throws Exception {
		encryptionManager = EncryptFactory.getEncriptioner(encryptionType);
		encryptionManager.encryptMessage(messageName, message, name);
	}
	
	public String decryptMessage(EncryptType encryptionType, String messageName,String name) throws Exception {
		encryptionManager = EncryptFactory.getEncriptioner(encryptionType);
		return encryptionManager.decryptMessage(messageName, name);
	}
}
