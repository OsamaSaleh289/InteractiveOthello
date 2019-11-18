package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.Hints;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BoardSquare extends Button implements Observer{
	private Othello othello;
	private Hints hints;
	private char prevTokenValue;
	
	public BoardSquare(Othello othello, int row, int col, Hints hints) {
		this.setText("");
		this.othello = othello;
		this.hints = hints;
		this.setGraphic(othello.getImage(row,col));
		
		this.prevTokenValue = othello.getToken(row, col);
		
		HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.hints);
		if(hintHighlight.getColor()!=null)
			this.setEffect(hintHighlight);
	}
	
	@Override
	public void update(Observable o) {
		int row = GridPane.getRowIndex(this);
		int col = GridPane.getColumnIndex(this);
		this.setGraphic(this.othello.getImage(row,col));
		this.setEffect(null);
		
		if (this.othello.getCount('X') == 2) {
			this.prevTokenValue = this.othello.getToken(row, col);
			
		}
		
		if (othello.getToken(row, col) != this.prevTokenValue && othello.getToken(row, col) != ' ') {
			FadeTransition ft = new FadeTransition(Duration.millis(2000), this);
			ft.setFromValue(0);
			ft.setToValue(1);
			if((this.othello.getWhosTurn()==this.othello.player1.getPlayer() && this.othello.player1.getStrategyName()!="Human")
					|| (this.othello.getWhosTurn()==this.othello.player2.getPlayer() && this.othello.player2.getStrategyName()!="Human"))
				ft.setOnFinished(new AITurnEventHandler(this.othello));
			ft.play();
			InnerShadow inS = new InnerShadow();
			inS.setOffsetX(0f);
			inS.setOffsetY(0f);
			inS.setWidth(25);
			inS.setHeight(25);
			inS.setColor(Color.BLACK);
			this.setEffect(inS);
			
			this.prevTokenValue = othello.getToken(row, col);
		}
		
		if(!this.othello.isGameOver()) {
			HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.hints);
			if(hintHighlight.getColor()!=null) {
				this.setEffect(hintHighlight);
			}
		}
	}
}
