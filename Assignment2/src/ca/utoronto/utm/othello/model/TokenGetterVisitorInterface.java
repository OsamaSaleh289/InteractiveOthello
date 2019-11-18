package ca.utoronto.utm.othello.model;

public interface TokenGetterVisitorInterface {
	public char visit(OthelloBoard board, int row, int col);

}
