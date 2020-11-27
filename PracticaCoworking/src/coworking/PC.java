package coworking;

public class PC {

	private boolean ocupado;

	public PC() {
		this.setOcupado(false);
	}
	
	public void entrarAlPC(int idPersona) throws InterruptedException {
		synchronized (this) {
			while (ocupado) {
				this.wait();
			}
			Mesa.showMessage("Persona " + idPersona + " entrando al pc");
			this.setOcupado(true);
			
		}
	}
	
	public void salirDelPC(int idPersona) {
		synchronized (this) {
		this.setOcupado(false);
		Mesa.showMessage("Persona " + idPersona + " saliendo al pc");
		this.notifyAll();
		}
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}
