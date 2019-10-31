package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
		
//		// MODEL
//		Othello othello=new Othello();

		// CONTROLLER
		// CONTROLLER->MODEL hookup
		ButtonPressEventHandler cpresshandler = new ButtonPressEventHandler(); 
//		PrintEventHandler p = new PrintEventHandler(othello);
		// VIEW
		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
		
        // create a input stream 
        FileInputStream input1 = new FileInputStream("D:\\black.jpg"); 
        FileInputStream input2 = new FileInputStream("D:\\white.jpg"); 
        // create a image 
        Image black = new Image(input1); 
        Image white = new Image(input2); 
        // create a image View 
        ImageView vblack = new ImageView(black); 
        ImageView vwhite = new ImageView(white); 
		// MODEL
		Othello othello= oc.getOthello();
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup
//		lb1.attach(lbo1);
		othello.addObserver(oc);
		
		GridPane grid = new GridPane();
		for (int row=0; row<8; row++) {
			for (int col=0; col<8; col++) {
				if(othello.getToken(row, col) == 'X') {
					Button button = new Button("", vblack));
				}else if (othello.getToken(row, col) == 'O') {
					Button button = new Button("", vwhite));
				}
				Button button = new Button(othello.getToken(row, col)+"");
				
				button.setOnAction(cpresshandler);
				
				PrintEventHandler p = new PrintEventHandler(othello, grid);
				button.setOnAction(p);
				button.addEventHandler(ActionEvent.ACTION, p);
				
				grid.add(button, col, row);
				button.setPrefSize(28, 28);
			}
		}
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
