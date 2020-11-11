package ej8;

public class LeeBuffer extends Thread {

	private Buffer buf;
	private boolean corr;

	public LeeBuffer(Buffer buffer) {
		this.buf=buffer;
		this.corr = true;
	}

	public void run() {
		lee();
	}
	
	private synchronized void lee() {
		try {
			buf.lee();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numCorr = buf.buffer[0];
		for (int i = 0; i < buf.buffer.length && this.corr; i++) {
			if (buf.buffer[i] != numCorr) {
				this.corr = false;
			}
		}

		if (corr) {
			System.out.println("Correcto");
		} else {
			System.out.println("Error");
		}
		buf.leido();
	}
	
	public boolean isCorr() {
		return this.corr;
	}
}
