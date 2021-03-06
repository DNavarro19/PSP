package ej10;

public class Caja10 {

	private int numCaja;
	private boolean ocupado;
	private int siguiente;
	private int turno;

	public Caja10(int num) {
		this.numCaja = num;
		this.ocupado = false;
		this.siguiente = 1;
		this.turno = 0;
	}

	public synchronized void entrar(int numCliente, int pago, int espera, int turno) {
		while (this.ocupado || turno != this.siguiente) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setOcupado(true);
		System.out.println("Soy el cliente " + numCliente + " entro a la caja " + this.numCaja);
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Soy el cliente " + numCliente + " pago " + pago);
		cobrar(pago);
	}

	public synchronized void cobrar(int cantidad) {
		Resultado10.resultado += cantidad;
		this.ocupado = false;
		siguiente++;
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

	public int getSiguiente() {
		return this.siguiente;
	}

	public void incrementarTurno() {
		turno++;
	}

	public int getTurno() {
		return this.turno;
	}
}
