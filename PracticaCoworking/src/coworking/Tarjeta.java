package coworking;

public class Tarjeta {

	private int idTarjeta;
	private boolean ocupada;

	public Tarjeta(int idTarjeta) {
		this.setIdTarjeta(idTarjeta);
		this.setOcupada(false);
	}

	public void cogerTarjeta(Persona p) throws InterruptedException {
		int espera = 0;
		synchronized (this) {
			while (this.ocupada) {
				this.wait(250);
				espera++;
				if (espera == 2 && this.ocupada) {
					if (p.getTarjetaIzquierda().equals(this)) {
						espera = 0;
						p.getTarjetaDerecha().soltarTarjeta();
					} else {
						espera = 0;
						p.getTarjetaIzquierda().soltarTarjeta();
					}
				}
			}
			this.setOcupada(true);
		}
	}

	public void soltarTarjeta() {
		synchronized (this) {
			this.setOcupada(false);
			this.notifyAll();
		}
	}

	public int getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
}
