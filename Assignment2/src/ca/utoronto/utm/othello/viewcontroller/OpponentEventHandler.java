package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.BetterMoveStrategy;
import ca.utoronto.utm.othello.model.GreedyMoveStrategy;
import ca.utoronto.utm.othello.model.MoveStrategy;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.RandomMoveStrategy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class OpponentEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	
	public OpponentEventHandler(Othello othello) {
		this.othello = othello;
	}
	
	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();
		
		MoveStrategy moveStrategy = null;
		if(button.getText()=="Human vs. Random")
			moveStrategy = new RandomMoveStrategy(this.othello);
		else if(button.getText()=="Human vs. Greedy")
			moveStrategy = new GreedyMoveStrategy(this.othello);
		else if(button.getText()=="Human vs. Better")
			moveStrategy = new BetterMoveStrategy(this.othello);
		
		if(this.othello.getWhosTurn()==OthelloBoard.P1)
			this.othello.player2.setStrategy(moveStrategy);
		else if(this.othello.getWhosTurn()==OthelloBoard.P2)
			this.othello.player1.setStrategy(moveStrategy);
	}
}
