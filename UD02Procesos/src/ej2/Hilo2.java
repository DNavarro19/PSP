package ej2;

public class Hilo2 implements Runnable {
	public Thread t;

	public Hilo2() {
		t = new Thread(this,"Hola");
	}

	@Override
	public void run() {
		System.out.println("Hola dede el hilo 2 creado");
	}
}