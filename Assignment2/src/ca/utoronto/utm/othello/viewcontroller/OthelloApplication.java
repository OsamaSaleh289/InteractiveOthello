package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
		Hints hints = new Hints(othello);
		TokenCounter p1Count;
		TokenCounter p2Count;
		GameStatusTracker status;
		TimerDisplay timedisplay;
		TimeTracker timer;
		Menu menu;
		MenuBar menuBar;
	
		// GUI grid to add all buttons and text views onto
		GridPane grid = new GridPane(); // CONTROLLER
		grid.setHgap(5); 
		grid.setVgap(5); 
		grid.setPadding(new Insets(5, 5, 5, 5));
		
		// Create token count text fields
		p1Count = new TokenCounter(OthelloBoard.P1 + " : "+othello.getCount(OthelloBoard.P1), OthelloBoard.P1);
		p2Count = new TokenCounter(OthelloBoard.P2 + " : "+othello.getCount(OthelloBoard.P2), OthelloBoard.P2);
		p1Count.setEditable(false);
		p2Count.setEditable(false);
		// Create text fields to track current game status
		status = new GameStatusTracker(OthelloBoard.P1 + "'s Turn");
		status.setEditable(false);
		// create text fields to track current player type
		MoveStrategyTracker currentPlayerTypeP1 = new MoveStrategyTracker(OthelloBoard.P1 + " : "+othello.player1.getStrategyName());
		MoveStrategyTracker currentPlayerTypeP2 = new MoveStrategyTracker(OthelloBoard.P2 + " : "+othello.player2.getStrategyName());
		currentPlayerTypeP1.setEditable(false);
		currentPlayerTypeP2.setEditable(false);
		//create timer to track time for each player's move
		timer = new TimeTracker(othello);
		timedisplay = new TimerDisplay(timer);
		timer.attach(timedisplay);
		timedisplay.setEditable(false);

		// A menu for Hint
		menuBar = new MenuBar();
		menu = new Menu("Hint Menu");
		HintMenuItem randomMenuItem = new HintMenuItem(hints, "random");
		menu.getItems().add(randomMenuItem);
		HintMenuItem greedyMenuItem = new HintMenuItem(hints, "greedy");
		menu.getItems().add(greedyMenuItem);
		HintMenuItem betterMenuItem = new HintMenuItem(hints, "better");
		menu.getItems().add(betterMenuItem);
		menuBar.getMenus().add(menu);
		
		
		// MODEL->VIEW hookup
		othello.attach(hints);
		othello.attach(p1Count);
		othello.attach(p2Count);
		othello.attach(status);
		othello.attach(timer);
		othello.player1.attach(currentPlayerTypeP1);
		othello.player2.attach(currentPlayerTypeP2);
		hints.attach(randomMenuItem);
		hints.attach(greedyMenuItem);
		hints.attach(betterMenuItem);
		
		
		// CONTROLLERS:
		// Create buttons to select which opponent to play
		Button hVsHuman = new Button("Human vs. Human");
		hVsHuman.setPrefSize(190, 20);
		Button hVsRandom = new Button("Human vs. Random");
		hVsRandom.setPrefSize(190, 20);
		Button hVsGreedy = new Button("Human vs. Greedy");
		hVsGreedy.setPrefSize(190, 20);
		Button hVsBetter = new Button("Human vs. Better");
		hVsBetter.setPrefSize(190, 20);
		
		//Setup the restart and undo buttons
		Button restart = new Button("Restart");
		Button undo = new Button("Undo");

		//Setup Play button
		Color buttongreen = Color.web("#4AA02C");
		CornerRadii corners = new CornerRadii(2);
		Button play = new Button("Play");
		play.setBackground(new Background(new BackgroundFill(buttongreen, corners, null)));
		play.setPrefSize(190, 20);
		// create game board buttons
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				BoardSquare boardSquare = new BoardSquare(othello,row,col,hints);

				MoveAttemptEventHandler moveToClick = new MoveAttemptEventHandler(othello);
				boardSquare.addEventHandler(ActionEvent.ACTION, moveToClick); // CONTROLLER->MODEL hookup
				grid.add(boardSquare, col, row);
				boardSquare.setPrefSize(35, 35);
				
				othello.attach(boardSquare); // MODEL->VIEW hookup
				hints.attach(boardSquare);
			}
		}
		//create Hbox to fit two buttons in one grid pane cell
		HBox box = new HBox();
		box.setPadding(new Insets(5, 5, 5, 5));
		box.getChildren().addAll(restart, undo);
		// Add token counter and game tracker to view
		grid.add(p1Count, 9, 1);
		grid.add(p2Count, 10, 1);
		grid.add(status, 9, 2, 2, 1);
		// Add timer to view
		grid.add(timedisplay, 9, 3, 2, 1);
		//Add the restart/undo buttons to the view
		grid.add(box, 9, 4);
		//Add play button to view
		grid.add(play, 10, 4);
		// add opponent select buttons to view
		grid.add(hVsHuman, 9, 5);
		grid.add(hVsRandom, 9, 6);
		grid.add(hVsGreedy, 10, 5);
		grid.add(hVsBetter, 10, 6);
		// add player type trackers to view
		grid.add(currentPlayerTypeP1, 9, 0);
		grid.add(currentPlayerTypeP2, 10, 0);
		
		
		// opponent chooser GUI Event Handler
		OpponentEventHandler humanOpponentHandler = new OpponentEventHandler(othello);
		OpponentEventHandler randomOpponentHandler = new OpponentEventHandler(othello);
		OpponentEventHandler greedyOpponentHandler = new OpponentEventHandler(othello);
		OpponentEventHandler betterOpponentHandler = new OpponentEventHandler(othello);
		
		HintEventHandler handleRandomHint = new HintEventHandler(hints);
		HintEventHandler handleGreedyHint = new HintEventHandler(hints);
		HintEventHandler handleBetterHint = new HintEventHandler(hints);
		randomMenuItem.addEventHandler(ActionEvent.ACTION, handleRandomHint);
		greedyMenuItem.addEventHandler(ActionEvent.ACTION, handleGreedyHint);
		betterMenuItem.addEventHandler(ActionEvent.ACTION, handleBetterHint);
		
		//Restart and undo event handler creation
		RestartEventHandler restartHandler = new RestartEventHandler(othello, timer);
		UndoInvoker undoInvoker = new UndoInvoker(othello);
		PlayEventHandler playHandler = new PlayEventHandler(othello, timer);
		
		
		// VIEW->CONTROLLER hookup
		
		
		// CONTROLLER->MODEL hookup
		// add eventHandlers to opponent chooser GUI
		hVsHuman.addEventHandler(ActionEvent.ACTION, humanOpponentHandler);
		hVsRandom.addEventHandler(ActionEvent.ACTION, randomOpponentHandler);
		hVsGreedy.addEventHandler(ActionEvent.ACTION, greedyOpponentHandler);
		hVsBetter.addEventHandler(ActionEvent.ACTION, betterOpponentHandler);
		
		//Add the restart,undo, handlers to our buttons
		restart.addEventHandler(ActionEvent.ACTION, restartHandler);
		undo.addEventHandler(ActionEvent.ACTION, undoInvoker);
		play.addEventHandler(ActionEvent.ACTION, playHandler);
		
		
		// SCENE
		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setCenter(grid);
		
		Scene scene = new Scene(root);
		stage.setTitle("Othello");
		stage.setScene(scene);

		// LAUNCH THE GUI
		stage.show();
	}
	

	public static void main(String[] args) {
		new OthelloApplication();
		launch(args);
		
	}
}
