package coworking;

/**
 * Clase que simula el PC
 */
public class PC {

	/**
	 * El objeto mediante el que se sincronizara (monitor).
	 */
	private Object mutex;

	/**
	 * Atributo que indica si el PC esta ocupado o no
	 */
	private boolean ocupado;

	/**
	 * Constructor PC que establece el atributo ocupado a false, e instancia mutex
	 * como nuevo objeto
	 */
	public PC() {
		this.setOcupado(false);
		this.mutex = new Object();
	}

	/**
	 * Metodo que simula la entrada al PC estableciendo ocupado a true y mostrando
	 * un mensaje por pantalla
	 *
	 * @param idPersona el id de la persona que entra al PC
	 * @throws InterruptedException InterruptedException
	 */
	public void entrarAlPC(int idPersona) throws InterruptedException {
		synchronized (this.mutex) {
			while (ocupado) {
				this.mutex.wait();
			}
			Mesa.showMessage("Persona " + idPersona + ": usando el pc");
			this.setOcupado(true);
		}
	}

	/**
	 * Metodo que simula la salida del PC estableciendo ocupado a false y mostrando
	 * un mensaje por pantalla
	 *
	 * @param idPersona el id de la persona que sale del PC
	 */
	public void salirDelPC(int idPersona) {
		synchronized (this.mutex) {
			this.setOcupado(false);
			Mesa.showMessage("Persona " + idPersona + ": saliendo del pc");
			this.mutex.notifyAll();
		}
	}

	/**
	 * Devuelve el valor de ocupado
	 *
	 * @return true, si esta ocupado
	 */
	public boolean isOcupado() {
		return ocupado;
	}

	/**
	 * Establece el valor de ocupado
	 *
	 * @param ocupado el valor que se va a asignar a ocupado
	 */
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}
