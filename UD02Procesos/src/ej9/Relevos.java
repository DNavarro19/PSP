package ej9;

public class Relevos {

	public static void main(String[] args) throws InterruptedException {
		Relevo rel = new Relevo();
		Corredor[] corredores = new Corredor[4];
		for (int i = 0; i < corredores.length; i++) {
			corredores[i] = new Corredor(rel, i+1);
		}
		System.out.println("Todos los hilos creados");
		System.out.println("Doy la salida");
		corredores[0].run();
		corredores[1].run();
		corredores[2].run();
		corredores[3].run();
		
		for (int i = 0; i < corredores.length; i++) {
			corredores[i].join();
		}
		
		System.out.println("Todos los corredores han finalizado");
	}

}
