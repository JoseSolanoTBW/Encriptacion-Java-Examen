package com.encriptacion.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.encriptacion.common.EncryptType;
import com.encriptacion.controller.EncryptionController;


public class EncriptionClient {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static EncryptionController encryptionController = new EncryptionController();
	
	public static void main(String[] args) throws Exception {
		startApp();
	}
	
	public static void startApp() throws Exception {
		int option = 0;
		do {
    		System.out.println("Select a encryption method");
        	System.out.println("1. Symetric Encript");
        	System.out.println("2. Asymetric Encript");
        	System.out.println("3. Data Encript DES");
        	System.out.println("4. Exit");
        	option = Integer.parseInt(br.readLine());
        	if (option >= 1 && option <= 3){
        		executeAction(option);
        	}
    	} while (option != 4);
	}
	
	private static void executeAction(int option) throws Exception {
		switch (option) {
		case 1:
			showEncryptionersMenu(EncryptType.Symetric);
		break;
			
		case 2:
			showEncryptionersMenu(EncryptType.Asymetric);
		break;
		
		case 3: 
			showEncryptionersMenu(EncryptType.DataEcryptStandard);
		break;
		
		}
	}

	private static void showEncryptionersMenu(EncryptType encrypType) throws Exception {
		int option = 0;
		do {
    		System.out.println("1.Create key");
        	System.out.println("2.Encript Message");
        	System.out.println("3.Decrypt Message");
        	System.out.println("4.Exit ");
        	option = Integer.parseInt(br.readLine());
        	if (option >= 1 && option <= 3){
        		executeEncryptionAction(option, encrypType);
        	}
    	} while (option != 4);		
	}

	private static void executeEncryptionAction(int option, EncryptType encrypType) throws Exception {
		switch (option) {
		case 1:
			createKey(encrypType);
			break;
		case 2:
			encryptMessage(encrypType);
			break;
		case 3:
			decryptMessage(encrypType);
			break;
		default:
			decryptMessage(encrypType);
			break;
		}
	}

	private static void decryptMessage(EncryptType encrypType) throws Exception {
		System.out.println("Key name: ");
		String keyName = br.readLine();
		System.out.println("Message name: ");
		String messageName = br.readLine();
		encryptionController.decryptMessage(encrypType, messageName, keyName);
	}

	private static void encryptMessage(EncryptType encrypType) throws Exception {
		System.out.println("Key name: ");
		String name = br.readLine();
		System.out.println("Message name: ");
		String messageName = br.readLine();
		System.out.println("Message: ");
		String message = br.readLine();
		encryptionController.encryptMessage(encrypType, messageName, message, name);
	}

	private static void createKey(EncryptType encrypType) throws Exception {
		System.out.println("Key name: ");
		String name = br.readLine();
		encryptionController.createKey(encrypType, name);		
	}
}
