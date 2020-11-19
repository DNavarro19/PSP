package ej12;

import java.util.Random;

import ej11.Caja11;
import ej11.ModernSuperMarket;

public class Coche implements Runnable {

	Thread t;
	private Parking park;
	private int numCoche;
	private int turno;
	private Random rnd = new Random();

	public Coche(int num, Parking park) {
		this.park = park;
		this.numCoche = num;
		this.t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(rnd.nextInt(500));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cogerTurno();
		try {
			entrarAlParking();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(rnd.nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		salirDelParking();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized void entrarAlParking() throws InterruptedException {
		park.entrar(this.numCoche, this.turno);
	}

	private synchronized void salirDelParking() {
		park.salir(this.numCoche);
	}

	private synchronized void cogerTurno() {
		this.turno = park.getTurno();
		System.out.println("Coche número " + this.numCoche + " cogiendo el turno " + this.turno);
	}

}
