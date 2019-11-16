package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.OthelloController;
import ca.utoronto.utm.othello.model.RandomMoveStrategy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RandomOpponentEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private OthelloController oc;
	private TimeTracker timer;
	
	public RandomOpponentEventHandler(Othello othello, TimeTracker timer, OthelloController oc) {
		this.othello = othello;
		this.oc = oc;
		this.timer = timer;
	}
	
	public void handle(ActionEvent event) {
		
		if(this.othello.getWhosTurn()==OthelloBoard.P1)
			this.oc.player2.setStrategy(new RandomMoveStrategy(this.othello));
		else if(this.othello.getWhosTurn()==OthelloBoard.P2)
			this.oc.player1.setStrategy(new RandomMoveStrategy(this.othello));
			
		timer.startTimer();
	}
}
