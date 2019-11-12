package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.PlayerHuman;
import ca.utoronto.utm.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MoveAttemptEventHandler extends Observable implements EventHandler<ActionEvent> {
	private Othello othello;
	private GridPane grid;

	MoveAttemptEventHandler(Othello othello, GridPane grid) {
		this.othello = othello;
		this.grid = grid;
	}

	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();

		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);

		//othello.setMove(row, col);
		othello.move(row,col);
		this.notifyObservers();
	}
}