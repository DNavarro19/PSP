package ej10;

public class Caja {

	private int numCaja;
	private boolean ocupado;

	public Caja(int num) {
		this.numCaja = num;
	}

	public synchronized void cobrar(int cantidad) {
		Resultado.resultado += cantidad;
		this.ocupado = false;
		notifyAll();
	}

	public int getNumCaja() {
		return this.numCaja;
	}

	public boolean isOcupado() {
		return this.ocupado;
	}

	public void setOcupado(boolean oc) {
		this.ocupado = oc;
	}
}
