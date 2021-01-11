package ej01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEj01 {

	public static void main(String[] args) throws IOException {
		FileWriter file = new FileWriter("C:\\Users\\davil\\git\\PSP\\UD03Procesos\\src\\ej01\\fichero2.txt");
		PrintWriter pw = new PrintWriter(file);
		BufferedWriter bw = new BufferedWriter(pw);
		try {
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();
			
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			server.bind(addr);
			
			System.out.println("Aceptando conexiones");
			Socket newSocket = server.accept();
			
			System.out.println("Conexión recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			byte[] message = new byte[40];
			while (is.read(message) != -1) {
				System.out.println("Mensaje recibido: " + new String(message));
				pw.println(new String(message).trim());
				message = new byte[40];
			}
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
			System.out.println("Cerrando el nuevo servidor");
			server.close();
			System.out.println("Terminando");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
