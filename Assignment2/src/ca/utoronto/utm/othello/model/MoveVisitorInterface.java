package ca.utoronto.utm.othello.model;

public interface MoveVisitorInterface {
	public boolean visit(OthelloBoard board, int row, int col, char player);

}
