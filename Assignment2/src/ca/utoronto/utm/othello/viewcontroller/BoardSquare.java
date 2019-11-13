package ca.utoronto.utm.othello.viewcontroller;

import java.util.concurrent.TimeUnit;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BoardSquare extends Button implements Observer{
	private Othello othello;
	int count = 0;
	private char currTokenValue;
	
	public BoardSquare(Othello othello, int row, int col) {
		this.setText("");
		this.setGraphic(othello.getImage(row,col));
		currTokenValue = othello.getToken(row, col);
		
	}
	
	@Override
	public void update(Observable o) {
		othello = (Othello) o;
		int row = GridPane.getRowIndex(this);
		int col = GridPane.getColumnIndex(this);
		this.setGraphic(this.othello.getImage(row,col));
		if (othello.getToken(row, col) != currTokenValue) {
			this.setStyle("-fx-background-color: yellow");
	
			
			
		}
		
		
	}

}
