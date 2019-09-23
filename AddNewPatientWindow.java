package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddNewPatientWindow {
	
	private PatientSet patients;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button closeButton;
	
	@FXML
	private TextField firstNameTextField;
	
	@FXML
	private TextField lastNameTextField;
	
	@FXML
	private TextField peselTextField;
	
	@FXML
	private TextField addressTextField;
	
	@FXML
	private Label messageLabel;
	
	public void initData (PatientSet patientSet) {
		patients = patientSet;
	}
	
	@FXML
	private void initialize() {
		initializeSaveButtonClick();
		initializeCloseButton();
	}
	
	public void initializeSaveButtonClick() {
		saveButton.setOnAction((event) -> {
			if (validateInput()) {
		    	Patient p = new Patient(firstNameTextField.getText(), lastNameTextField.getText(), peselTextField.getText());
		    	p.setAddress(addressTextField.getText());
		    	patients.addPatient(p);
		    	messageLabel.setTextFill(Color.GREEN);
		    	messageLabel.setText("Gotowe!");
			} else {
				messageLabel.setTextFill(Color.FIREBRICK);
		    	messageLabel.setText("Wype³nij wszystkie pola");
			}
		});
	}
	
	public void initializeCloseButton() {
		closeButton.setOnAction((event) -> {
		    Stage stage = (Stage) closeButton.getScene().getWindow();
		    stage.close();
		});
	}
	
	private boolean validateInput() {
		if( firstNameTextField.getText() != null && !firstNameTextField.getText().isEmpty() &&
				lastNameTextField.getText() != null && !lastNameTextField.getText().isEmpty() &&
						peselTextField.getText() != null && !peselTextField.getText().isEmpty() &&
								addressTextField.getText() != null && !addressTextField.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
