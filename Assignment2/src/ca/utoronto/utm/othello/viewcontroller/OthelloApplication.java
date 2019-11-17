package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		OthelloController oc = new OthelloController(othello);
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
		MoveStrategyTracker currentPlayerTypeP1 = new MoveStrategyTracker(OthelloBoard.P1 + " : "+oc.player1.getStrategyName());
		MoveStrategyTracker currentPlayerTypeP2 = new MoveStrategyTracker(OthelloBoard.P2 + " : "+oc.player2.getStrategyName());
		currentPlayerTypeP1.setEditable(false);
		currentPlayerTypeP2.setEditable(false);
		//create timer to track time for each player's move
		timer = new TimeTracker();
		timedisplay = new TimerDisplay(timer);
		timer.attach(timedisplay);
		timedisplay.setEditable(false);

		// A menu for Hint
		menuBar = new MenuBar();
		menu = new Menu("Hint Menu");
		HintMenuItem randomMenuItem = new HintMenuItem(oc, "random");
		menu.getItems().add(randomMenuItem);
		HintMenuItem greedyMenuItem = new HintMenuItem(oc, "greedy");
		menu.getItems().add(greedyMenuItem);
		HintMenuItem betterMenuItem = new HintMenuItem(oc, "better");
		menu.getItems().add(betterMenuItem);
		menuBar.getMenus().add(menu);
		
		
		// MODEL->VIEW hookup
		//othello.attach(oc);
		othello.attach(p1Count);
		othello.attach(p2Count);
		othello.attach(status);
		othello.attach(timer);
		oc.player1.attach(currentPlayerTypeP1);
		oc.player2.attach(currentPlayerTypeP2);
		oc.attach(randomMenuItem);
		oc.attach(greedyMenuItem);
		oc.attach(betterMenuItem);
		
		
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

		// create game board buttons
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				BoardSquare boardSquare = new BoardSquare(othello,row,col,oc);

				MoveAttemptEventHandler moveToClick = new MoveAttemptEventHandler(othello);
				boardSquare.addEventHandler(ActionEvent.ACTION, moveToClick); // CONTROLLER->MODEL hookup
				grid.add(boardSquare, col, row);
				boardSquare.setPrefSize(35, 35);
				
				othello.attach(boardSquare); // MODEL->VIEW hookup
				oc.attach(boardSquare);
			}
		}
		// Add token counter and game tracker to view
		grid.add(p1Count, 9, 1);
		grid.add(p2Count, 10, 1);
		grid.add(status, 9, 2, 2, 1);
		// Add timer to view
		grid.add(timedisplay, 9, 3, 2, 1);
		//Add the restart button to the view
		grid.add(restart, 9, 4);
		grid.add(undo, 10, 4);
		// add opponent select buttons to view
		grid.add(hVsHuman, 9, 5);
		grid.add(hVsRandom, 9, 6);
		grid.add(hVsGreedy, 10, 5);
		grid.add(hVsBetter, 10, 6);
		// add player type trackers to view
		grid.add(currentPlayerTypeP1, 9, 0);
		grid.add(currentPlayerTypeP2, 10, 0);
		
		// opponent chooser GUI Event Handler
		HumanOpponentEventHandler humanOpponentHandler = new HumanOpponentEventHandler(othello, timer, oc);
		RandomOpponentEventHandler randomOpponentHandler = new RandomOpponentEventHandler(othello, timer, oc);
		GreedyOpponentEventHandler greedyOpponentHandler = new GreedyOpponentEventHandler(othello, timer, oc);
		BetterOpponentEventHandler betterOpponentHandler = new BetterOpponentEventHandler(othello, timer, oc);
		
		HintEventHandler handleRandomHint = new HintEventHandler(oc);
		HintEventHandler handleGreedyHint = new HintEventHandler(oc);
		HintEventHandler handleBetterHint = new HintEventHandler(oc);
		randomMenuItem.addEventHandler(ActionEvent.ACTION, handleRandomHint);
		greedyMenuItem.addEventHandler(ActionEvent.ACTION, handleGreedyHint);
		betterMenuItem.addEventHandler(ActionEvent.ACTION, handleBetterHint);
		
		//Restart and undo event handler creation
		RestartEventHandler restartHandler = new RestartEventHandler(othello);
		UndoInvoker undoInvoker = new UndoInvoker(othello);
		
		
		// VIEW->CONTROLLER hookup
		
		
		// CONTROLLER->MODEL hookup
		// add eventHandlers to opponent chooser GUI
		hVsHuman.addEventHandler(ActionEvent.ACTION, humanOpponentHandler);
		hVsRandom.addEventHandler(ActionEvent.ACTION, randomOpponentHandler);
		hVsGreedy.addEventHandler(ActionEvent.ACTION, greedyOpponentHandler);
		hVsBetter.addEventHandler(ActionEvent.ACTION, betterOpponentHandler);
		
		//Add the restart and undo handler to our button
		restart.addEventHandler(ActionEvent.ACTION, restartHandler);
		undo.addEventHandler(ActionEvent.ACTION, undoInvoker);
		
		
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
