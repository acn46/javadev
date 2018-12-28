package edu.drexel.domain;

public class Cat implements CanSPeak{
	public String petName;
	private int age;
	
	
	public Cat() {
		System.out.println("A new instance of Cat created!");
	}
	
	@Override
	public void speak() {
		System.out.println(petName + " says, \"Meow\" ");
	}
	
	public int getAge() {
		return age;
	}
	
}
