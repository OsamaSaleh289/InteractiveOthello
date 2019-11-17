package ca.utoronto.utm.othello.viewcontroller;

import java.time.Duration;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloController;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardSquare extends Button implements Observer{
	private Othello othello;
	private OthelloController oc;
	private char currTokenValue;
	private char prevTokenValue;
	private int currNumTokens;
	
	public BoardSquare(Othello othello, int row, int col, OthelloController oc) {
		this.setText("");
		this.othello = othello;
		this.oc = oc;
		this.setGraphic(othello.getImage(row,col));
		
		prevTokenValue = othello.getToken(row, col);
		currNumTokens = othello.getCount('X') + othello.getCount('O'); 
		
		HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.oc);
		if(hintHighlight.getColor()!=null)
			this.setEffect(hintHighlight);
	}
	
	@Override
	public void update(Observable o) {
		int row = GridPane.getRowIndex(this);
		int col = GridPane.getColumnIndex(this);
		

		HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.oc);
		if(hintHighlight.getColor()!=null && !this.othello.isGameOver()) {
			this.setEffect(hintHighlight);
		}
		
		//We reset the color of the coloured tokens if a different move has been made
		if (othello.getCount('X') + othello.getCount('O') > currNumTokens) {
			this.setStyle(""); 
					
		//We also reset the token's colours and our varibables if the game has been restarted
		} else if ( othello.getCount('X') + othello.getCount('O') == 4) {
			this.setStyle(""); 
			prevTokenValue = othello.getToken(row, col);
			currNumTokens = othello.getCount('X') + othello.getCount('O');
					
		}
		
		currTokenValue = othello.getToken(row, col);
		this.setGraphic(othello.getImage(row,col));
		//The second condition is to make sure our boxes don't get colored  
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
