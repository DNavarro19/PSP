package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;
	final String usuario;

	// Constructor
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, String usu) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		this.usuario = usu;
	}

	@Override
	public void run() {
		String received;
		String toReturn;
		System.out.println("Comprobando hilo");
		// codigo que comprueba si tiene mensajes
		while (true) {
			try {
				dos.writeUTF("Hola " + this.usuario);
				dos.writeUTF("¿Desea leer sus mensajes?");
				received = dis.readUTF();
				if (received.equals("Exit")) {
					System.out.println("Cliente " + this.s + " desea salir...");
					System.out.println("Cerrando la conexion.");
					this.s.close();
					System.out.println("Conexion cerrada");
					break;
				}
				switch (received) {
				case "Hola":
					toReturn = "Que tal";
					dos.writeUTF(toReturn);
					break;
				case "Adios":
					toReturn = "Bye";
					dos.writeUTF(toReturn);
					break;
				default:
					break;
				}
				// codigo del mensaje
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			// cerrando streams
			this.dis.close();
			this.dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
