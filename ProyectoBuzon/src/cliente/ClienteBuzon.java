package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBuzon {

	public static void main(String[] args) {
		try {
			Scanner scn = new Scanner(System.in);
			System.out.println("Creando socket cliente");
			System.out.println("Estableciendo la conexión");
			InetAddress ip = InetAddress.getByName("localhost");
			Socket clienteSocket = new Socket(ip, 5555);
			DataInputStream dis = new DataInputStream(clienteSocket.getInputStream());
			DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
			String mensaje;
			while (true) {
				System.out.println(dis.readUTF());
				String tosend = scn.nextLine();
				dos.writeUTF(tosend);
				// If client sends exit,close this connection
				// and then break from the while loop
				if (tosend.equals("Exit")) {
					System.out.println("Closing this connection : " + clienteSocket);
					clienteSocket.close();
					System.out.println("Connection closed");
					break;
				}
				// printing date or time as requested by client
				String received = dis.readUTF();
				System.out.println(received);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
