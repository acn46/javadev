package edu.drexel.domain;

public class CompoundTest {

	public static void main(String[] args) {
		if (true || false) {
			System.out.println("Only one value needs to be true");
		}
		
		if (true && false ) {
			System.out.println("Both values need to be true for the and");
		} else {
			System.out.println("Both values need to be true");
		}
		
		int myMaxLimit = 20;
		int myMinLimit = 5;
		
		int myValue = 19;
		
		if (myValue < myMaxLimit && myValue > myMinLimit) {
			System.out.println("The value " + myValue + " is between " + myMinLimit + " and " + myMaxLimit);
		} else {
			System.out.println("The value " + myValue + " is not between " + myMinLimit + " and " + myMaxLimit);
		}
		
		int myMaxXLimit = 5;
		int myMinXLimit = 1;
		int myMaxYLimit = 3;
		int myMinYLimit = 1;
		
		int myXValue = 6;
		int myYValue = 2;
		
		if ( (myXValue < myMaxXLimit && myXValue > myMinXLimit) && (myYValue < myMaxYLimit && myYValue > myMinYLimit) ) {
			System.out.println("X and Y are within limits");
		} else {
			System.out.println("One value is outside the limit");
		}
		
		boolean myBoolean = false;
		if (!myBoolean) {
			System.out.println("Not false is true");
		}
	}

}
