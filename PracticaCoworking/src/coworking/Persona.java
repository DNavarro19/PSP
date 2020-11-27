package coworking;

import java.util.Random;

public class Persona implements Runnable {

	Thread t;
	private int idPersona;
	private Tarjeta tarjetaIzquierda;
	private Tarjeta tarjetaDerecha;
	private PC pc;
	private Random rnd = new Random();

	public Persona(int idPersona, Tarjeta izquierda, Tarjeta derecha, PC pc) {
		this.setIdPersona(idPersona);
		this.setTarjetaIzquierda(izquierda);
		this.setTarjetaDerecha(derecha);
		this.setPc(pc);
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		System.out.println("Persona " + this.idPersona + " sentandose");
		try {
			Mesa.showMessage("Persona " + this.idPersona + " pensando");
			Thread.sleep(rnd.nextInt(500));
			Mesa.showMessage("Cogiendo tarjetas " + idPersona);
			this.cogerTarjetas();
			Mesa.showMessage("Tarjetas cogidas " + idPersona);
			Mesa.showMessage("Persona " + idPersona + " intentando entrar al pc");
			this.pc.entrarAlPC(idPersona);
			Thread.sleep(rnd.nextInt(500));
			this.pc.salirDelPC(idPersona);
			Mesa.showMessage("Persona " + idPersona + " soltando las tarjetas del pc");
			this.soltarTarjetas();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void cogerTarjetas() throws InterruptedException {
		if (!tarjetaIzquierda.isOcupada()) {
			tarjetaIzquierda.cogerTarjeta(this);
			tarjetaDerecha.cogerTarjeta(this);
		} else {
			tarjetaDerecha.cogerTarjeta(this);
			tarjetaIzquierda.cogerTarjeta(this);
		}
	}

	private void soltarTarjetas() {
		tarjetaIzquierda.soltarTarjeta();
		tarjetaDerecha.soltarTarjeta();
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public Tarjeta getTarjetaIzquierda() {
		return tarjetaIzquierda;
	}

	public void setTarjetaIzquierda(Tarjeta tarjetaIzquierda) {
		this.tarjetaIzquierda = tarjetaIzquierda;
	}

	public Tarjeta getTarjetaDerecha() {
		return tarjetaDerecha;
	}

	public void setTarjetaDerecha(Tarjeta tarjetaDerecha) {
		this.tarjetaDerecha = tarjetaDerecha;
	}

	public PC getPc() {
		return pc;
	}

	public void setPc(PC pc) {
		this.pc = pc;
	}
}
