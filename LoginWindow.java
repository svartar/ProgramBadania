package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow {
	
	Stage primaryStage;

	public LoginWindow(Stage stage) {
		primaryStage = stage;
	}
	
	public void run() { 
		
		primaryStage.setTitle("Witaj - zaloguj siê");
		
		// Tworzymy scene, czyli layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25)); // Tworzy prostokat i pozwala nam stworzyc odstepy 

		// Doczepiamy scene do podium
		Scene scene = new Scene(grid, 400, 275);
		primaryStage.setScene(scene);
		
		// Tworzymy rozne elementy ktore dodajemy do sceny
		Text scenetitle = new Text("Witaj");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		// Wpisujemy: co mamy dodac, index kolumny, index wiersza, ile kolumn obiekt powinien zajac, ile wierszy obiekt powinien zajac
		grid.add(scenetitle, 0, 0, 2, 1); 

		Label userName = new Label("Nazwa u¿ytkownika:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Has³o:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		Button btn = new Button("Zaloguj siê");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	
            	if (userTextField.getText().equals("admin") && pwBox.getText().equals("admin")) {
                    try {
                        
                        Parent root = FXMLLoader.load(getClass().getResource("PrimaryWindow.fxml"));
                		
                		Stage stage = new Stage();
           			 	stage.setTitle("G³ówne okno");
        				Scene scene = new Scene(root);
        				
        				// Jezeli chcemy uzyc stylow CSS to musimy je dodac
        				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        				
        				stage.setScene(scene);
        				stage.show();
        	            primaryStage.close();
                    	
        	         } catch (Exception ex) {
        		            ex.printStackTrace();
        	   		 	}

            	} else {
                	
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Z³y login lub has³o");
            	}
            }
        });
			
	    primaryStage.show();
	}
}
