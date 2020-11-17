package ej10;

import java.util.Random;

public class Cliente extends Thread {
	private Caja caja;
	private int numCliente;
	private Random r = new Random();

	public Cliente(int num) {
		this.numCliente = num;
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
		caja = SuperMarket.asignarCaja();
		System.out.println("Soy el cliente " + numCliente + " elijo la caja " + caja.getNumCaja());
		entrarACaja();

	}

	public synchronized void entrarACaja() {
		while (caja.isOcupado()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		caja.setOcupado(true);
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
