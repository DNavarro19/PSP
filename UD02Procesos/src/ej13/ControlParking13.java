package ej13;

import ej12.Coche;
import ej12.Parking;

public class ControlParking13 {

	public static void main(String[] args) throws InterruptedException {
		int numPlazas = 5;
		int numVehiculos = 7;
		int[] plazas = new int[numPlazas];
		for (int i = 0; i < plazas.length; i++) {
			plazas[i] = 0;
		}
		Parking13 park = new Parking13(plazas);
		Coche13[] coches = new Coche13[numVehiculos];
		Camion13[] camiones = new Camion13[numVehiculos];
		for (int i = 0; i < numVehiculos; i++) {
			coches[i] = new Coche13(i + 1, park);
			camiones[i] = new Camion13(i + 1, park);
		}
		for (int i = 0; i < numVehiculos; i++) {
			coches[i].t.join();
			camiones[i].t.join();
		}
	}
}
