package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.OthelloBoard;

public class FourxFour_TokenCountVisitor implements CounterVisitorInterface{
	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	@Override
	public int visit(OthelloBoard board, char player) {
		// TODO Auto-generated method stub
		int count = 0;
		ArrayList<Integer> centre = new ArrayList<Integer>();
		centre.add(2);
		centre.add(3);
		centre.add(4);
		centre.add(5);
		for (int row = 0; row < board.getDimensions(); row++) {
			for (int col = 0; col < board.getDimensions(); col++) {
				if (board.get(row, col) == player && centre.contains(row) && centre.contains(col))
					count++;
			}
		}
		return count;
	}
	
	

}
