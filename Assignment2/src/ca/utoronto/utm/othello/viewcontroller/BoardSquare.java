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

/**
 * A BoardSquare is a Button object that knows how to update to reflect
 * the changes to Othello and Hints to represent an OthelloBoard square.
 * 
 * @author katri
 *
 */
public class BoardSquare extends Button implements Observer{
	private Othello othello;
	private Hints hints;
	private char prevTokenValue;
	
	/**
	 * Constructs a Button to represent an Othello game BoardSquare
	 * 
	 * @param othello  the current Othello game
	 * @param row  the row of this BoardSquare
	 * @param col  the column of this BoardSquare
	 * @param hints  the Hints for this game
	 */
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
	
	/**
	 * Updates the BoardSquare to reflect any changes
	 */
	@Override
	public void update(Observable o) {
		int row = GridPane.getRowIndex(this);
		int col = GridPane.getColumnIndex(this);
		this.setGraphic(this.othello.getImage(row,col));
		this.setEffect(null);
		
		// if game restarted, we don't want the animation playing the changes
		if(this.othello.getCount('X')==2)
			this.prevTokenValue = this.othello.getToken(row, col);
		
		// move animations
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
		
		// hints
		if(!this.othello.isGameOver()) {
			HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.hints);
			if(hintHighlight.getColor()!=null) {
				this.setEffect(hintHighlight);
			}
		}
	}
}
