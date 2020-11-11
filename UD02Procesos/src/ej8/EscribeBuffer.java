package ej8;

public class EscribeBuffer extends Thread {

	private Buffer buf;

	public EscribeBuffer(Buffer buffer) {
		this.buf = buffer;
	}

	public void run() {
		escribe();
	}

	private synchronized void escribe() {
		try {
			buf.incrementa();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = 0;
		for (int i = 0; i < buf.buffer.length; i++) {
			buf.buffer[i] = aux;
		}
		aux++;
		buf.incrementado();
	}
}
