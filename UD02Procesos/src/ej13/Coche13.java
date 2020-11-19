package ej13;

import java.util.Random;

public class Coche13 implements Runnable {

	Thread t;
	private Parking13 park;
	private int numCoche;
	private int turno;
	private Random rnd = new Random();

	public Coche13(int num, Parking13 park) {
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
	}

	private synchronized void entrarAlParking() throws InterruptedException {
		park.entrarCoche(this.numCoche, this.turno);
	}

	private synchronized void salirDelParking() {
		park.salirCoche(this.numCoche);
	}

	private synchronized void cogerTurno() {
		this.turno = park.getTurnoCoche();
		System.out.println("Coche número " + this.numCoche + " cogiendo el turno " + this.turno);
	}

}