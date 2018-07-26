package com.encriptacion.factory;

import com.encriptacion.asymetric.AsymetricEncrypt;
import com.encriptacion.common.EncryptType;
import com.encriptacion.symetric.SymetricEncrypt;

public class EncryptFactory {

	private static Encryptioner getEncriptioner(EncryptType encriptionMethod) {
		switch (encriptionMethod) {
		case Asymetric:
			return new AsymetricEncrypt();
		case Symetric:
			return new SymetricEncrypt();
		default:
			return new SymetricEncrypt();
		}
	}
}
