package pruebas;

public class HelloThread extends Thread {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hola desde el hilo creado");
		System.out.println("Hilo finalizado");
	}

}
