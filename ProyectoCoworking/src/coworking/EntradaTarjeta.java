package coworking;

public class EntradaTarjeta {
	Object tarjeta0 = new Object();
	Object tarjeta1 = new Object();
	Object tarjeta2 = new Object();
	Object tarjeta3 = new Object();
	Object tarjeta4 = new Object();
	
	public Tarjeta cogerTarjetaPrueba(int idTarjeta, Persona p) throws InterruptedException {
		Tarjeta tar = Mesa.cogerTarjeta(idTarjeta);
		int espera = 0;
			synchronized (tar) {
				while (tar.isOcupada()) {
					tar.wait(50);
					espera++;
					if (espera >= 2 && tar.isOcupada()) {
						p.soltarTarjeta();
						espera = 0;
					}
				}
				tar.setOcupada(true);
				tar.setPersona(p);
				Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
				return tar;
			}
		}
	
	public void soltarTarjetaPrueba(Tarjeta tarjeta, int idPersona) {
		Tarjeta tar = tarjeta;
			synchronized (tar) {
				tar.setOcupada(false);
				tar.setPersona(null);
				Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
				tar.notifyAll();
			}
		}
	public Tarjeta cogerTarjeta(int idTarjeta, Persona p) throws InterruptedException {
		Tarjeta tar = null;
		int espera = 0;
		switch (idTarjeta) {
		case 0:
			synchronized (tarjeta0) {
				while ((tar = Mesa.cogerTarjeta(idTarjeta)) == null) {
					tarjeta0.wait(100);
					espera++;
					if (espera >= 2 && Mesa.cogerTarjeta(idTarjeta) == null) {
						p.soltarTarjeta();
						espera = 0;
					}
				}
				tar.setOcupada(true);
				tar.setPersona(p);
				Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
				return tar;
			}
		case 1:
			synchronized (tarjeta1) {
				while ((tar = Mesa.cogerTarjeta(idTarjeta)) == null) {
					tarjeta1.wait(100);
					espera++;
					if (espera >= 2 && Mesa.cogerTarjeta(idTarjeta) == null) {
						p.soltarTarjeta();
						espera = 0;
					}
				}
				tar.setOcupada(true);
				tar.setPersona(p);
				Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
				return tar;

			}
		case 2:
			synchronized (tarjeta2) {
				while ((tar = Mesa.cogerTarjeta(idTarjeta)) == null) {
					tarjeta2.wait(100);
					espera++;
					if (espera >= 2 && Mesa.cogerTarjeta(idTarjeta) == null) {
						p.soltarTarjeta();
						espera = 0;
					}
				}
				tar.setOcupada(true);
				tar.setPersona(p);
				Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
				return tar;
			}
		case 3:
			synchronized (tarjeta3) {
				while ((tar = Mesa.cogerTarjeta(idTarjeta)) == null) {
					tarjeta3.wait(100);
					espera++;
					if (espera >= 2 && Mesa.cogerTarjeta(idTarjeta) == null) {
						p.soltarTarjeta();
						espera = 0;
					}
				}
				tar.setOcupada(true);
				tar.setPersona(p);
				Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
				return tar;
			}
		case 4:
			synchronized (tarjeta4) {
				while ((tar = Mesa.cogerTarjeta(idTarjeta)) == null) {
					tarjeta4.wait(100);
					espera++;
					if (espera >= 2 && Mesa.cogerTarjeta(idTarjeta) == null) {
						p.soltarTarjeta();
						espera = 0;
					}
				}
				tar.setOcupada(true);
				tar.setPersona(p);
				Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
				return tar;
			}
		default:
			return tar;
		}
	}

	public void soltarTarjeta(Tarjeta tar, int idPersona) {
		switch (tar.getIdTarjeta()) {
		case 0:
			synchronized (tarjeta0) {
				tar.setOcupada(false);
				tar.setPersona(null);
				Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
				tarjeta0.notifyAll();
			}
			break;
		case 1:
			synchronized (tarjeta1) {
				tar.setOcupada(false);
				tar.setPersona(null);
				Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
				tarjeta1.notifyAll();
			}
			break;
		case 2:
			synchronized (tarjeta2) {
				tar.setOcupada(false);
				tar.setPersona(null);
				Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
				tarjeta2.notifyAll();
			}
			break;
		case 3:
			synchronized (tarjeta3) {
				tar.setOcupada(false);
				tar.setPersona(null);
				Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
				tarjeta3.notifyAll();
			}
			break;
		case 4:
			synchronized (tarjeta4) {
				tar.setOcupada(false);
				tar.setPersona(null);
				Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
				tarjeta4.notifyAll();
			}
			break;

		default:
			break;
		}
	}

}
