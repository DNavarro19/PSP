package ej03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ReceptorEj03 {

	public static void main(String[] args) {
		try {
			System.out.println("Creando socket datagram");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			DatagramSocket socket = new DatagramSocket(addr);
			InetAddress addr2 = InetAddress.getByName("localhost");
			DatagramPacket datagram1;
			DatagramPacket datagram2;
			byte[] mensaje;
			System.out.println("Recibiendo mensaje");
			mensaje = new byte[25];
			datagram1 = new DatagramPacket(mensaje, 25);
			socket.receive(datagram1);
			System.out.println(new String(mensaje));

			System.out.println("Enviando mensaje");
			String message = "Que tal";
			datagram2 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr2, 5555);
			socket.send(datagram2);
			System.out.println("Mensaje enviado");

			System.out.println("Cerrando el socket datagram");
			socket.close();
			System.out.println("Terminando");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
