package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import archivador.Buzon;

public class ClientHandler extends Thread {
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;
	private String usuario;
	private Buzon buzon;

	// Constructor
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, Buzon buzon) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		this.buzon = buzon;
	}

	@Override
	public void run() {
		boolean exit = false;
		String received;
		String toReturn;
		// codigo que comprueba si tiene mensajes
		try {
			dos.writeUTF("�Cual es su nombre de usuario?");
			this.usuario = this.dis.readUTF();
			dos.writeUTF("Hola " + this.usuario);
			dos.flush();
			while (!exit) {
				mostrarMenu();
				received = dis.readUTF();
				switch (received) {
				case "1":
					ArrayList<String> msg = buzon.verMensajes(usuario);
					for (int i = 0; i < msg.size(); i++) {
						dos.writeUTF(msg.get(i));
						dos.flush();
					}
					break;
				case "2":
					toReturn = "Quien es el destinatario";
					dos.writeUTF(toReturn);
					String destin = dis.readUTF();
					toReturn = "Cual es el mensaje que desea enviar";
					dos.writeUTF(toReturn);
					String mensaje = dis.readUTF();
					buzon.enviarMensaje(destin, mensaje);
					break;
				case "3":
					System.out.println("Cliente " + this.s + " desea salir...");
					System.out.println("Cerrando la conexion.");
					this.s.close();
					System.out.println("Conexion cerrada");
					exit = true;
					break;
				default:
					toReturn = "Escoja una opcion valida";
					dos.writeUTF(toReturn);
					break;
				}
			}
			// codigo del mensaje
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// cerrando streams
			this.dis.close();
			this.dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void mostrarMenu() throws IOException {
		dos.writeUTF("Selecciona una opcion");
		dos.writeUTF("(1) Leer mensajes");
		dos.writeUTF("(2) Escribir mensaje");
		dos.writeUTF("(3) Salir");
		dos.flush();
	}
}
