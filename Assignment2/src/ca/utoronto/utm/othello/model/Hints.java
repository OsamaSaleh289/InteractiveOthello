package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.*;

/**
 * A Hint uses MoveStrategys to give the user hints about different moves they could make.
 *
 */
public class Hints extends Observable implements Observer {
	protected Othello othello;
	private boolean randomHintOn = false;
	private boolean greedyHintOn = false;
	private boolean betterHintOn = false;
	private Move greedyHint = null;
	private Move randomHint = null;
	private Move betterHint = null;
	
	/**
	 * Constructs the hints and stores whether they are on or not. 
	 */
	public Hints(Othello othello) {
		this.othello = othello;
	}
	
	public void toggleGreedyHint() {
		if(this.greedyHintOn) {
			this.greedyHintOn = false;
			this.greedyHint = null;
		} else {
			this.greedyHintOn = true;
			this.greedyHint = (new GreedyMoveStrategy(this.othello)).getMove();
		}
		this.notifyObservers();
	}

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

	public void toggleBetterHint() {
		if(this.betterHintOn) {
			this.betterHintOn = false;
			this.betterHint = null;
		} else {
			this.betterHintOn = true;
			this.betterHint = (new BetterMoveStrategy(this.othello)).getMove();
		}
		this.notifyObservers();
	}
	
	public boolean isGreedyHintOn() {
		return this.greedyHintOn;
	}
	public boolean isRandomHintOn() {
		return this.randomHintOn;
	}
	public boolean isBetterHintOn() {
		return this.betterHintOn;
	}
	
	public boolean isButtonGreedyHint(int row, int col) {
		return this.greedyHintOn && (this.greedyHint.getRow()==row && this.greedyHint.getCol()==col);
	}
	public boolean isButtonRandomHint(int row, int col) {
		return this.randomHintOn && (this.randomHint.getRow()==row && this.randomHint.getCol()==col);
	}
	public boolean isButtonBetterHint(int row, int col) {
		return this.betterHintOn && (this.betterHint.getRow()==row && this.betterHint.getCol()==col);
	}

	@Override
	public void update(Observable o) {
		if (!othello.isGameOver()) {		
			if(this.randomHintOn)
				this.randomHint = (new RandomMoveStrategy(this.othello)).getMove();
			if(this.greedyHintOn)
				this.greedyHint = (new GreedyMoveStrategy(this.othello)).getMove();
			if(this.greedyHintOn) 
				this.betterHint = (new BetterMoveStrategy(this.othello)).getMove();
		}
	}
}
