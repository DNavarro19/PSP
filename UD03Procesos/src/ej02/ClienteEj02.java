package ej02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEj02 {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner teclado = new Scanner(System.in);
		String mensaje;
		byte[] message = new byte[40];
		try {
			System.out.println("Creando socket cliente");
			Socket clienteSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clienteSocket.connect(addr);
			InputStream is = clienteSocket.getInputStream();
			OutputStream os = clienteSocket.getOutputStream();
			System.out.println("Enviando mensaje");
			do {
				mensaje = teclado.nextLine();
				os.write(mensaje.getBytes());
				is.read(message);
				System.out.println("Mensaje recibido: " + new String(message).trim());
				message = new byte[40];
			} while (!mensaje.equalsIgnoreCase("cerrar"));
			os.close();
			clienteSocket.close();
			System.out.println("Cerrando");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
