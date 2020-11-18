package ej11;

public class Caja11 {

	private int numCaja;
	private boolean ocupado;
	public static int turno = 0;
	public static int siguiente = 0;

	public Caja11(int num) {
		this.numCaja = num;
	}

	public synchronized void cobrar(int cantidad) {
		Resultado11.resultado += cantidad;
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
	
	public static int getTurno() {
		++turno;
		return turno;
	}
}
