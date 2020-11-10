package ej6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import acumulador.Acumula;

public class ContadorVocal extends Thread {

	private char vocal;
	private File f;
	private FileReader fr;
	private BufferedReader br;

	public ContadorVocal(char vocal) throws FileNotFoundException {
		this.vocal = vocal;
		f = new File("./src/archivo.txt");
		fr = new FileReader(f);
		br = new BufferedReader(fr);
	}

	@Override
	public void run() {
		leeArchivo();
	}

	private void leeArchivo() {
		String linea;
		try {
			while ((linea = br.readLine()) != null) {
				linea = linea.toLowerCase();
				for (int i = 0; i < linea.length(); i++) {
					if (linea.charAt(i) == this.vocal) {
						sumaVocal();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void sumaVocal() {
		Acumula.acumulador++;
	}

}
