package edu.drexel.domain;

public class Main {

	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.petName = "Hoover";
		cat.speak();
		
		System.out.println(cat.getAge());
		Lynx wild = new Lynx();
		wild.growl();
		
		Cat pet = new Lynx();
//		Lynx lynx = new Cat();    // not legal.fzz
		
		CanSPeak newPet = new Cat();
		CanSPeak newPet2 = new Lynx();
	}

}
