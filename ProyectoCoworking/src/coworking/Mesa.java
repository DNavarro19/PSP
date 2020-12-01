package coworking;

/**
 * Clase que simula la mesa en la que estaran el pc, las tarjetas y las personas
 */
public class Mesa {

	/**
	 * El array de tarjetas
	 */
	static Tarjeta[] tarjetas = new Tarjeta[5];

	/**
	 * El array de personas
	 */
	static Persona[] personas = new Persona[5];

	/**
	 * Metodo main que instancia el pc, las tarjetas y luego instancia las personas
	 * y las ejecuta
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		PC pc = new PC();

		for (int i = 0; i < tarjetas.length; i++) {
			tarjetas[i] = new Tarjeta(i);
		}

		for (int i = 0; i < personas.length - 1; i++) {
			personas[i] = new Persona(i, i, i + 1, pc);
		}
		personas[personas.length - 1] = new Persona(personas.length - 1, personas.length - 1, 0, pc);
	}

	/**
	 * Muestra un mensaje por consola
	 *
	 * @param s el mensaje
	 */
	public static synchronized void showMessage(String s) {
		System.out.println(s);
	}

	/**
	 * Metodo que devuelve la tarjeta que se solicita por parametros si no esta
	 * ocupada, si lo está, pone a esperar a la persona durante un tiempo, pasado
	 * ese tiempo si sigue sin estar disponible, la persona suelta la tarjeta que
	 * tenga en mano, si la tiene
	 *
	 * @param idTarjeta el id de la tarjeta que se solicita
	 * @param p         la persona que solicita la tarjeta
	 * @return la tarjeta que se solicita
	 * @throws InterruptedException InterruptedException
	 */
	public static Tarjeta cogerTarjeta(int idTarjeta, Persona p) throws InterruptedException {
		Tarjeta tar = tarjetas[idTarjeta];
		synchronized (tar) {
			while (tar.isOcupada()) {
				tar.wait(250);
				if (tar.isOcupada()) {
					p.soltarTarjeta();
				}
			}
			tar.setOcupada(true);
			tar.setPersona(p);
			Mesa.showMessage("Persona " + p.getIdPersona() + ": cogiendo tarjeta " + tar.getIdTarjeta());
			return tar;
		}
	}

	/**
	 * Metodo que suelta la tarjeta que se le pasa por parametros y despierta a las
	 * personas que les corresponderia intentar coger esta tarjeta
	 *
	 * @param tarjeta   la tarjeta a soltar
	 * @param idPersona la persona que suelta la tarjeta
	 */
	public static void soltarTarjeta(Tarjeta tarjeta, int idPersona) {
		Tarjeta tar = tarjeta;
		synchronized (tar) {
			tar.setOcupada(false);
			tar.setPersona(null);
			Mesa.showMessage("Persona " + idPersona + ": soltando tarjeta " + tar.getIdTarjeta());
			tar.notifyAll();
		}
	}
}
