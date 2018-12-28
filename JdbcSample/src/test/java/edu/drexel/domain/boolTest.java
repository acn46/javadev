package edu.drexel.domain;

public class boolTest {

	public static void main(String[] args) {
		boolean knownJava = true;
		System.out.println("The value of knownJava is " + knownJava);

		if (knownJava) { 
			System.out.println("I know Java");
		}
		
		int myValue = 5;
		System.out.println("myValue is equal to 5");
		
		if (myValue == 5) {
			System.out.println("The condition myValue is equal to 5");
		}
		
		if (myValue < 5) {
			System.out.println("myValue is less than 5");
		} else {
			System.out.println("myValue is not less than 5");
		}
		
		if (myValue != 5) {
			System.out.println("This condition myValue != 5 is true");
		} else if (myValue > 5) {
			System.out.println("The condition myValye >5 is true");
		} else {
			System.out.println("All conditions are false");
		}
		
		if (myValue >= 1) {
			System.out.println("This condition myValue >= 1 is true");
			
			if (myValue <= 5) {
				System.out.println("This condition of myValue <= 5 is true");
			}
		}
	}

}
