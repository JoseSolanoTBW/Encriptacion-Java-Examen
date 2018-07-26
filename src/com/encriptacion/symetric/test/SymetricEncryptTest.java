package com.encriptacion.symetric.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.encriptacion.common.EncryptType;
import com.encriptacion.controller.EncryptionController;

public class SymetricEncryptTest {

	static EncryptionController controllerEncryption;
	
	@BeforeClass
	public static void init() {
		controllerEncryption =  new EncryptionController();
	}
		
	@Test
	public void ProbarEncryption() throws Exception {
		String expected = "Esto es una prueba Examen2";
		controllerEncryption.createKey(EncryptType.Symetric, "Examen2");
		controllerEncryption.encryptMessage(EncryptType.Symetric,"Examen Title", expected, "Examen2");
		String respuesta = controllerEncryption.decryptMessage(EncryptType.Symetric,"Examen Title", "Examen2");
		assertEquals(expected, respuesta);
	}
	
	@Test
	public void MismaDimesionMsj() throws Exception {
		String expected = "Dimesion Mensaje";
		controllerEncryption.createKey(EncryptType.Symetric, "Test 2 Syn");
		controllerEncryption.encryptMessage(EncryptType.Symetric,"Title Syn", expected, "Test 2 Syn");
		String respuesta = controllerEncryption.decryptMessage(EncryptType.Symetric,"Title Syn", "Test 2 Syn");
		assertEquals(expected.length(), respuesta.length());
	}

}
