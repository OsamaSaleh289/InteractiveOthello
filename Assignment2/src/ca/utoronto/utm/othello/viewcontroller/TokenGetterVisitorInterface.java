package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloBoard;

public interface TokenGetterVisitorInterface {
	public char visit(OthelloBoard board, int row, int col);

}
