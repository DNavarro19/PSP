package ej11;

import java.util.ArrayList;



public class ModernSuperMarket {
	static ArrayList<Caja11> cajas = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		Cliente11[] clientes = new Cliente11[5];
		for (int i = 0; i < 2; i++) {
			cajas.add(new Caja11(i + 1));
		}

		for (int i = 0; i < 5; i++) {
			clientes[i] = new Cliente11(i + 1);
		}

		for (int i = 0; i < clientes.length; i++) {
			clientes[i].start();
		}
		Thread.sleep(10000);
		System.out.println(Resultado11.resultado);
	}

	public synchronized static Caja11 asignarCaja() throws InterruptedException {
		boolean asignada = false;
		int inx = 0;
		do {
			for (int i = 0; i < cajas.size() && !asignada; i++) {
				if (!cajas.get(i).isOcupado()) {
					asignada = true;
					inx = i;
				}
			}
		} while (!asignada);
		return cajas.get(inx);
	}
}
