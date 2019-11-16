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
		this.strategy = new HumanMoveStrategy(this.othello);
		this.strategyName = "Human";
	}

	public void setStrategy(MoveStrategy strategy) {
		this.strategy = strategy;
		if(strategy instanceof HumanMoveStrategy)
			this.setStrategyName("Human");
		else if(strategy instanceof RandomMoveStrategy) 
			this.setStrategyName("Random");
		else if(strategy instanceof GreedyMoveStrategy) 
			this.setStrategyName("Greedy");
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
		return this.strategy.getMove();
	}
}
