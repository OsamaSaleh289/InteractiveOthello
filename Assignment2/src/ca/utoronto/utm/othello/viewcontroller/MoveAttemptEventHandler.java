package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MoveAttemptEventHandler extends Observable implements EventHandler<ActionEvent> {
	private Othello othello;
	
	MoveAttemptEventHandler(Othello othello) {
		this.othello = othello;
	}

	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();

		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);

		othello.move(row,col);
		//this.notifyObservers();
	}
}