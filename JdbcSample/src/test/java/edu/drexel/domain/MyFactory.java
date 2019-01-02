package edu.drexel.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A Singleton class.
 * @author AlanN
 *
 */
public class MyFactory {
	
	private static final int poolSize = 7;
	private static final MyFactory[] INSTANCE_POOL = new MyFactory[poolSize];
	private static int kounter = 0;
	
	public static MyFactory getInstance() {
		int index = kounter % poolSize;
		MyFactory instance = INSTANCE_POOL[index];
		if (instance == null) {
			System.out.println("kounter="+kounter+" - creating a new instance.");
			instance = new MyFactory();
			INSTANCE_POOL[index] = instance;
		} else {
			System.out.println("kounter="+kounter+" - return existing instance.");
		}
		
		kounter++;
		
		return instance;
	}
	
	
	private int staffId;
	private String firstName;
	private String lastName;
	
	private MyFactory() {
		//
	}
	
	@Override
	public String toString() {
		return "MySingle [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + staffId;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyFactory other = (MyFactory) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (staffId != other.staffId)
			return false;
		return true;
	}

}
