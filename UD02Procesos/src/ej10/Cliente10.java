package ej10;

import java.util.Random;

public class Cliente10 implements Runnable {
	private Caja10 caja;
	private int numCliente;
	private Random r = new Random();
	private int turno;
	Thread t;

	public Cliente10(int num) {
		this.numCliente = num;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		System.out.println("Soy el cliente " + numCliente + " entro al super");
		int espera = r.nextInt(100);
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		escogerCaja();
		entrarACaja();
	}

	public void entrarACaja() {
		int pago = r.nextInt(50);
		int espera = r.nextInt(100);
		caja.entrar(this.numCliente, pago, espera, this.turno);

	}

	private void escogerCaja() {
		caja = SuperMarket.asignarCaja();
		turno = caja.getTurno();
		System.out.println(
				"Soy el cliente " + numCliente + " elijo la caja " + caja.getNumCaja() + " con turno " + turno);
	}

}
