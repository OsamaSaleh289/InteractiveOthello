package ca.utoronto.utm.othello.model;

public interface MoveVisitorInterface {
	public void visit(OthelloBoard board);
	public boolean visit(OthelloBoard board, int row, int col, char player);

}
