package archivador;

import java.util.ArrayList;

/**
 * La clase Buzon que guarda los mensajes de los clientes
 */
public class Buzon {

	/**
	 * Los mensajes de los clientes guardados en un DuplicateMap que no es mas que
	 * un HashMap
	 */
	private DuplicateMap<String, String> mensajes;

	/**
	 * Instacia un buzon
	 */
	public Buzon() {
		this.mensajes = new DuplicateMap<>();
	}

	/**
	 * Envia un mensaje a otro cliente y lo guarda en el DuplicateMap
	 *
	 * @param usuario el cliente al que se le va a enviar el mensaje
	 * @param mensaje el contenido del mensaje
	 */
	public synchronized void enviarMensaje(String usuario, String mensaje) {
		mensajes.put(usuario, mensaje);
	}

	/**
	 * Devuelve los mensajes del cliente correspondiente
	 *
	 * @param usuario el cliente que pide sus mensajes
	 * @return el arrayList de mensajes del cliente
	 */
	public synchronized ArrayList<String> verMensajes(String usuario) {
		return mensajes.get(usuario);
	}

	/**
	 * Borra los mensajes ya leidos del cliente
	 *
	 * @param usuario el cliente que va a borrar sus mensajes
	 */
	public synchronized void borrarMensajes(String usuario) {
		mensajes.remove(usuario);
	}

	/**
	 * Devuelve el DuplicateMap que contiene todos los mensajes
	 *
	 * @return el DuplicateMap
	 */
	public DuplicateMap<String, String> getMensajes() {
		return mensajes;
	}

	/**
	 * Establece el DuplicateMap que contiene los mensajes
	 *
	 * @param mensajes el DuplicateMap
	 */
	public void setMensajes(DuplicateMap<String, String> mensajes) {
		this.mensajes = mensajes;
	}

}
