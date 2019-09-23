package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditPatientWindow {
	
	private Patient currentPatient;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button closeButton;
	
	@FXML
	private Button bloodPressureButton;
	
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
	
	public void initData (Patient patient) {
		currentPatient = patient;
		initializeTextFields();
	}
	
	@FXML
	private void initialize() {
		initializeSaveButton();
		initializeCloseButton();
		bloodPressureButton();
	}
	
	private void initializeTextFields() {
		firstNameTextField.setText(currentPatient.getFirstName());
		lastNameTextField.setText(currentPatient.getLastName());
		peselTextField.setText(currentPatient.getPesel());
		addressTextField.setText(currentPatient.getAddress());
	}
	
	private void initializeSaveButton() {
		saveButton.setOnAction((event) -> {
			if (validateInput()) {
				
				currentPatient.setFirstName(firstNameTextField.getText());
				currentPatient.setLastName(lastNameTextField.getText());
				currentPatient.setPesel(peselTextField.getText());
				currentPatient.setAddress(addressTextField.getText());
		    	messageLabel.setTextFill(Color.GREEN);
		    	messageLabel.setText("Gotowe!");
			} else {
				messageLabel.setTextFill(Color.FIREBRICK);
		    	messageLabel.setText("Wype³nij wszystkie pola");
			}
		});
	}
	
	private void initializeCloseButton() {
		closeButton.setOnAction((event) -> {
		    Stage stage = (Stage) closeButton.getScene().getWindow();
		    stage.close();
		});
	}
	
	private void bloodPressureButton() {
		bloodPressureButton.setOnAction((event) -> {
			try {
				showBloodPressureTestWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
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


	public void showBloodPressureTestWindow() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BloodPressureTestsWindow.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		BloodPressureTestsWindow controller = loader.<BloodPressureTestsWindow>getController();
		controller.initData(currentPatient);
        stage.show();
	}
}
