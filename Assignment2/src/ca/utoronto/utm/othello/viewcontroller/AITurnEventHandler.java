package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AITurnEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	
	/**
	 * 
	 */
	public AITurnEventHandler(Othello othello) {
		this.othello = othello;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		if (!othello.isGameOver()) {			
			Move move = null;
			char whosTurn = othello.getWhosTurn();
			
			if(whosTurn==OthelloBoard.P1)move = this.othello.player1.getMove();
			else if(whosTurn==OthelloBoard.P2)move = this.othello.player2.getMove();

			if (move != null) {
				othello.move(move.getRow(), move.getCol());
			}
		}
	}
}
