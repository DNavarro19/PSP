package ej01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteEj01 {	

	public static void main(String[] args) throws IOException, InterruptedException {
		File file = new File("C:\\Users\\davil\\git\\PSP\\UD03Procesos\\src\\ej01\\fichero.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		try {
			System.out.println("Creando socket cliente");
			Socket clienteSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clienteSocket.connect(addr);
			InputStream is = clienteSocket.getInputStream();
			OutputStream os = clienteSocket.getOutputStream();
			System.out.println("Enviando mensaje");
			while (br.ready()) {
				String linea = br.readLine();
				os.write(linea.getBytes());
				Thread.sleep(25);
			}
			br.close();
			fr.close();
			os.close();
			clienteSocket.close();
			System.out.println("Mensaje enviado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
