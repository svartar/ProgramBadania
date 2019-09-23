/**
 * This class represents list, that stores patients
 */
package application;

import java.util.HashMap;

public class PatientSet {
	
	private HashMap<String, Patient> patients;
	private int size;
	
	public PatientSet() {
		patients = new HashMap<>();
	}
	
	public boolean addPatient(Patient patient) {
		if (patients.get(patient.getPesel()) == null) {
			patients.put(patient.getPesel(), patient);
			size++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removePatient(String pesel) {
		if (patients.remove(pesel) != null) {
			size--;
			return true;
		} else {
			return false;
		}	
	}
	
	public Patient getPatient(String pesel) {
		return patients.get(pesel);
	}
	
	public int getPatientAmount() {
		return size;
	}
	
	public HashMap<String, Patient> getPatients() {
		return patients;
	}

}
