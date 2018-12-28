package edu.drexel.domain;

public class WhileTest {

	public static void main(String[] args) {
		int myValue = 0;
		
		while (myValue < 100) {
			if (myValue % 9 == 0) {
				System.out.println(myValue);
			}
			
			//myValue = myValue + 1;
			//myValue += 1;
			myValue++;
		}
		
		int myBadValue = 0;
		
		do {
			System.out.println("This will run at least once");
		} while (myBadValue != 0);
		
		for (int i = 0; i < 25; i++) {
			System.out.println("The square is " + i + " is " + Math.pow(i, 2));
		}
		
		for (int r = 1; r <= 12; r++) {
			String tableRow = "";
			for (int c = 1; c <=12; c++) {
				int product = r*c;
				String productString = Integer.toString(product) + "\t";
				tableRow += productString;
			}
			System.out.println(tableRow);
		}
		
	}
	
	

}
