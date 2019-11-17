package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.Observable;

public class Player extends Observable{
	protected Othello othello;
	protected char player;
	protected String strategyName;
	protected MoveStrategy strategy;

	public Player(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
		
		this.setStrategy(null);
	}

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
	
	public void setStrategyName(String name) {
		this.strategyName = name;
		this.notifyObservers();
	}
	
	public String getStrategyName() {
		return this.strategyName;
	}
	
	public char getPlayer() {
		return this.player;
	}
	
	public Move getMove() {
		if(this.strategy!=null)
			return this.strategy.getMove();
		return null;
	}
}
