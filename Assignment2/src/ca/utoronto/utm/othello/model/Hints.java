package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.*;

/**
 * Hints create the hints for the game. 
 * Each Hint can be turned on or off, and if on, each Hint updates each turn. 
 * 
 * @author katri
 *
 */
public class Hints implements Observer {
	private Othello othello;
	public Hint randomHint;
	public Hint greedyHint;
	public Hint betterHint;

	/**
	 * Constructs the hints and stores whether they are on or not. 
	 */
	public Hints(Othello othello) {
		this.othello = othello;
		this.randomHint = new Hint(new RandomMoveStrategy(this.othello));
		this.greedyHint = new Hint(new GreedyMoveStrategy(this.othello));
		this.betterHint = new Hint(new BetterMoveStrategy(this.othello));
	}

	/**
	 * updates each Hint each turn, if on
	 */
	@Override
	public void update(Observable o) {
		if (!othello.isGameOver()) {		
			if(this.randomHint.isHintOn())
				this.randomHint.setHint();
			if(this.greedyHint.isHintOn())
				this.greedyHint.setHint();
			if(this.betterHint.isHintOn())
				this.betterHint.setHint();
		}
	}
}
