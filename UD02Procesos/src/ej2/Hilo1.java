package ej2;

public class Hilo1 implements Runnable {
	public Thread t;

	public Hilo1() {
		t = new Thread(this,"Hola");
	}

	@Override
	public void run() {
		System.out.println("Hola desde el hilo 1 creado");
	}
}