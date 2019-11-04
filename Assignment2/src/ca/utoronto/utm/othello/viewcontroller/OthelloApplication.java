package ca.utoronto.utm.othello.viewcontroller;

import java.io.FileInputStream;
import java.io.InputStream;

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
import java.lang.Object;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put
	// --module-path "/usr/share/openjfx/lib" --add-modules
	// javafx.controls,javafx.fxml
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

		// Create token count text fields
		p1Count = new TokenCounter("X : ", 'X');
		p2Count = new TokenCounter("O : ", 'O');
		p1Count.setEditable(false);
		p2Count.setEditable(false);

		// Create text fields to track current game status
		status = new GameStatusTracker("X's Turn");
		status.setEditable(false);

		// MODEL
		Othello othello = oc.getOthello();
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup
		othello.addObserver(oc);
		othello.addObserver(p1Count);
		othello.addObserver(p2Count);
		othello.addObserver(status);

		GridPane grid = new GridPane();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				
				Button button = new Button("", othello.getImage(row, col));

				button.setOnAction(cpresshandler);

				PrintEventHandler p = new PrintEventHandler(othello, grid);
				button.setOnAction(p);
				button.addEventHandler(ActionEvent.ACTION, p);

				grid.add(button, col, row);
				button.setPrefSize(35, 35);
				
			}
		}
		// Add token counter and game tracker to view
		grid.add(p1Count, 9, 0);
		grid.add(p2Count, 9, 1);
		grid.add(status, 9, 2);
		
		// Create buttons to select which opponent to play
		Button hVsHuman = new Button("Human vs. Human");
		Button hVsRandom = new Button("Human vs. Random");
		Button hVsGreedy = new Button("Human vs. Greedy");
		
		//add OpponentChooserGUISelection Event Handler
		OpponentChooserEventHandler chooseOpponentHandler = new OpponentChooserEventHandler(othello);
		
		//hVsHuman.setOnAction(chooseOpponentHandler);
		//hVsRandom.setOnAction(chooseOpponentHandler);
		//hVsGreedy.setOnAction(chooseOpponentHandler);
		
		hVsHuman.addEventHandler(ActionEvent.ACTION, chooseOpponentHandler);
		hVsRandom.addEventHandler(ActionEvent.ACTION, chooseOpponentHandler);
		hVsGreedy.addEventHandler(ActionEvent.ACTION, chooseOpponentHandler);
		
		OpponentTrackerP1 currentPlayerTypeP1 = new OpponentTrackerP1("P1: "+othello.getOpponent(OthelloBoard.P2));
		OpponentTrackerP2 currentPlayerTypeP2 = new OpponentTrackerP2("P2: "+othello.getOpponent(OthelloBoard.P1));
		currentPlayerTypeP1.setEditable(false);
		currentPlayerTypeP2.setEditable(false);
		
		othello.addObserver(currentPlayerTypeP1);
		othello.addObserver(currentPlayerTypeP2);
		
		// add opponent select buttons to view
		grid.add(hVsHuman, 9, 4);
		grid.add(hVsRandom, 9, 5);
		grid.add(hVsGreedy, 9, 6);
		
		grid.add(currentPlayerTypeP1, 9, 7);
		grid.add(currentPlayerTypeP2, 9, 8);
		
		// SCENE
		Scene scene = new Scene(grid);
		stage.setTitle("Othello");
		stage.setScene(scene);

		// LAUNCH THE GUI
		stage.show();
	}
	
//	public static BufferedImage resizeImage(final Image black, int width, int height) {
//        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        final Graphics2D graphics2D = bufferedImage.createGraphics();
//        graphics2D.setComposite(AlphaComposite.Src);
//        //below three lines are for RenderingHints for better image quality at cost of higher processing time
//        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
//        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        graphics2D.drawImage(black, 0, 0, width, height, null);
//        graphics2D.dispose( graphics2D.drawzImage);
//        return bufferedImage;
//	}

	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);
	}
}
