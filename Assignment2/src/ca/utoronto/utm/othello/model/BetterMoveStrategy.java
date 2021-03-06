package ca.utoronto.utm.othello.model;

import java.util.ArrayList;

/**
 * A concrete implementation of MoveStrategy. 
 * 
 * This MoveStrategy uses the following algorithm:
 * choose a corner if available,
 * otherwise choose a side if available, 
 * otherwise choose a space that maximizes the players presence in the middle 4 by 4 square of the board,
 * otherwise choose the location maximizing the movers presence on the board. 
 * 
 * @author katri
 *
 */
public class BetterMoveStrategy implements MoveStrategy{
	private Othello othello;

	/**
	 * Constructs a new BetterMoveStrategy
	 * @param othello  The Othello object we are using to run the game
	 */
	public BetterMoveStrategy(Othello othello) {
		this.othello = othello;
	}
	
	/**
	 * @return a Move according to better strategy algorithm
	 */
	public Move getMove() {
		if(this.othello.isGameOver())
			return new Move(-1,-1);
		
		ArrayList<Move> moves = this.othello.allMoves();
		
		// 1. try to choose a corner
		for(Move move: moves) {
			if(move.getRow() == 0 && move.getCol() == 0)
				return move;
			else if(move.getRow() == 0 && move.getCol() == Othello.DIMENSION-1)
				return move;
			else if(move.getRow() == Othello.DIMENSION-1 && move.getCol() == 0)
				return move;
			else if(move.getRow() == Othello.DIMENSION-1 && move.getCol() == Othello.DIMENSION-1)
				return move;
		}
		// 2. try to choose a side if available
		for(Move move: moves) {
			if(move.getRow() == 0 || move.getCol() == 0 || move.getRow() == Othello.DIMENSION-1 || move.getCol() == Othello.DIMENSION-1)
				return move;
		}
		// 3. try to choose a space that maximizes the player's presence in the middle 4x4 squares of the board
		Othello othelloCopy = this.othello.copy();
		Move bestMove = new Move(-1, -1);
		int bestMoveCount = 0;
		
		for(Move move: moves) {
			othelloCopy = this.othello.copy();
			if(othelloCopy.move(move.getRow(), move.getCol()) && othelloCopy.getCount4x4(this.othello.getWhosTurn()) > bestMoveCount) {
				bestMoveCount = othelloCopy.getCount4x4(this.othello.getWhosTurn());
				bestMove = move;
			}
		}
		if(bestMove.getRow()!=-1)
			return bestMove;
		
		// 4. try to choose the location maximizing the movers presence on the board
		return (new GreedyMoveStrategy(this.othello)).getMove();
	}
}