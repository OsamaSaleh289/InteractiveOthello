package ca.utoronto.utm.othello.model;

public abstract class OthelloController {

	protected Othello othello;
	Player player1, player2;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with a user at the console.
	 */
	public OthelloController(Othello othello) {
		this.othello = othello;
	}

	public void play() {
		if (!othello.isGameOver()) {

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if(whosTurn==OthelloBoard.P1)move = player1.getMove();
			if(whosTurn==OthelloBoard.P2)move = player2.getMove();

			if (move != null) {
				othello.move(move.getRow(), move.getCol());
			}
		}
	}
}
