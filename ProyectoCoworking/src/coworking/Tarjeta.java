package coworking;

/**
 * Clase que simula las tarjetas que dan acceso al PC
 */
public class Tarjeta {

	/**
	 * El id de la tarjeta
	 */
	private int idTarjeta;

	/**
	 * Atributo que indica si la tarjeta la tiene una persona o no
	 */
	private boolean ocupada;

	/**
	 * La persona que tiene cogida la tarjeta
	 */
	private Persona persona;

	/**
	 * Constructor que establece el idTarjeta al valor pasado por parametros y
	 * ocupada a false
	 *
	 * @param idTarjeta El id de la tarjeta
	 */
	public Tarjeta(int idTarjeta) {
		this.setIdTarjeta(idTarjeta);
		this.setOcupada(false);
	}

	/**
	 * Devuelve el id de la tarjetaa
	 *
	 * @return el id tarjeta
	 */
	public int getIdTarjeta() {
		return idTarjeta;
	}

	/**
	 * Establece el id de la tarjeta
	 *
	 * @param idTarjeta El nuevo id tarjeta
	 */
	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	/**
	 * Devuelve si la tarjeta esta o no ocupada
	 *
	 * @return true, si está ocupada
	 */
	public boolean isOcupada() {
		return ocupada;
	}

	/**
	 * Establece si la tarjeta esta ocupada o no
	 *
	 * @param ocupada el valor que se le va a asignar a ocupada
	 */
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	/**
	 * Devuelve la persona que tiene cogida la tarjeta
	 *
	 * @return la persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * Establece la persona que tiene cogida la persona
	 *
	 * @param persona la nueva persona
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
