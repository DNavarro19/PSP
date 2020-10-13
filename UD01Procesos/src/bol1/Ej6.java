package bol1;

public class Ej6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(numRandom());

	}

	private static int numRandom(){
		int num;
		num = (int) (Math.random()*11);
		return num;
	}
}
