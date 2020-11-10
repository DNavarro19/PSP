package ej1;

public class Fibonacci implements Runnable {

	Thread t;
	private int n;

	Fibonacci(int n) {
		t = new Thread(this);
		this.n = n;
		System.out.println("Creado hilo: " + t);
		t.start(); // arranca el nuevo hilo de ejecución y ejecuta el método run
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hola dede el hilo creado");
		for (int i = 1; i <= this.n; i++) {
			System.out.println(fibonacci(i));
		}
		System.out.println("Hilo finalizando");

	}

	private int fibonacci(int n) {
		if (n > 1) {
			return fibonacci(n - 1) + fibonacci(n - 2); // función recursiva
		} else if (n == 1) { // caso base
			return 1;
		} else if (n == 0) { // caso base
			return 0;
		} else { // error
			System.out.println("Debes ingresar un tamaño mayor o igual a 1");
			return -1;
		}
	}
}