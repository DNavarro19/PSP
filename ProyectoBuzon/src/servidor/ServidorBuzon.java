package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.ClientHandler;

public class ServidorBuzon {

	public static void main(String[] args) {
		try {
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			server.bind(addr);
			while (true) {
				Socket s = null;
				try {
					// socket object to receive incoming client requests
					s = server.accept();
					System.out.println("Un nuevo cliente está conectado: " + s);
					// obteniendo input y output streams
					DataInputStream dis = new DataInputStream(s.getInputStream());
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					System.out.println("Asignando nuevo hilo para el cliente");
					// creando un nuevo objeto hilo
					Thread t = new ClientHandler(s, dis, dos);
					// invocando metodo start() del hilo
					t.start();
					System.out.println("Hilo creado");
				} catch (Exception e) {
					s.close();
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
