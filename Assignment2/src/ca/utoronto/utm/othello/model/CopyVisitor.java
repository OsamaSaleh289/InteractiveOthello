package ca.utoronto.utm.othello.model;

public class CopyVisitor implements CopyVisitorInterface{
	
	/**
	 * 
	 * @return a copy of this
	 */
	@Override
	public OthelloBoard visit(OthelloBoard board) {
		// TODO Auto-generated method stub
		OthelloBoard ob = new OthelloBoard(board.getDimensions());
		for (int row = 0; row < board.getDimension(); row++) {
			for (int col = 0; col < board.getDimension(); col++) {
				ob.getBoardList()[row][col] = board.getBoardList()[row][col];
			}
		}
		return ob;
		
	}
	
	

}
