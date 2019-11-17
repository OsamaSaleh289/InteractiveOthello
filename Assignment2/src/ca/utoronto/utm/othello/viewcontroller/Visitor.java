package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;

public interface Visitor {
	public void visit(OthelloBoard board);
	public boolean visit(OthelloBoard board, int row, int col, char player);

}
