package com.encriptacion.des.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.encriptacion.common.EncryptType;
import com.encriptacion.controller.EncryptionController;

public class DataEncryptStandardTest {

	static EncryptionController controllerEncryption;
	
	@BeforeClass
	public static void init() {
		controllerEncryption =  new EncryptionController();
	}
		
	@Test
	public void ProbarEncryption() throws Exception {
		String expected = "Esto es una prueba Examen2";
		controllerEncryption.createKey(EncryptType.DataEcryptStandard, "Examen2");
		controllerEncryption.encryptMessage(EncryptType.DataEcryptStandard,"Examen Title", expected, "Examen2");
		String respuesta = controllerEncryption.decryptMessage(EncryptType.DataEcryptStandard,"Examen Title", "Examen2");
		assertEquals(expected, respuesta);
	}
	
	@Test
	public void MismaDimesionMsj() throws Exception {
		String expected = "Dimesion Mensaje";
		controllerEncryption.createKey(EncryptType.DataEcryptStandard, "Test 2 DES");
		controllerEncryption.encryptMessage(EncryptType.DataEcryptStandard,"Title Syn", expected, "Test 2 DES");
		String respuesta = controllerEncryption.decryptMessage(EncryptType.DataEcryptStandard,"Title Syn", "Test 2 DES");
		assertEquals(expected.length(), respuesta.length());
	}

}
