package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.*;

/**
 * An OthelloController runs the game and updates each turn
 *
 */
public class OthelloController extends Observable implements Observer {
	protected Othello othello;
	public Player player1, player2;
	public boolean randomHintOn = false;
	public Move randomHint = null;
	public boolean greedyHintOn = false;
	
	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloController(Othello othello) {
		this.othello = othello;
		this.player1 = new Player(this.othello, OthelloBoard.P1);
		this.player2 = new Player(this.othello, OthelloBoard.P2);
	}

	/**
	 * 
	 */
	public void toggleRandomHint() {
		if(this.randomHintOn) {
			this.randomHintOn = false;
			this.randomHint = null;
		} else {
			this.randomHintOn = true;
			this.randomHint = (new RandomMoveStrategy(this.othello)).getMove();
		}
		this.notifyObservers();
	}
	
	/**
	 * 
	 */
	public void toggleGreedyHint() {
		if(this.greedyHintOn) {
			this.greedyHintOn = false;
		} else {
			this.greedyHintOn = true;
		}
		this.notifyObservers();
	}
	
	public void play() {
		if (!othello.isGameOver()) {
			if(this.randomHintOn) {
				this.randomHint = (new RandomMoveStrategy(this.othello)).getMove();
			}

			Move move = null;
			char whosTurn = othello.getWhosTurn();
			
			if(whosTurn==OthelloBoard.P1)move = player1.getMove();
			if(whosTurn==OthelloBoard.P2)move = player2.getMove();

			if (move != null) {
				othello.move(move.getRow(), move.getCol());
			}
		}
	}

	@Override
	public void update(Observable o) {
		this.play();
	}
}
