package ej10;

import java.util.ArrayList;

public class SuperMarket {
	static ArrayList<Caja10> cajas = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		Cliente10[] clientes = new Cliente10[5];
		for (int i = 0; i < 2; i++) {
			cajas.add(new Caja10(i + 1));
		}

		for (int i = 0; i < 5; i++) {
			clientes[i] = new Cliente10(i + 1);
		}
		
		Thread.sleep(1000);
		System.out.println(Resultado10.resultado);

		for (int i = 0; i < clientes.length; i++) {
			clientes[i].t.join();
		}
	}

	public static Caja10 asignarCaja() {
		int random = (int) (Math.random() * cajas.size());
		cajas.get(random).incrementarTurno();
		return cajas.get(random);
	}

}
