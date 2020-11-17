package ej10;

import java.util.ArrayList;

public class SuperMarket {
	static ArrayList<Caja> cajas = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		Cliente[] clientes = new Cliente[5];
		for (int i = 0; i < 2; i++) {
			cajas.add(new Caja(i + 1));
		}

		for (int i = 0; i < 5; i++) {
			clientes[i] = new Cliente(i + 1);
		}

		for (int i = 0; i < clientes.length; i++) {
			clientes[i].start();
		}
		Thread.sleep(10000);
		System.out.println(Resultado.resultado);
	}

	public static Caja asignarCaja() {
		int random = (int) (Math.random() * cajas.size());
		return cajas.get(random);
	}

}
