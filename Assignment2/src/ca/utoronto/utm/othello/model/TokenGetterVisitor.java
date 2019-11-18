package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.othello.model.TokenGetterVisitorInterface;
/**
 * 
 * Visits OthelloBoard and extracts the token at a certain position. Implmentation of a visitor
 * 
 * @author Osama Saleh
 */

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
		if (board.validCoordinate(row, col))
			return (board.getBoardList())[row][col];
		else
			return OthelloBoard.EMPTY;
	}
	

}
