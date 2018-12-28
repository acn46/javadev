package edu.drexel.domain;

import java.util.Scanner;

public class Input {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Enter your name: ");
		Scanner in = new Scanner(System.in);
		String name = in.next();
		String greeting = "Hello, " + name;
		System.out.println(greeting);
	}

}
