package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import archivador.Buzon;

/**
 * La clase Servidor que acepta las conexiones de los clientes y se comunica con
 * ellos a traves de un hilo que lanza por cada cliente
 */
public class ServidorBuzon {

	/**
	 * Metodo pirncipal que establece el servidor en un puerto y acepta conexiones
	 * de los clientes que deseen conectarse, una vez la aceptan, lanzan un hilo al
	 * que le pasan los streams y el socket de ese cliente para comunicarse con el
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			Buzon buzon = new Buzon();
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			server.bind(addr);
			while (true) {
				Socket s = null;
				try {
					// objeto socket para recibir solicitudes de clientes entrantes
					s = server.accept();
					System.out.println("Un nuevo cliente est� conectado: " + s);
					// obteniendo input y output streams
					DataInputStream dis = new DataInputStream(s.getInputStream());
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					System.out.println("Asignando nuevo hilo para el cliente");
					// creando un nuevo objeto hilo
					Thread t = new ClientHandler(s, dis, dos, buzon);
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
