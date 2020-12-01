package coworking;

import java.util.Random;

/**
 * Clase que simula las personas que piensan y usan el PC
 */
public class Persona implements Runnable {

	/*
	 * El hilo
	 */
	Thread t;

	/**
	 * El id de la persona
	 */
	private int idPersona;

	/**
	 * La tarjeta de su izquierda
	 */
	private Tarjeta tarjetaIzquierda;

	/**
	 * La tarjeta de su derecha
	 */
	private Tarjeta tarjetaDerecha;

	/**
	 * El id de la tarjeta de su izquierda
	 */
	private int idTarjetaIzquierda;

	/**
	 * El id de la tarjeta de su derecha
	 */
	private int idTarjetaDerecha;

	/**
	 * El PC que va a usar
	 */
	private PC pc;

	/**
	 * Para crear números aleatorios (para las esperas)
	 */
	private Random rnd = new Random();

	/**
	 * Establece los atributos de persona de acuerdo a los parámetro que se le
	 * introducen, exceptuando las tarjetas que se inician a nulo y el hilo, al cual
	 * se le pasa la propia clase
	 * 
	 * @param idPersona el id de la persona
	 * @param izquierda el id de la tarjeta izquierda
	 * @param derecha   el id de la tarjeta derecha
	 * @param pc        el pc
	 * 
	 */
	public Persona(int idPersona, int izquierda, int derecha, PC pc) {
		this.setTarjetaDerecha(null);
		this.setTarjetaIzquierda(null);
		this.setIdPersona(idPersona);
		this.setIdTarjetaIzquierda(izquierda);
		this.setIdTarjetaDerecha(derecha);
		this.setPc(pc);
		t = new Thread(this);
		t.start();
	}

	/**
	 * El metodo run que se ejecutará al crear un objeto de esta clase
	 */
	@Override
	public void run() {
		Mesa.showMessage("Persona " + idPersona + ": sentandose");
		try {
			this.pensar();
			this.cogerTarjetas();
			this.entrarAlPC();
			this.salirDelPC();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		Mesa.showMessage("Persona " + idPersona + ": saliendo");
	}

	/**
	 * Metodo que sale del PC y suelta las tarjetas
	 */
	private void salirDelPC() {
		this.pc.salirDelPC(idPersona);
		this.soltarTarjetas();
	}

	/**
	 * Metodo que entra al PC y despues lo usa durante el tiempo necesario
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	private void entrarAlPC() throws InterruptedException {
		Mesa.showMessage("Persona " + idPersona + ": intentando entrar al pc");
		this.pc.entrarAlPC(idPersona);
		Thread.sleep(rnd.nextInt(1000));
	}

	/**
	 * Metodo que coge las tarjetas, empezando por la izquierda
	 *
	 * @throws InterruptedException InterruptedException
	 */
	private void cogerTarjetas() throws InterruptedException {
		Mesa.showMessage("Persona " + idPersona + ": cogiendo tarjetas");
		while (tarjetaIzquierda == null || tarjetaDerecha == null) {
			if (tarjetaIzquierda == null) {
				tarjetaIzquierda = Mesa.cogerTarjeta(idTarjetaIzquierda, this);
			}
			if (tarjetaDerecha == null) {
				tarjetaDerecha = Mesa.cogerTarjeta(idTarjetaDerecha, this);
			}
		}
		Mesa.showMessage("Persona " + idPersona + ": tarjetas cogidas");
	}

	/**
	 * Metodo que suelta las dos tarjetas
	 */
	private void soltarTarjetas() {
		this.soltarTarjetaIzquierda();
		this.soltarTarjetaDerecha();
	}

	/**
	 * Suelta la/s tarjeta/s que tenga en ese momento
	 */
	public void soltarTarjeta() {
		if (this.tarjetaIzquierda != null) {
			this.soltarTarjetaIzquierda();
		}
		if (this.tarjetaDerecha != null) {
			this.soltarTarjetaDerecha();
		}
	}

	/**
	 * Suelta la tarjeta izquierda.
	 */
	public void soltarTarjetaIzquierda() {
		Mesa.soltarTarjeta(tarjetaIzquierda, idPersona);
		this.setTarjetaIzquierda(null);
	}

	/**
	 * Suelta la tarjeta derecha.
	 */
	public void soltarTarjetaDerecha() {
		Mesa.soltarTarjeta(tarjetaDerecha, idPersona);
		this.setTarjetaDerecha(null);
	}

	/**
	 * Metodo que muestra un mensaje por pantalla y duerme el hilo un tiempo
	 * aleatorio entre 0 y 500 milisegundos
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	private void pensar() throws InterruptedException {
		Mesa.showMessage("Persona " + this.idPersona + ": pensando");
		Thread.sleep(rnd.nextInt(500));
	}

	/**
	 * Devuelve el id de la persona
	 *
	 * @return el id persona
	 */
	public int getIdPersona() {
		return idPersona;
	}

	/**
	 * Establece el id de la persona
	 *
	 * @param idPersona el nuevo id persona
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * Devuelve la tarjeta izquierda
	 *
	 * @return la tarjeta izquierda
	 */
	public Tarjeta getTarjetaIzquierda() {
		return tarjetaIzquierda;
	}

	/**
	 * Establece la tarjeta izquierda
	 *
	 * @param tarjetaIzquierda la nueva tarjeta izquierda
	 */
	public void setTarjetaIzquierda(Tarjeta tarjetaIzquierda) {
		this.tarjetaIzquierda = tarjetaIzquierda;
	}

	/**
	 * Devuelve la tarjeta derecha
	 *
	 * @return la tarjeta derecha
	 */
	public Tarjeta getTarjetaDerecha() {
		return tarjetaDerecha;
	}

	/**
	 * Establece la tarjeta derecha
	 *
	 * @param tarjetaDerecha la nueva tarjeta derecha
	 */
	public void setTarjetaDerecha(Tarjeta tarjetaDerecha) {
		this.tarjetaDerecha = tarjetaDerecha;
	}

	/**
	 * Devuelve el id de la tarjeta izquierda.
	 *
	 * @return el id de la tarjeta izquierda
	 */
	public int getIdTarjetaIzquierda() {
		return idTarjetaIzquierda;
	}

	/**
	 * Establece el id de la tarjeta izquierda.
	 *
	 * @param idTarjetaIzquierda el nuevo id de la tarjeta izquierda
	 */
	public void setIdTarjetaIzquierda(int idTarjetaIzquierda) {
		this.idTarjetaIzquierda = idTarjetaIzquierda;
	}

	/**
	 * Devuelve el id de la tarjeta derecha
	 *
	 * @return el id de la tarjeta derecha
	 */
	public int getIdTarjetaDerecha() {
		return idTarjetaDerecha;
	}

	/**
	 * Sets the id tarjeta derecha.
	 *
	 * @param idTarjetaDerecha el nuevo id de la tarjeta derecha
	 */
	public void setIdTarjetaDerecha(int idTarjetaDerecha) {
		this.idTarjetaDerecha = idTarjetaDerecha;
	}

	/**
	 * Devuelve el pc
	 *
	 * @return el pc
	 */
	public PC getPc() {
		return pc;
	}

	/**
	 * Establece el pc
	 *
	 * @param pc el nuevo pc
	 */
	public void setPc(PC pc) {
		this.pc = pc;
	}
}
