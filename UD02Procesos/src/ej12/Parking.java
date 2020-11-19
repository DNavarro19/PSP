package ej12;

public class Parking {

	private int turno;
	private int siguiente;
	public int[] plazas;
	private boolean completo;

	public Parking(int[] plazas) {
		this.plazas = plazas;
		this.siguiente = 1;
		this.turno = 0;
		this.completo = false;
	}

	public synchronized int getTurno() {
		++this.turno;
		return this.turno;
	}

	public int getSiguiente() {
		return this.siguiente;
	}

	public synchronized void entrar(int numCoche, int turno) throws InterruptedException {
		while (compruebaParking() || turno != this.siguiente) {
			wait();
		}
		boolean asignado = false;
		for (int i = 0; !asignado && i < plazas.length; i++) {
			if (plazas[i] == 0) {
				asignado = true;
				siguiente++;
				plazas[i] = numCoche;
				System.out.println("Entrada: Coche " + numCoche + " aparca en " + i);
				System.out.println("Parking: ");
				int j;
				for (j = 0; j < plazas.length - 1; j++) {
					System.out.print("[" + plazas[j] + "] ");
				}
				System.out.println("[" + plazas[j] + "]");
			}
		}

	}

	public synchronized void salir(int numCoche) {
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == numCoche) {
				plazas[i] = 0;
				this.completo = false;
				System.out.println("Salida: Coche " + numCoche + " sale del parking");
			}
		}
		notifyAll();
	}

	private boolean compruebaParking() {
		for (int i = 0; i < plazas.length; i++) {
			if (this.plazas[i] == 0) {
				return false;
			}
		}
		return true;
	}
}
