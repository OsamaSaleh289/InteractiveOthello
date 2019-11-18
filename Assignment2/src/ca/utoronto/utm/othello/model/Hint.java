package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.Observable;

/**
 * A Hint object. Each Hint uses a MoveStrategy to give the hint.
 * A Hint has a MoveStrategy and knows whether it is turned on or off.
 * It also stores a Move hint for each turn.
 * 
 * @author katri
 *
 */
public class Hint extends Observable{
	private MoveStrategy strategy;
	private boolean isOn;
	private Move hint;
	
	/**
	 * 
	 * @param strategy The MoveStrategy this Hint will use to generate the hints
	 */
	public Hint(MoveStrategy strategy) {
		this.setStrategy(strategy);
		this.isOn = false;
	}

	/**
	 * Sets the strategy for this hint.
	 * 
	 * @param strategy  The MoveStrategy this Hint will use to generate the hints
	 */
	private void setStrategy(MoveStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Sets the Move hint for this turn
	 */
	public void setHint() {
		this.hint = this.strategy.getMove();
	}
	
	/**
	 * Returns whether or not this Hint is on
	 * 
	 * @return  true if on, false if off
	 */
	public boolean isHintOn() {
		return this.isOn;
	}

	/**
	 * Returns whether or not this Hint is on this BoardSquare
	 * 
	 * @param row  The row of the BoardSquare this is being called on
	 * @param col  The column of the BoardSquare this is being called on
	 * @return  true if this Hint is for this BoardSquare
	 */
	public boolean isButtonHint(int row, int col) {
		return this.isHintOn() && this.hint.getRow()==row && this.hint.getCol()==col;
	}
	
	/**
	 * Turns this Hint on or off and changes the Move hint accordingly
	 */
	public void toggleHint() {
		if(this.isHintOn()) {
			this.isOn = false;
			this.hint = null;
		} else {
			this.isOn = true;
			this.setHint();
		}
		this.notifyObservers();
	}
}
