package ej8;

public class Buffer {

	public static int[] buffer = new int[10000];
	public boolean leyendo;
	public boolean escribiendo;

	public synchronized void incrementa() throws InterruptedException {
		if (leyendo) {
			wait();
		} else {
			escribiendo = true;
		}
	}

	public synchronized void incrementado() {
		escribiendo = false;
		notify();
	}

	public synchronized void lee() throws InterruptedException {
		if (escribiendo) {
			wait();
		} else {
			leyendo = true;
		}
	}

	public synchronized void leido() {
		leyendo = false;
		notify();
	}
}
