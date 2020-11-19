package ej12;

public class ControlParking {

	public static void main(String[] args) {
		int numPlazas = 3;
		int numCoches = 7;
		int[] plazas = new int[numPlazas];
		for (int i = 0; i < plazas.length; i++) {
			plazas[i] = 0;
		}
		Parking park = new Parking(plazas);
		Coche[] coches = new Coche[numCoches];
		for (int i = 0; i < coches.length; i++) {
			coches[i] = new Coche(i + 1, park);
		}
	}

}
