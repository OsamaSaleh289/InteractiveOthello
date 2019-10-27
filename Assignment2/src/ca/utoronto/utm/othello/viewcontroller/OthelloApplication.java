package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put 
	// --module-path "/usr/share/openjfx/lib" --add-modules javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.
	
	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller
		
		// MODEL
		Othello othello=new Othello();
		
		// CONTROLLER
		// CONTROLLER->MODEL hookup
		ButtonPressEventHandler cpresshandler = new ButtonPressEventHandler(othello); 
	
		// VIEW
		GridPane grid = new GridPane();
		for (int row=0; row<8; row++) {
			for (int col=0; col<8; col++) {
				String b = "b" + Integer.toString(row) +Integer.toString(col);
				Button button = new Button(b);
				button.setOnAction(cpresshandler);
				grid.add(button, col, row);
			}
		}
		
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup
		
		
		// SCENE
		Scene scene = new Scene(grid); 
		stage.setTitle("Othello");
		stage.setScene(scene);
				
		// LAUNCH THE GUI
		stage.show();
	}

	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);
	}
}
