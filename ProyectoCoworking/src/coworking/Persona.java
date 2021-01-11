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
	/*
	 * Indica si es su turno para coger las tarjetas o no
	 */
	private boolean turno;
	/*
	 * El objeto que hara de monitor para esperar si no se tiene el turno
	 */
	private Object mutex;

	/*
	 * Numero de veces que la persona pensara e intentara usar el pc
	 */
	private int nVeces = 0;

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
	public Persona(int idPersona, int izquierda, int derecha, PC pc, boolean turno, Object mutex) {
		this.setTarjetaDerecha(null);
		this.setTarjetaIzquierda(null);
		this.setIdPersona(idPersona);
		this.setIdTarjetaIzquierda(izquierda);
		this.setIdTarjetaDerecha(derecha);
		this.setPc(pc);
		this.setTurno(turno);
		this.setMutex(mutex);
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
			while (nVeces < 5) {
				this.pensar();
				this.cogerTarjetas();
				this.entrarAlPC();
				this.salirDelPC();
				Mesa.showMessage("Persona " + idPersona +"nVeces: " + nVeces);
				nVeces++;
			}
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
		this.pc.salirDelPC(this.idPersona);
		this.soltarTarjetas();
		Mesa.pasarTurno(this.idPersona);
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
		synchronized (mutex) {
			while (!turno) {
				mutex.wait();
			}
			Mesa.showMessage("Persona " + idPersona + ": cogiendo tarjetas");
			tarjetaIzquierda = Mesa.cogerTarjeta(idTarjetaIzquierda, this);
			tarjetaDerecha = Mesa.cogerTarjeta(idTarjetaDerecha, this);
			Mesa.showMessage("Persona " + idPersona + ": tarjetas cogidas");
		}

	}

	/**
	 * Metodo que suelta las dos tarjetas
	 */
	private void soltarTarjetas() {
		this.soltarTarjetaIzquierda();
		this.soltarTarjetaDerecha();
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
	 * aleatorio entre 0 y 500 milisegundos (simula el pensamiento de la persona)
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

	/*
	 * Devuelve si es el turno de la persona o no
	 *
	 * @return true, si es el turno
	 */
	public boolean isTurno() {
		return turno;
	}

	/*
	 * Establece si es el turno de la persona o no
	 * 
	 * @param turno el valor que se le va a asignar al turno
	 */
	public void setTurno(boolean turno) {
		this.turno = turno;
	}

	/*
	 * Devuelve el objeto
	 * 
	 * @return mutex el objeto monitor
	 */
	public Object getMutex() {
		return mutex;
	}

	/*
	 * Establece el objeto
	 * 
	 * @param mutex el objeto monitor
	 */
	public void setMutex(Object mutex) {
		this.mutex = mutex;
	}

}
