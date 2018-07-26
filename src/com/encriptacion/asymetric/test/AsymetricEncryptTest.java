package com.encriptacion.asymetric.test;


import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.encriptacion.common.EncryptType;
import com.encriptacion.controller.EncryptionController;


public class AsymetricEncryptTest {
	
	static EncryptionController controllerEncryption;
		
	@BeforeClass
	public static void init() {
		controllerEncryption =  new EncryptionController();
	}
		
	@Test
	public void ProbarEncryption() throws Exception {
		String expected = "Esto es una prueba Examen2";
		controllerEncryption.createKey(EncryptType.Asymetric, "Examen2");
		controllerEncryption.encryptMessage(EncryptType.Asymetric,"Examen Title", expected, "Examen2");
		String respuesta = controllerEncryption.decryptMessage(EncryptType.Asymetric,"Examen Title", "Examen2");
		assertEquals(expected, respuesta);
	}
	
	@Test
	public void MismaDimesionMsj() throws Exception {
		String expected = "Dimesion Mensaje";
		controllerEncryption.createKey(EncryptType.Asymetric, "Test 2 Asyn");
		controllerEncryption.encryptMessage(EncryptType.Asymetric,"Title Asyn", expected, "Test 2 Asyn");
		String respuesta = controllerEncryption.decryptMessage(EncryptType.Asymetric,"Title Asyn", "Test 2 Asyn");
		assertEquals(expected.length(), respuesta.length());
	}

}
