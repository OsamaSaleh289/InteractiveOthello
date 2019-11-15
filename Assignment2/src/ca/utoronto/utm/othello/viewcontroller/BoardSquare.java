package ca.utoronto.utm.othello.viewcontroller;

import java.util.concurrent.TimeUnit;

import ca.utoronto.utm.othello.model.Move;
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
	private char prevTokenValue;
	private int currNumTokens;
	public BoardSquare(Othello othello, int row, int col) {
		this.setText("");
		this.othello = othello;
		this.setGraphic(othello.getImage(row,col));
		prevTokenValue = othello.getToken(row, col);
		currNumTokens = othello.getCount('X') + othello.getCount('O'); 
		
	}
	
	@Override
	public void update(Observable o) {
		//We reset the color of the token if a different move has been made or we restarted the game
		if (othello.getCount('X') + othello.getCount('O') > currNumTokens || othello.getCount('X') + othello.getCount('O') == 4) {
			this.setStyle("");
			
		}
		othello = (Othello) o;
		int row = GridPane.getRowIndex(this);
		int col = GridPane.getColumnIndex(this);
		currTokenValue = othello.getToken(row, col);
		this.setGraphic(othello.getImage(row,col));
		//The second condition is to make sure our boxes don't get colored yellow 
		//when we change values of board squares due to restarting the game
		if (currTokenValue != prevTokenValue && othello.getCount('X') + othello.getCount('O') != 4) {
			if (currTokenValue == 'X') {
				this.setStyle("-fx-background-color: yellow");
			} else {
				this.setStyle("-fx-background-color: red");
			}
			prevTokenValue = currTokenValue;
			currNumTokens = othello.getCount('X') + othello.getCount('O');
			
			
			
		}
		
		
	}
	

}
