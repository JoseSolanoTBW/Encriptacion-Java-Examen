package com.encriptacion.common;

public class RandomBytes {

	public static byte[] generatedSequenceOfBytes(int keySize ) throws Exception {
		StringBuilder randomkey = new StringBuilder();
		for (int i = 0; i < keySize; i++) {
			randomkey.append(Integer.parseInt(Double.toString((Math.random() + 0.1) * 1000).substring(0, 2)));
		}
		return randomkey.toString().getBytes("UTF-8");
	}
}
