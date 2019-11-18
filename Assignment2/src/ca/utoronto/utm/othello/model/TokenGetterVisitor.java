package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.othello.model.TokenGetterVisitorInterface;

public class TokenGetterVisitor implements TokenGetterVisitorInterface{
	
	/**
	 * 
	 * @param board
	 * @param row
	 * @param col
	 * 
	 * 
	 * Return the token at position (row, col)
	 */
	@Override
	public char visit(OthelloBoard board, int row, int col) {
		// TODO Auto-generated method stub
		if (board.validCoordinate(row, col))
			return (board.getBoardList())[row][col];
		else
			return OthelloBoard.EMPTY;
	}
	

}
