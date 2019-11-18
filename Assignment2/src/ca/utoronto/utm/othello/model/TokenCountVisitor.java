package ca.utoronto.utm.othello.model;

/**
 * 
 * Visits OthelloBoard and extracts the token count for a certain player. Implmentation of a visitor
 * 
 * @author Osama Saleh
 */

public class TokenCountVisitor implements TokenCountVisitorInterface{
	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	@Override
	public int visit(OthelloBoard board, char player) {
		// TODO Auto-generated method stub
		int count = 0;
		for (int row = 0; row < board.getDimension(); row++) {
			for (int col = 0; col < board.getDimension(); col++) {
				if (board.get(row, col) == player)
					count++;
			}
		}
		return count;
	}

}
