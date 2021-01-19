package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * La clase Cliente que se comunica con el servidor
 */
public class ClienteBuzon {

	/**
	 * Metodo principal que establece la conexion con el servidor y se comunica con
	 * el
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) {
		try {
			Scanner scn = new Scanner(System.in);
			InetAddress ip = InetAddress.getByName("localhost");
			Socket clienteSocket = new Socket(ip, 5555);
			DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
			DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
			System.out.println("Creando socket cliente");
			System.out.println("Estableciendo la conexión");
			String mensaje;
			while (true) {
				do {
					String received = dis.readUTF();
					System.out.println(received);
				} while (dis.available() > 0);
				mensaje = scn.nextLine();
				dos.writeUTF(mensaje);
				dos.flush();
				if (mensaje.equals("3")) {
					System.out.println("Closing this connection : " + clienteSocket);
					scn.close();
					clienteSocket.close();
					System.out.println("Connection closed");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
