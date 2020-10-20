package bol1;

import java.util.Random;

public class Ej6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(numRandom());

	}

	private static int numRandom(){
		Random r = new Random();
		return r.nextInt(10);  
	}
}
