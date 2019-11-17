package ca.utoronto.utm.othello.model;

public class GreedyMoveStrategy  implements MoveStrategy{
	private Othello othello;

	public GreedyMoveStrategy(Othello othello) {
		this.othello = othello;
	}
	
	public Move getMove() {
		if(this.othello.isGameOver())
			return new Move(-1,-1);
		
		Othello othelloCopy = this.othello.copy();
		Move bestMove = new Move(0, 0); 
		int bestMoveCount = othelloCopy.getCount(this.othello.getWhosTurn());
		
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				othelloCopy = this.othello.copy();
				if (othelloCopy.move(row, col) && othelloCopy.getCount(this.othello.getWhosTurn()) > bestMoveCount) {
					bestMoveCount = othelloCopy.getCount(this.othello.getWhosTurn());
					bestMove = new Move(row, col);
				}
			}
		}
		return bestMove;
	}
}
