package ej11;

import java.util.Random;

public class Cliente11 implements Runnable {
	private Caja11 caja;
	private int numCliente;
	private Random r = new Random();
	private int turno;
	Thread t;

	public Cliente11(int num) {
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
		try {
			turno = Caja11.getTurno();
			do {
				caja = ModernSuperMarket.asignarCaja(turno);
				if (caja.equals(null)) {
					wait();
				}
			} while (caja.equals(null));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Soy el cliente " + numCliente + " elijo la caja " + caja.getNumCaja());
		entrarACaja();

	}

	public void entrarACaja() {
		System.out.println("Soy el cliente " + numCliente + " entro a la caja " + caja.getNumCaja());
		try {
			Thread.sleep(r.nextInt(100));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pago = r.nextInt(50);
		System.out.println("Soy el cliente " + numCliente + " pago " + pago);
		caja.cobrar(pago);

	}

}
