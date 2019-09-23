package application;

import java.util.ArrayList;
import java.util.List;

public class Patient {

	private String firstName;
	private String lastName;
	private String pesel;
	private String address;
	private List<BloodPressure> bloodPressureTests;
	
	public Patient(String firstName, String lastName, String pesel) {
		if (firstName != null && lastName != null && pesel != null) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.pesel = pesel;
			this.bloodPressureTests = new ArrayList<>();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		if (firstName != null) {
			this.firstName = firstName;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		if (lastName != null) {
			this.lastName = lastName;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getPesel() {
		return pesel;
	}
	
	public void setPesel(String pesel) {
		if (pesel != null) {
			this.pesel = pesel;
		} else {
			throw new IllegalArgumentException();
		}	
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != null) {
			this.address = address;
		} else {
			throw new IllegalArgumentException();
		}	
	}
	
	public List<BloodPressure> getBloodPressureTests() {
		return bloodPressureTests;
	}

	public void setBloodPressureTests(List<BloodPressure> bloodPressureTests) {
		this.bloodPressureTests = bloodPressureTests;
	}
}
