package ca.utoronto.utm.othello.viewcontroller;

import java.io.FileInputStream;
import java.io.InputStream;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.*;
import javax.swing.*;
import javafx.animation.AnimationTimer;
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
	// --module-path "/usr/share/openjfx/lib" --add-modules
	// javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.

	@Override
	public void start(Stage stage) throws Exception {
		// MODEL
		Othello othello=new Othello();

		
		// VIEWs:
		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman(othello);
		TokenCounter p1Count;
		TokenCounter p2Count;
		GameStatusTracker status;
		TimerDisplay timedisplay;
		GameTracker timer;
		// Create token count text fields
		p1Count = new TokenCounter("X : ", 'X');
		p2Count = new TokenCounter("O : ", 'O');
		p1Count.setEditable(false);
		p2Count.setEditable(false);
		// Create text fields to track current game status
		status = new GameStatusTracker("X's Turn");
		status.setEditable(false);
		// create text fields to track current player type
		OpponentTrackerP1 currentPlayerTypeP1 = new OpponentTrackerP1("P1: "+othello.getOpponent(OthelloBoard.P2));
		OpponentTrackerP2 currentPlayerTypeP2 = new OpponentTrackerP2("P2: "+othello.getOpponent(OthelloBoard.P1));
		currentPlayerTypeP1.setEditable(false);
		currentPlayerTypeP2.setEditable(false);
		//create timer to track time for each player's move
		timedisplay = new TimerDisplay();
		timedisplay.setEditable(false);
		/*javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	              p.repaint();
	          }
	       });*/
		int countdown = 300000;
		timer = new GameTracker(countdown, timetracker)
		
		 
		// MODEL->VIEW hookup
		othello.attach(oc);
		othello.attach(p1Count);
		othello.attach(p2Count);
		othello.attach(status);
		othello.attach(timer);
		othello.attach(currentPlayerTypeP1);
		othello.attach(currentPlayerTypeP2);
		
		
		// CONTROLLERS:
		// Create buttons to select which opponent to play
		Button hVsHuman = new Button("Human vs. Human");
		Button hVsRandom = new Button("Human vs. Random");
		Button hVsGreedy = new Button("Human vs. Greedy");
		// GUI grid to add all buttons and text views onto
		GridPane grid = new GridPane();
		// create game board buttons
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				BoardSquare boardSquare = new BoardSquare(othello,row,col);

				MoveAttemptEventHandler moveToClick = new MoveAttemptEventHandler(othello, grid);
				boardSquare.addEventHandler(ActionEvent.ACTION, moveToClick); // CONTROLLER->MODEL hookup

				grid.add(boardSquare, col, row);
				boardSquare.setPrefSize(35, 35);
				
				othello.attach(boardSquare); // MODEL->VIEW hookup
			}
		}
		// Add token counter and game tracker to view
		grid.add(p1Count, 9, 0);
		grid.add(p2Count, 9, 1);
		grid.add(status, 9, 2);
		// add opponent select buttons to view
		grid.add(hVsHuman, 9, 4);
		grid.add(hVsRandom, 9, 5);
		grid.add(hVsGreedy, 9, 6);
		// add player type trackers to view
		grid.add(currentPlayerTypeP1, 9, 7);
		grid.add(currentPlayerTypeP2, 9, 8);
		
		// opponent chooser GUI Event Handler
		OpponentChooserEventHandler chooseOpponentHandler = new OpponentChooserEventHandler(othello);
		
		
		// VIEW->CONTROLLER hookup
		
		
		// CONTROLLER->MODEL hookup
		// add eventHandlers to opponent chooser GUI
		hVsHuman.addEventHandler(ActionEvent.ACTION, chooseOpponentHandler);
		hVsRandom.addEventHandler(ActionEvent.ACTION, chooseOpponentHandler);
		hVsGreedy.addEventHandler(ActionEvent.ACTION, chooseOpponentHandler);
		
		
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
