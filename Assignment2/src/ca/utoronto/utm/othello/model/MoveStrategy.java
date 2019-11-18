package ca.utoronto.utm.othello.model;

/**
 * The interface for the Move strategies.
 * Each concrete MoveStrategy uses an algorithm to generate a Move.
 * 
 * @author katri
 *
 */
public interface MoveStrategy {

	/**
	 * Will return a Move according to the strategy implemented
	 * @return the Move according to the strategy implemented
	 */
	public abstract Move getMove();
}
