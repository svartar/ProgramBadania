package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PrimaryWindow {
	
	@FXML
	private Button addNewPatientButton;
	
	@FXML
	private Button editPatientButton;
	
	@FXML
	private Button removePatientButton;
	
	@FXML
	private Button searchPatientButton;
	
	@FXML
	private Label firstNameLabel;
	
	@FXML
	private Label lastNameLabel;
	
	@FXML
	private Label peselLabel;
	
	@FXML
	private Label addressLabel;
	
	@FXML
	private TextField searchTextField;
	
	@FXML
	private Label searchMessageLabel;
	
    @FXML
    private ListView<Patient> myListView;
    private ObservableList<Patient> listViewData = FXCollections.observableArrayList();
	
	private Patient currentPatient;
	private PatientSet patients;
	
    public PrimaryWindow() {
    	
    	patients = new PatientSet();
    	
    	Patient p1 = new Patient("Jolanta", "Makowska", "76040998765");
    	p1.setAddress("ul. Rydygiera 1, 12-345 Warszawa");
    	ArrayList<BloodPressure> a1 = new ArrayList<>();
    	a1.add(new BloodPressure(130, 100, "02/01/16"));
    	a1.add(new BloodPressure(140, 90, "10/01/16"));
    	a1.add(new BloodPressure(135, 80, "29/01/16"));
    	a1.add(new BloodPressure(150, 100, "28/02/16"));
    	a1.add(new BloodPressure(145, 90, "20/03/16"));
    	a1.add(new BloodPressure(155, 110, "10/04/16"));
    	p1.setBloodPressureTests(a1);
    	
    	Patient p2 = new Patient("Marian", "Lemiesz", "90051212387");
    	p2.setAddress("ul. Pankracego 2, 12-345 Szczecin");
    	Patient p3 = new Patient("Kazimierz", "Bogdan", "80061212345");
    	p3.setAddress("ul. Turecka 3, 12-345 Lublin");
    	Patient p4 = new Patient("Janina", "Krokodyl", "63061212347");
    	p4.setAddress("ul. Lipowa 14/4, 12-345 Katowice");
    	
    	patients.addPatient(p1);
    	patients.addPatient(p2);
    	patients.addPatient(p3);
    	patients.addPatient(p4);

    }
    
	@FXML
	private void initialize() {
		
		// Inicjalizujemy przyciski
		initializeAddNewPatientButtonClick();
		initializeEditPatientButton();
		initializeRemovePatientButton();
		initializeSearchPatientButton();
		
		// Inicjalizujemy liste
		initializeListView();
		initializeListSelectionClick();
		
	}
	
	public void initializeAddNewPatientButtonClick() {
		addNewPatientButton.setOnAction((event) -> {
			try {
				showAddNewPatientWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void initializeEditPatientButton() {
		editPatientButton.setOnAction((event) -> {
			try {
				if (currentPatient != null)
					showEditPatientWindow(currentPatient);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void initializeRemovePatientButton() {
		removePatientButton.setOnAction((event) -> {
			if (currentPatient != null) {
				patients.removePatient(currentPatient.getPesel());
				initializeListView();
			}
		});
	}
	
	public void initializeSearchPatientButton() {
		searchPatientButton.setOnAction((event) -> {
			if (patients.getPatients().get(searchTextField.getText()) != null) {
				try {
					showEditPatientWindow(patients.getPatients().get(searchTextField.getText()));
					searchMessageLabel.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				searchMessageLabel.setTextFill(Color.FIREBRICK);
				searchMessageLabel.setText("Taki pacjent nie istnieje");
			}
		});
	}
	
	public void initializeListView() {
		
		listViewData.clear();
		
    	for (Patient patient : patients.getPatients().values()) {
    		listViewData.add(patient);
    	}
    	
		myListView.setItems(listViewData);
		
		myListView.setCellFactory((list) -> {
		    return new ListCell<Patient>() {
		        @Override
		        protected void updateItem(Patient item, boolean empty) {
		            super.updateItem(item, empty);
		            
		            if (item == null || empty) {
		                setText(null);
		            } else {
		                setText(item.getFirstName() + " " + item.getLastName());
		            }
		        }
		    }; 
		});
	}
	
	public void initializeListSelectionClick() {
		myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			
			if (newValue != null) {
				firstNameLabel.setText(newValue.getFirstName());
				lastNameLabel.setText(newValue.getLastName());
				peselLabel.setText(newValue.getPesel());
				addressLabel.setText(newValue.getAddress());
				
				currentPatient = newValue;
			}
		});
	}
	
	public Stage showAddNewPatientWindow() throws IOException {
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNewPatientWindow.fxml"));

		  Stage stage = new Stage();
		  stage.setScene(new Scene(loader.load()));

		  AddNewPatientWindow controller = loader.<AddNewPatientWindow>getController();
		  controller.initData(patients);

		  stage.show();
		  
		  stage.setOnHiding((event) -> {
			  initializeListView();
		  });

		  return stage;
	}
	
	public Stage showEditPatientWindow(Patient patient) throws IOException {
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPatientWindow.fxml"));

		  Stage stage = new Stage();
		  stage.setScene(new Scene(loader.load()));

		  EditPatientWindow controller = loader.<EditPatientWindow>getController();
		  controller.initData(patient);

		  stage.show();
		  
		  stage.setOnHiding((event) -> {
			  initializeListView();
		  });

		  return stage;
	}

}
