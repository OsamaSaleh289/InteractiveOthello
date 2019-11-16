package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AnimationHandler implements EventHandler<ActionEvent>{
	Othello othello;
	
	public AnimationHandler(Othello o) {
		othello = o;
	}

	@Override
	public void handle(ActionEvent buttonClicked) {
		BoardSquare button = (BoardSquare) buttonClicked.getSource();
		char currTokenValue = button.getTokenValue();
		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);
		if (othello.getToken(row, col) != currTokenValue) {
			button.setStyle("-fx-background-color: yellow");
			currTokenValue = othello.getToken(row, col);	
		}	
	}
}
