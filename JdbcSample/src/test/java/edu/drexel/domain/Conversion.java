package edu.drexel.domain;

public class Conversion {

	public static void main(String[] args) {
		int myVariable = 5;
		System.out.println("myVariable is equal to " + myVariable);
		
		String myString = Integer.toString(myVariable);
		System.out.println("myString is equal to " + myString);
		
		//myString = myString + 1;
		myString +=1;
		System.out.println("myString is equal to " + myString);
		
		myVariable = Integer.parseInt(myString) + 1;
		System.out.println("myVariable is equal to " + myVariable);
	}

}
