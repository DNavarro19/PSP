package ej13;

public class Parking13 {
	private int turnoCoche;
	private int turnoCamion;
	private int siguienteCoche;
	private int siguienteCamion;
	public int[] plazas;
	private int espera;

	public Parking13(int[] plazas) {
		this.plazas = plazas;
		this.siguienteCoche = 1;
		this.siguienteCamion = 1;
		this.turnoCamion = 0;
		this.turnoCoche = 0;
		this.espera = 0;
	}

	public synchronized int getTurnoCoche() {
		++this.turnoCoche;
		return this.turnoCoche;
	}

	public synchronized int getTurnoCamion() {
		++this.turnoCamion;
		return this.turnoCamion;
	}

	public synchronized void entrarCoche(int numCoche, int turnoCoche, String tipoV) throws InterruptedException {
		while (compruebaParking(tipoV, turnoCoche)) {
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

	public synchronized void entrarCamion(int numCamion, int turnoCamion, String tipoV) throws InterruptedException {
		while (compruebaParking(tipoV, turnoCamion)) {
			wait();
		}
		boolean asignado = false;
		for (int i = 0; !asignado && i < plazas.length - 1; i++) {
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
				System.out.println("Salida: Camion " + numCamion + " sale del parking");
			}
		}
		notifyAll();
	}

	private boolean compruebaParking(String tipoV, int turno) {
		if (tipoV.equalsIgnoreCase("camion")) { // comprueba si el vehiculo es un camion
			if (turno == this.siguienteCamion) { // comprueba si es el turno de este camion y si lo es busca si hay
													// plaza libre para él
				for (int i = 0; i < plazas.length - 1; i++) {
					if (this.plazas[i] == 0 && this.plazas[i + 1] == 0) { // si encuentra plaza, entra al parking
						return false;
					}
				}
				return true;
			} else { // si no es su turno se espera
				return true;
			}
		} else if (tipoV.equalsIgnoreCase("coche")) { // comprueba si el vehiculo es un coche
			if (turno == this.siguienteCoche) { // comprueba si es el turno de este coche
				if (this.siguienteCamion <= this.turnoCamion) { // si lo es, y hay camiones esperando, busca plaza
					for (int i = 0; i < plazas.length; i++) {
						if (this.plazas[i] == 0) { // si la encuentra y la espera todavia no llega al limite, sigue
													// esperando
							if (espera < 3) {
								espera++;
								return true;
							} else if (espera == 3) { // si la espera llega al límite, entra al parking
								espera = 0;
								return false;
							}
						}
					}
					return true; // si no encuentra, espera
				} else { // si no hay camiones, busca plaza para él
					for (int i = 0; i < plazas.length; i++) {
						if (this.plazas[i] == 0) { // si la encuentra, entra sin esperar ya que no hay camiones
							return false;
						}
					}
					return true; // si no la encuentra espera a que haya hueco
				}
			} else { // si no es su turno, se espera a que lo sea
				return true;
			}
		}
		return true;
	}
}
