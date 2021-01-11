
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServidorEj02 {

	public static void main(String[] args) throws InterruptedException {
		String dev = "";
		String mensaje;
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

			while (dev.compareTo("cerrar") != 0) {
				do {
					is.read(message);
					dev = dev.concat(" " + new String(message));
					dev = dev.trim();
					message = new byte[40];
					if (!dev.contains("?")) {
						mensaje = " ";
						os.write(mensaje.getBytes());
						os.flush();
					}
				} while (!(dev.contains("?") || dev.compareTo("cerrar") == 0));
				System.out.println(dev);
				if (dev.equalsIgnoreCase("¿Como te llamas?")) {
					mensaje = "Me llamo Ejercicio 2";
					os.write(mensaje.getBytes());
					os.flush();
					dev = "";
				} else if (dev.equalsIgnoreCase("¿Cuantas lineas de codigo tienes?")) {
					mensaje = "Tengo 73 lineas de codigo";
					os.write(mensaje.getBytes());
					os.flush();
					dev = "";
				} else {
					mensaje = "No he entendido la pregunta";
					os.write(mensaje.getBytes());
					os.flush();
					dev = "";
				}
			}
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
			System.out.println("Cerrando el nuevo servidor");
			server.close();
			System.out.println("Terminando");
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}