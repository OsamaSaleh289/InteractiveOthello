package ca.utoronto.utm.othello.model;

public abstract class OthelloController {

	protected Othello othello;
	Player player1, player2;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with a user at the console.
	 */
	public OthelloController() {
		this.othello = new Othello();
	}

	public void play() {
		if (!othello.isGameOver()) {
//			System.out.println("CCCCCC");
//			this.report();

			Move move = null;
			char whosTurn = othello.getWhosTurn();

//			if(whosTurn==OthelloBoard.P1)move = player1.getMove();
//			if(whosTurn==OthelloBoard.P2)move = player2.getMove();
			move = othello.getMove();
//			System.out.println(move);
			if (move != null) {
				// this.reportMove(whosTurn, move);
				othello.move(move.getRow(), move.getCol());
			}
			// this.report();
//			this.reportMove(whosTurn, move);
//			othello.move(move.getRow(), move.getCol());
		} else {
			// this.reportFinal();
		}
//		this.reportFinal();
	}

	protected void reportMove(char whosTurn, Move move) {
	}

	protected void report() {
	}

	protected void reportFinal() {
	}

	public void update() {
		// TODO Auto-generated method stub

	}
}
