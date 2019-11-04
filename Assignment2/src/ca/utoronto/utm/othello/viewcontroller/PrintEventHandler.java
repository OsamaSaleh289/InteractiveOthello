package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerHuman;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PrintEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private GridPane grid;

	PrintEventHandler(Othello othello, GridPane grid) {
		this.othello = othello;
		this.grid = grid;
	}

	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();

		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);

		othello.setMove(row, col);

		for (int child = 0; child < 64; child++) {
			Button bttn = (Button) grid.getChildren().get(child);
			int r = GridPane.getRowIndex(bttn);
			int c = GridPane.getColumnIndex(bttn);

			bttn.setGraphic(this.othello.getImage(r, c));
		}

	}
}