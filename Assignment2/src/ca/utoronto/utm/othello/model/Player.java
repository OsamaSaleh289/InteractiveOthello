package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.Observable;

/**
 * A Player has an Othello game and a player token. It also knows what MoveStrategy it 
 * is using and the String representation of that MoveStrategy. 
 * 
 * @author katri
 *
 */
public class Player extends Observable{
	protected Othello othello;
	protected char player;
	protected String strategyName;
	protected MoveStrategy strategy;

	/**
	 * Constructs a new Player with a null (Human) strategy.
	 * 
	 * @param othello	the Othello game being played
	 * @param player	the player token for this Player
	 */
	public Player(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
		
		this.setStrategy(null);
	}

	/**
	 * Sets the MoveStrategy for this Player
	 * 
	 * @param strategy the MoveStrategy this Player is using
	 */
	public void setStrategy(MoveStrategy strategy) {
		this.strategy = strategy;
		if(strategy == null)
			this.setStrategyName("Human");
		else if(strategy instanceof RandomMoveStrategy) 
			this.setStrategyName("Random");
		else if(strategy instanceof GreedyMoveStrategy) 
			this.setStrategyName("Greedy");
		else if(strategy instanceof BetterMoveStrategy) 
			this.setStrategyName("Better");
	}
	
	/**
	 * Sets the String representation of the MoveStrategy being used
	 * 
	 * @param name  the String representation of the MoveStrategy being used
	 */
	public void setStrategyName(String name) {
		this.strategyName = name;
		this.notifyObservers();
	}
	
	/**
	 * Returns the String representation of this Player's MoveStrategy
	 * 
	 * @return  the String representation of the MoveStrategy being used
	 */
	public String getStrategyName() {
		return this.strategyName;
	}
	
	/**
	 * Returns the token char associated with this Player
	 * 
	 * @return The token representation of this Player
	 */
	public char getPlayer() {
		return this.player;
	}
	
	/**
	 * Returns this player's Move from their MoveStrategy this turn.
	 * 
	 * @return player's Move 
	 */
	public Move getMove() {
		if(this.strategy!=null)
			return this.strategy.getMove();
		return null;
	}
}
