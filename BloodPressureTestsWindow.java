package application;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class BloodPressureTestsWindow {
	
	private Patient currentPatient;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button showDiagramButton;
	
	@FXML
	private TextField highTextField;
	
	@FXML
	private TextField lowTextField;
	
	@FXML
	private TextField dateTextField;
	
	@FXML
	private Label messageLabel;
	
	@FXML
	private Label patientLabel;
	
	@FXML
	private TableView<BloodPressure> table;
	
	@FXML
	private TableColumn<BloodPressure, String> highColumn;
	
	@FXML
	private TableColumn<BloodPressure, String> lowColumn;
	
	@FXML
	private TableColumn<BloodPressure, String> dateColumn;
	
	public void initData(Patient patient) {
		currentPatient = patient;
		
		List<BloodPressure> array = currentPatient.getBloodPressureTests();
		
		for (int i = 0; i < currentPatient.getBloodPressureTests().size(); i++) {
			table.getItems().add(new BloodPressure(array.get(i).getHigh(), array.get(i).getLow(), array.get(i).getDate()));
		}
		
		patientLabel.setText(currentPatient.getFirstName() + " " + currentPatient.getLastName());
	}
	
	@FXML
	private void initialize() {
    	highColumn.setCellValueFactory(new PropertyValueFactory<BloodPressure, String>("high"));
    	lowColumn.setCellValueFactory(new PropertyValueFactory<BloodPressure, String>("low"));
    	dateColumn.setCellValueFactory(new PropertyValueFactory<BloodPressure, String>("date"));
    }
	
    @FXML
    private void saveButtonAction(ActionEvent event) {
    	
    	try {
    		BloodPressure bp = new BloodPressure(Integer.parseInt(highTextField.getText()), Integer.parseInt(lowTextField.getText()), dateTextField.getText());
    		
    		List<BloodPressure> array = currentPatient.getBloodPressureTests();
    		array.add(bp);
    		currentPatient.setBloodPressureTests(array);
    		
            table.getItems().add(bp);
            highTextField.clear();
            lowTextField.clear();
            dateTextField.clear();
            messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Gotowe");
    		
    	} catch(IllegalArgumentException e) {
    		messageLabel.setTextFill(Color.FIREBRICK);
    		messageLabel.setText("B³¹d");
    	}
    }
    
    @FXML 
    private void showDiagram() {
    	new BloodPressureDiagram(currentPatient);
    }
}
