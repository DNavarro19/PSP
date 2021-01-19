package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import archivador.Buzon;

/**
 * Clase que hace de conector entre el servidor y el cliente
 */
public class ClientHandler extends Thread {

	/** El DataInputStream del socket del cliente */
	private final DataInputStream dis;

	/** El DataOutputStream del socket del cliente */
	private final DataOutputStream dos;

	/** El socket del cliente. */
	private final Socket s;

	/** El nombre de usuario que identifica al cliente */
	private String usuario;

	/** El buzon que guarda los mensajes del cliente */
	private Buzon buzon;

	/**
	 * Establece los atributos del hilo conforme a los parámetros que se introducen
	 *
	 * @param s     el socket del cliente
	 * @param dis   el DataInputStream del cliente
	 * @param dos   el DataOutputStream del cliente
	 * @param buzon el buzon
	 */
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, Buzon buzon) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		this.buzon = buzon;
	}

	/**
	 * El metodo run que se ejecutará al crear un objeto de esta clase
	 */
	@Override
	public void run() {
		boolean exit = false;
		String received;
		String toReturn;
		try {
			dos.writeUTF("¿Cual es su nombre de usuario?"); // Se le pregunta al cliente cual es su nombre de usuario
			this.usuario = this.dis.readUTF();
			dos.writeUTF("Hola " + this.usuario + "\n"); // Se saluda al cliente
			dos.flush();
			while (!exit) {
				mostrarMenu(); // Se muestran las diferentes opciones al cliente
				received = dis.readUTF(); // Se lee la opcion elegida por el cliente
				switch (received) {
				case "1": // Se le envian los mensajes correspondientes al cliente, si es que los tiene
					ArrayList<String> msg = buzon.verMensajes(usuario);
					if (msg == null) {
						toReturn = "No tiene mensajes\n";
						dos.writeUTF(toReturn);
					} else {
						for (int i = 0; i < msg.size(); i++) {
							dos.writeUTF(msg.get(i));
							dos.flush();
						}
						buzon.borrarMensajes(usuario);
					}
					break;
				case "2": // Se envia un mensaje a otro cliente
					toReturn = "Quien es el destinatario";
					dos.writeUTF(toReturn);
					String destin = dis.readUTF();
					toReturn = "Cual es el mensaje que desea enviar";
					dos.writeUTF(toReturn);
					String mensaje = dis.readUTF();
					if (destin.equals(usuario)) {
						mensaje += " - By: Yo";
					} else {
						mensaje += " - By: " + usuario;
					}
					buzon.enviarMensaje(destin, mensaje);
					toReturn = "Mensaje enviado\n";
					dos.writeUTF(toReturn);
					break;
				case "3": // Se cierra la conexion con el cliente
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// Se cierran los streams y la ejecución del hilo acaba
			this.dis.close();
			this.dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Muestra el menú de opciones que el cliente puede hacer
	 * 
	 * @throws IOException Señala si una excepción de entrada/salida ocurre
	 */
	private void mostrarMenu() throws IOException {
		dos.writeUTF("Selecciona una opcion");
		dos.writeUTF("(1) Leer mensajes");
		dos.writeUTF("(2) Escribir mensaje");
		dos.writeUTF("(3) Salir");
		dos.flush();
	}
}
