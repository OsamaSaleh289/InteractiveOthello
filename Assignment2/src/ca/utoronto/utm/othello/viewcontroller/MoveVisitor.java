package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;

public class MoveVisitor implements Visitor{
	private boolean boardMoveSuccess;

	@Override
	public boolean visit(OthelloBoard board, int row, int col, char player) {
		if (!board.validCoordinate(row, col))
			return false;
		if (board.get(row, col) != OthelloBoard.EMPTY)
			return false;

		int numChangedTotal = 0;

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				if (drow == 0 && dcol == 0)
					continue;
				int numChanged = board.flip(row + drow, col + dcol, drow, dcol, player);
				if (numChanged >= 0)
					numChangedTotal += numChanged;
			}
		}
		if (numChangedTotal > 0) {
			board.setCoordinate(row, col, player);;
			return true;
		}
		return false;
	}


	@Override
	public void visit(OthelloBoard board) {
		// TODO Auto-generated method stub
		
	}

	


}
