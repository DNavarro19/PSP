package alquilerCoches;

import java.io.*;


/**
 * La Clase ProcessPadre.
 */
public class ProcessPadre {

	/**
	 * Comprueba si el proceso esta vivo
	 *
	 * @param p el proceso
	 * @return true, si el proceso esta vivo
	 */
	public static boolean isAlive(Process p) {
		// si el proceso hijo ha terminado devuelve falso, si no ha terminado lanza una
		// excepción y devuelve true
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

	/**
	 * Ejecuta un proceso
	 *
	 * @param command comando a ejecutar
	 * @throws Exception cualquier excepcion
	 */
	private static void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		pro.waitFor();
	}

	/**
	 * Metodo principal que comunica con un proceso hijo
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		runProcess("javac -cp src src/prueba/ProcessHijo.java");
		Process process = Runtime.getRuntime().exec("java -cp src prueba/ProcessHijo");
		InputStream out = process.getInputStream(); // configuramos la salida del proceso hijo
		OutputStream in = process.getOutputStream(); // configuramos la entrada del proceso hijo

		byte[] buffer = new byte[4000]; // buffer de comunicación entre procesos
		while (isAlive(process)) {
			// se comprueba el stream de salida del proceso hijo
			int no = out.available();
			if (no > 0) {
				// si el stream de salida del proceso hijo tiene información se muestra por
				// pantalla
				int n = out.read(buffer, 0, Math.min(no, buffer.length));
				System.out.println(new String(buffer, 0, n));
			}

			// se comprueba si hay información para enviar al proceso hijo
			int ni = System.in.available();
			if (ni > 0) {
				// si existe información se envía al proceso hijo
				int n = System.in.read(buffer, 0, Math.min(ni, buffer.length));
				in.write(buffer, 0, n);
				in.flush();
			}

			try {
				Thread.sleep(10); // se introduce un retardo de 10 milisegundos
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
}