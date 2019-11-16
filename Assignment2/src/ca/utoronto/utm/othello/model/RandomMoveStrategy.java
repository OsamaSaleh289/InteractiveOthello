package ca.utoronto.utm.othello.model;

import java.util.ArrayList;
import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy{
	protected Othello othello;
	private Random rand = new Random();

	public RandomMoveStrategy(Othello othello) {
		this.othello = othello;
	}
	
	public Move getMove() {
		if(this.othello.isGameOver())
			return new Move(-1,-1);
		
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				Othello othelloCopy = othello.copy();
				if (othelloCopy.move(row, col))
					moves.add(new Move(row, col));
			}
		}
		return moves.get(this.rand.nextInt(moves.size()));
	}

}
