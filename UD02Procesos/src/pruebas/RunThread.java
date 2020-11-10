package pruebas;

public class RunThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HelloThread().start();
		System.out.println("Hola desde el hilo principal");
		System.out.println("Proceso acabando");
	}

}
