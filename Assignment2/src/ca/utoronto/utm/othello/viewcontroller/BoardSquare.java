package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloController;
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
	private OthelloController oc;
	private char prevTokenValue;
	
	public BoardSquare(Othello othello, int row, int col, OthelloController oc) {
		this.setText("");
		this.othello = othello;
		this.oc = oc;
		this.setGraphic(othello.getImage(row,col));
		
		this.prevTokenValue = othello.getToken(row, col);
		
		HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.oc);
		if(hintHighlight.getColor()!=null)
			this.setEffect(hintHighlight);
	}
	
	@Override
	public void update(Observable o) {
		int row = GridPane.getRowIndex(this);
		int col = GridPane.getColumnIndex(this);
		this.setGraphic(this.othello.getImage(row,col));
		this.setEffect(null);
		
		if (othello.getToken(row, col) != this.prevTokenValue && othello.getToken(row, col) != ' ') {
			InnerShadow inS = new InnerShadow();
			inS.setOffsetX(0f);
			inS.setOffsetY(0f);
			inS.setWidth(25);
			inS.setHeight(25);
			inS.setColor(Color.BLACK);
			this.setEffect(inS);
			FadeTransition ft = new FadeTransition(Duration.millis(2000), this);
			ft.setFromValue(0);
			ft.setToValue(1);
			
			if(this.othello.getWhosTurn()==this.oc.player1.getPlayer() && this.oc.player1.getStrategyName()!="Human")
				ft.setOnFinished(new TurnEventHandler(this.othello, this.oc.player1, this.oc.player2));
			else if(this.othello.getWhosTurn()==this.oc.player2.getPlayer() && this.oc.player2.getStrategyName()!="Human")
				ft.setOnFinished(new TurnEventHandler(this.othello, this.oc.player1, this.oc.player2));
				
			ft.play();
			this.prevTokenValue = othello.getToken(row, col);
		}
		
		HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.oc);
		if(hintHighlight.getColor()!=null && !this.othello.isGameOver()) {
			this.setEffect(hintHighlight);
		}
	}
}
