package edu.drexel.domain;

/**
 * A one-instance-per-thread class.
 * (singleton per thread).
 * @author AlanN
 *
 */
public class MyOnePerThread {
	
	private static final ThreadLocal<MyOnePerThread> INSTANCE_POOL = new ThreadLocal() {
		@Override
		protected MyOnePerThread initialValue() {
			System.out.println(Thread.currentThread().getName() + " - creating the instance for the thread.");
	        return (new MyOnePerThread());
	    }
	};
	
	public static MyOnePerThread getInstance() {
		return INSTANCE_POOL.get();
	}
	
	
	private int staffId;
	private String firstName;
	private String lastName;
	
	private MyOnePerThread() {
		//
	}
	
	@Override
	public String toString() {
		return "MyOnePerThread [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
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
		MyOnePerThread other = (MyOnePerThread) obj;
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
