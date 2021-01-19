package archivador;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase auxiliar que organiza las interacciones con el HashMap
 *
 * @param <K> el tipo de la clave
 * @param <V> el tipo del valor
 */
public class DuplicateMap<K, V> {

	/** El HashMap creado para guardar los mensajes */
	private HashMap<K, ArrayList<V>> m = new HashMap<>();

	/**
	 * Inserta un mensaje en el arraylist si este ya existe, si no, lo crea e
	 * inserta el mensaje
	 *
	 * @param k the k
	 * @param v the v
	 */
	public void put(K k, V v) {
		if (m.containsKey(k)) {
			m.get(k).add(v);
		} else {
			ArrayList<V> arr = new ArrayList<>();
			arr.add(v);
			m.put(k, arr);
		}
	}

	/**
	 * Devuelve el array de mensajes de un usuario
	 *
	 * @param k la clave
	 * @return el arrayList de mensajes
	 */
	public ArrayList<V> get(K k) {
		return m.get(k);
	}

	/**
	 * Borra los mensajes de un usuario
	 *
	 * @param k la clave
	 */
	public void remove(K k) {
		m.remove(k);
	}

	/**
	 * Devuelve un mensaje en concreto
	 *
	 * @param k     la clave
	 * @param index la posicion del mensaje en el arrayList
	 * @return el mensaje
	 */
	public V get(K k, int index) {
		return m.get(k).size() - 1 < index ? null : m.get(k).get(index);
	}
}