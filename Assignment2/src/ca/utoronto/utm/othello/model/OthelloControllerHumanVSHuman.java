package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.*;

/**
 * Run the main from this class to play two humans against eachother. Only
 * minimal modifications to this class are permitted.
 * 
 * @author arnold
 *
 */
public class OthelloControllerHumanVSHuman extends OthelloController implements Observer {

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloControllerHumanVSHuman(Othello othello) {
		super(othello);
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
	}

	@Override
	public void play() {
		if (!othello.isGameOver()) {

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if(this.othello.getOpponent(player2.getPlayer())!=player1.getType()) {
				if(this.othello.getOpponent(player2.getPlayer())=="Human")
					this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
				else if(this.othello.getOpponent(player2.getPlayer())=="Random")
					this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
				else if(this.othello.getOpponent(player2.getPlayer())=="Greedy")
					this.player1 = new PlayerGreedy(this.othello, OthelloBoard.P1);
			}
			if(this.othello.getOpponent(player1.getPlayer())!=player2.getType()) {
				if(this.othello.getOpponent(player1.getPlayer())=="Human")
					this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
				else if(this.othello.getOpponent(player1.getPlayer())=="Random")
					this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
				else if(this.othello.getOpponent(player1.getPlayer())=="Greedy")
					this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
			}
			
			if(whosTurn==OthelloBoard.P1)move = player1.getMove();
			if(whosTurn==OthelloBoard.P2)move = player2.getMove();

			if (move != null) {
				othello.move(move.getRow(), move.getCol());
			}
		}
	}
	
	public Othello getOthello() {
		return this.othello;
	}

	@Override
	public void update(Observable o) {
		this.play();
	}
	/**
	 * Run main to play two Humans against each other at the console.
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
//		oc.play();
//	}

}
