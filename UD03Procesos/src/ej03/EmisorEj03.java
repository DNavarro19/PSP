package ej03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class EmisorEj03 {

	public static void main(String[] args) {
		try {
			System.out.println("Creando socket datagram");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			InetAddress addr2 = InetAddress.getByName("localhost");
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket datagram1;
			DatagramPacket datagram2;
			byte[] message;

			System.out.println("Enviando mensaje");
			String mensaje = "Hola";
			datagram1 = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, addr2, 5555);
			socket.send(datagram1);
			System.out.println("Mensaje enviado");
			message = new byte[25];
			datagram2 = new DatagramPacket(message, 25, addr);
			socket.receive(datagram2);
			System.out.println(new String(message));
			System.out.println("Cerrando socket datagram");
			socket.close();
			System.out.println("Terminando");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
