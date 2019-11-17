package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.OthelloBoard;

public interface CounterVisitorInterface {
	public int visit(OthelloBoard board, char player);
	

}
