package ca.utoronto.utm.othello.viewcontroller;
import java.io.FileInputStream;

import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		TokenCounter p1Count;
		TokenCounter p2Count;
		GameStatusTracker status;
		// Create and hook up the Model, View and the controller
		
//		// MODEL
//		Othello othello=new Othello();

		// CONTROLLER
		// CONTROLLER->MODEL hookup
		ButtonPressEventHandler cpresshandler = new ButtonPressEventHandler(); 
//		PrintEventHandler p = new PrintEventHandler(othello);
		// VIEW
		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
		
//        // create a input stream 
//		
//		
//        FileInputStream input1 = new FileInputStream("D:\\black.jpg"); 
//        FileInputStream input2 = new FileInputStream("D:\\white.jpg"); 
//        
//        
//        // create a image 
//        Image black = new Image(input1); 
//        Image white = new Image(input2); 
//        // create a image View 
//        ImageView vblack = new ImageView(black); 
//        ImageView vwhite = new ImageView(white);
        
        
		 //Create token count text fields
        p1Count = new TokenCounter("X : ", 'X');
        p2Count = new TokenCounter ("O : ", 'O');
        p1Count.setEditable(false);
        p2Count.setEditable(false);
        
        //Create text fields to track current game status
        status = new GameStatusTracker("X's Turn");
        status.setEditable(false);
        
		// MODEL
		Othello othello= oc.getOthello();
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup
//		lb1.attach(lbo1);
		othello.addObserver(oc);
		othello.addObserver(p1Count);
		othello.addObserver(p2Count);
		othello.addObserver(status);
		
		GridPane grid = new GridPane();
		for (int row=0; row<8; row++) {
			for (int col=0; col<8; col++) {
//				Button button = new Button("", vwhite);
//				if(othello.getToken(row, col) == 'X') {
//					button = new Button("", vblack);
//				}
				Button button = new Button(othello.getToken(row, col)+"");
				
				button.setOnAction(cpresshandler);
				
				PrintEventHandler p = new PrintEventHandler(othello, grid);
				button.setOnAction(p);
				button.addEventHandler(ActionEvent.ACTION, p);
				
				grid.add(button, col, row);
				button.setPrefSize(28, 28);
			}
		}
		//Add token counter and game tracker to view
		grid.add(p1Count, 9, 0);
		grid.add(p2Count, 9, 1);
		grid.add(status, 9, 2);
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
