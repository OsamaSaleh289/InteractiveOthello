package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

class ButtonPressEventHandler implements EventHandler<ActionEvent> {
//	private Othello othello;
//	public ButtonPressEventHandler(Othello othello) {
//		this.othello = othello;
//	}
	public void handle(ActionEvent event) {
		Button button = (Button)event.getSource();

		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);

	}
}