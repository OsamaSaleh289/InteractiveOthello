package ca.utoronto.utm.othello.model;

public class HumanMoveStrategy implements MoveStrategy{
	protected Othello othello;

	public HumanMoveStrategy(Othello othello) {
		this.othello = othello;
	}
	
	public Move getMove() {
		return null;
	}
}
