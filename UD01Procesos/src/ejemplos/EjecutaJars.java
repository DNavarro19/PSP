package ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EjecutaJars {

	public static void main(String[] command) {
		// TODO Auto-generated method stub
		String line;
		String txt = "";
		Scanner escaner = new Scanner(System.in);
		ProcessBuilder pb = new ProcessBuilder(command);
		pb.redirectErrorStream(true);
		try {
			while (!txt.equalsIgnoreCase("fin")) {
				Process process = pb.start();
				InputStream is = process.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "850"));
				System.out.println("La salida del proceso hijo:");
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
				System.out.println("Introduzca frase");
				txt = escaner.nextLine();
			}

		} catch (IOException e) {
			System.out.println("Error ocurrió ejecutando el comando. Descripción: " + e.getMessage());
		}
	}

}
