package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.Player;
import ca.utoronto.utm.othello.model.RandomMoveStrategy;
import ca.utoronto.utm.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TurnEventHandler extends Observable implements EventHandler<ActionEvent> {
	protected Othello othello;
	public Player player1, player2;
	private Move humanMove;
	//public boolean randomHintOn = false;
	//public Move randomHint = null;
	//public boolean greedyHintOn = false;
	//public boolean betterHintOn = false;
	
	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public TurnEventHandler(Othello othello, Player player1, Player player2) {
		this.othello = othello;
		this.player1 = player1;
		this.player2 = player2;
		this.humanMove = null;
	}
	public TurnEventHandler(Othello othello, Player player1, Player player2, Move move) {
		this.othello = othello;
		this.player1 = player1;
		this.player2 = player2;
		this.humanMove  = move;
	}

	/*public void toggleRandomHint() {
		if(this.randomHintOn) {
			this.randomHintOn = false;
			this.randomHint = null;
		} else {
			this.randomHintOn = true;
			this.randomHint = (new RandomMoveStrategy(this.othello)).getMove();
		}
		this.notifyObservers();
	}

	public void toggleGreedyHint() {
		if(this.greedyHintOn) {
			this.greedyHintOn = false;
		} else {
			this.greedyHintOn = true;
		}
		this.notifyObservers();
	}

	public void toggleBetterHint() {
		if(this.betterHintOn) {
			this.betterHintOn = false;
		} else {
			this.betterHintOn = true;
		}
		this.notifyObservers();
	}*/
	
	@Override
	public void handle(ActionEvent arg0) {
		if (!othello.isGameOver()) {			
			/*if(this.randomHintOn) {
				this.randomHint = (new RandomMoveStrategy(this.othello)).getMove();
			}*/

			Move move = null;
			char whosTurn = othello.getWhosTurn();
			
			if(this.humanMove!=null && whosTurn==player1.getPlayer() && player1.getStrategyName()=="Human")
				othello.move(this.humanMove.getRow(), this.humanMove.getCol());
			else if(this.humanMove!=null && whosTurn==player2.getPlayer() && player2.getStrategyName()=="Human")
				othello.move(this.humanMove.getRow(), this.humanMove.getCol());
			
			if(whosTurn==OthelloBoard.P1)move = player1.getMove();
			else if(whosTurn==OthelloBoard.P2)move = player2.getMove();

			if (move != null) {
				othello.move(move.getRow(), move.getCol());
			}
		}
	}

}
