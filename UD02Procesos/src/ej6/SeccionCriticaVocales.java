package ej6;

import java.io.FileNotFoundException;

import acumulador.Acumula;

public class SeccionCriticaVocales {

	public static void main(String[] args) throws FileNotFoundException {

		char[] vocales = { 'a', 'e', 'i', 'o', 'u' };
		ContadorVocal[] hilos = new ContadorVocal[vocales.length];

		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new ContadorVocal(vocales[i]);
			hilos[i].run();
		}

		System.out.println(Acumula.acumulador);

	}

}
