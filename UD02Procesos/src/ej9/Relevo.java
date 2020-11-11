package ej9;

public class Relevo {
	private boolean enMano;

	public Relevo() {
		enMano = false;
	}

	public synchronized void coger() throws InterruptedException {
		if (enMano) {
			wait();
		} else {
			enMano = true;
		}
	}

	public synchronized void dar() {
		enMano = false;
		notify();
	}
}
