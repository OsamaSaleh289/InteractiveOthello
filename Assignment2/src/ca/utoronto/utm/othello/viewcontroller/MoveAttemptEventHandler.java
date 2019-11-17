
package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MoveAttemptEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	
	MoveAttemptEventHandler(Othello othello) {
		this.othello = othello;
	}

	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();

		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);

		if((this.othello.getWhosTurn()==this.othello.player1.getPlayer() && this.othello.player1.getStrategyName()=="Human")
				||(this.othello.getWhosTurn()==this.othello.player2.getPlayer() && this.othello.player2.getStrategyName()=="Human")) {
			othello.move(row,col);	
		}
	}
}