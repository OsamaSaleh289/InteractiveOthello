package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.viewcontroller.TokenGetterVisitorInterface;

public class TokenVisitor implements TokenGetterVisitorInterface{

	@Override
	public char visit(OthelloBoard board, int row, int col) {
		// TODO Auto-generated method stub
		if (board.validCoordinate(row, col))
			return (board.getBoardList())[row][col];
		else
			return OthelloBoard.EMPTY;
	}
	

}
