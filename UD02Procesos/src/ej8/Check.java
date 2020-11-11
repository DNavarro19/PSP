package ej8;

import java.nio.IntBuffer;

public class Check {

	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		EscribeBuffer escribe = new EscribeBuffer(buffer);
		LeeBuffer lee = new LeeBuffer(buffer);
		escribe.start();
		lee.start();
		
		while(lee.isCorr()) {
			escribe.run();
			lee.run();
		}
	}

}
