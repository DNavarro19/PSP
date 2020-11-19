package ej13;

public class Parking13 {
	private int turnoCoche;
	private int turnoCamion;
	private int siguienteCoche;
	private int siguienteCamion;
	public int[] plazas;
	private boolean completo;

	public Parking13(int[] plazas) {
		this.plazas = plazas;
		this.siguienteCoche = 1;
		this.siguienteCamion = 1;
		this.turnoCamion = 0;
		this.turnoCoche = 0;
		this.completo = false;
	}

	public synchronized int getTurnoCoche() {
		++this.turnoCoche;
		return this.turnoCoche;
	}

	public synchronized int getTurnoCamion() {
		++this.turnoCamion;
		return this.turnoCamion;
	}

	public synchronized void entrarCoche(int numCoche, int turnoCoche) throws InterruptedException {
		while (compruebaParkingCoche() || turnoCoche != this.siguienteCoche) {
			wait();
		}
		boolean asignado = false;
		for (int i = 0; !asignado && i < plazas.length; i++) {
			if (plazas[i] == 0) {
				asignado = true;
				siguienteCoche++;
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

	public synchronized void entrarCamion(int numCamion, int turnoCamion) throws InterruptedException {
		while (compruebaParkingCamion() || turnoCamion != this.siguienteCamion) {
			wait();
		}
		boolean asignado = false;
		for (int i = 0; !asignado && i < plazas.length; i++) {
			if (plazas[i] == 0) {
				asignado = true;
				siguienteCamion++;
				plazas[i] = numCamion;
				plazas[i + 1] = numCamion;
				System.out.println("Entrada: Camion " + numCamion + " aparca en " + i);
				System.out.println("Parking: ");
				int j;
				for (j = 0; j < plazas.length - 1; j++) {
					System.out.print("[" + plazas[j] + "] ");
				}
				System.out.println("[" + plazas[j] + "]");
			}
		}

	}

	public synchronized void salirCoche(int numCoche) {
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == numCoche) {
				plazas[i] = 0;
				this.completo = false;
				System.out.println("Salida: Coche " + numCoche + " sale del parking");
			}
		}
		notifyAll();
	}

	public synchronized void salirCamion(int numCamion) {
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == numCamion) {
				plazas[i] = 0;
				plazas[i + 1] = 0;
				this.completo = false;
				System.out.println("Salida: Camion " + numCamion + " sale del parking");
			}
		}
		notifyAll();
	}

	private boolean compruebaParkingCoche() {
		for (int i = 0; i < plazas.length; i++) {
			if (this.plazas[i] == 0) {
				return false;
			}
		}
		return true;
	}

	private boolean compruebaParkingCamion() {
		for (int i = 0; i < plazas.length - 1; i++) {
			if (this.plazas[i] == 0 && this.plazas[i + 1] == 0) {
				return false;
			}
		}
		return true;
	}
}
