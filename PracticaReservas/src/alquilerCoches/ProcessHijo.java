package alquilerCoches;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * La Clase ProcessHijo.
 */
public class ProcessHijo {

	/**
	 * Valida que la cadena introducida es una fecha
	 *
	 * @param cadena String a validar
	 * @return true, si la cadena introducida es una fecha
	 */
	public static boolean validarFecha(String cadena) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			formatoFecha.setLenient(false);
			formatoFecha.parse(cadena);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo principal de alquiler de vehiculos que da como opciones a alquilar, el tipo de vehiculo, la gama, la fecha para recogerlo, 
	 * los dias que se necesitaria y si se desea hacer otro alquiler
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		String entrada = "";
		boolean exit = false;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		try {
			do {
				System.out.println("Bienvenido al alquiler de vehículos ¿Desea alquilar un vehiculo?");
				entrada = br.readLine();
				while (!entrada.equalsIgnoreCase("si") && !entrada.equalsIgnoreCase("no")) {
					System.out.println("No entiendo");
					entrada = br.readLine();
				}
				if (entrada.equalsIgnoreCase("si")) {

					System.out.println("Las gamas son estándar y premium ¿Cuál desea?");
					entrada = br.readLine();
					while (!validarGama(entrada)) {
						System.out.println("No disponemos de esa gama");
						entrada = br.readLine();
					}
					System.out.println("¿Qué tipo de vehiculo desea?");
					System.out.println("Moto, turismo o deportivo");
					entrada = br.readLine();
					while (!validarTipo(entrada)) {
						System.out.println("No disponemos de ese tipo");
						entrada = br.readLine();
					}
					System.out.println("¿Para que fecha lo necesitaría?");
					System.out.println("Formato: dd/mm/aaaa");
					entrada = br.readLine();
					while (!validarFecha(entrada)) {
						System.out.println("Fecha incorrecta");
						entrada = br.readLine();
					}
					System.out.println("¿Cuántos días necesitaría dicho vehículo?");
					entrada = br.readLine();
					while (!esNumero(entrada)) {
						System.out.println("Introduzca un numero por favor");
						entrada = br.readLine();
					}
					System.out.println("Alquiler exitoso\n");
					System.out.println("¿Desea hacer otro alquiler?");
					entrada = br.readLine();
					while (!entrada.equalsIgnoreCase("si") && !entrada.equalsIgnoreCase("no")) {
						System.out.println("¿Desea hacer otro alquiler?");
						entrada = br.readLine();
					}
					if (entrada.equalsIgnoreCase("si")) {
						exit = false;
					} else {
						exit = true;
					}
				} else {
					exit = true;
				}
			} while (!exit);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Valida si la cadena introducida es un numero
	 *
	 * @param cadena String a validar
	 * @return true, si la cadena introducida es un numero
	 */
	private static boolean esNumero(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Valida el tipo del vehiculo
	 *
	 * @param cadena tipo del vehiculo
	 * @return true, si el tipo elegido esta disponible
	 */
	private static boolean validarTipo(String cadena) {

		switch (cadena.toLowerCase()) {
		case "moto":
			return true;
		case "turismo":
			return true;
		case "deportivo":
			return true;
		default:
			return false;

		}
	}

	/**
	 * Valida la gama elegida
	 *
	 * @param cadena gama del vehiculo
	 * @return true, si la gama elegida esta disponible
	 */
	private static boolean validarGama(String cadena) {

		switch (cadena.toLowerCase()) {
		case "estandar":
			return true;
		case "premium":
			return true;
		default:
			return false;

		}
	}
}