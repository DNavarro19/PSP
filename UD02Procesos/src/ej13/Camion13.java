package ej13;

import java.util.Random;

public class Camion13 implements Runnable {

	Thread t;
	private Parking13 park;
	private int numCamion;
	private int turno;
	private Random rnd = new Random();
	private String tipoV = "camion";

	public Camion13(int num, Parking13 park) {
		this.park = park;
		this.numCamion = 100 + num;
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

	private void entrarAlParking() throws InterruptedException {
		park.entrarCamion(this.numCamion, this.turno, this.tipoV);
	}

	private void salirDelParking() {
		park.salirCamion(this.numCamion);
	}

	private void cogerTurno() {
		this.turno = park.getTurnoCamion();
		System.out.println("Camion número " + this.numCamion + " cogiendo el turno " + this.turno);
	}

}