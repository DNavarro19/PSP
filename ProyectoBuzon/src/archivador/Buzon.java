package archivador;

import java.util.ArrayList;

public class Buzon {

	private DuplicateMap<String, String> mensajes;

	public Buzon() {
		this.mensajes = new DuplicateMap<>();
	}

	public synchronized void enviarMensaje(String usuario, String mensaje) {
		mensajes.put(usuario, mensaje);
	}

	public synchronized ArrayList<String> verMensajes(String usuario) {
		return mensajes.get(usuario);
	}

	public DuplicateMap<String, String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(DuplicateMap<String, String> mensajes) {
		this.mensajes = mensajes;
	}

}
