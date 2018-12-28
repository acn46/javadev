package edu.drexel.domain;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest {

	public static void main(String[] args) {
		String[] myArray = new String[] {"Doug", "Mike", "Janet", "Matt"};
		System.out.println(myArray[0]);
		
		for (int i = 0; i < myArray.length; i++) {
			System.out.println(myArray[i]);
		}
		
		for (String element : myArray) {
			System.out.println(element);
		}
		
		myArray[0] = "Tim";		
		for (String element : myArray) {
			System.out.println(element);
		}
		
		List<String> myList = new ArrayList();
		myList.add("Rocket");
		myList.add("Scout");
		System.out.println(myList);
		
		for (String element : myList) {
			System.out.println(element);
		}
		
		myList.add(1, "Hoover");
		System.out.println(myList);
		
		myList.remove("Scout");
		System.out.println(myList);
		
		myList.remove("Scout1");
		System.out.println(myList);
	}

}
