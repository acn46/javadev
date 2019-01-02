package edu.drexel.domain;

/**
 * A Singleton class.
 * @author AlanN
 *
 */
public class MySingle {
	
	private static final MySingle INSTANCE = new MySingle();
	
	public static MySingle getInstance() {
		return INSTANCE;
	}
	
	
	private int staffId;
	private String firstName;
	private String lastName;
	
	private MySingle() {
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
		MySingle other = (MySingle) obj;
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
