package ca.utoronto.utm.othello.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Run the main from this class to play two humans against eachother. Only
 * minimal modifications to this class are permitted.
 * 
 * @author arnold
 *
 */
public class OthelloControllerHumanVSHuman extends OthelloControllerVerbose implements Observer {

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloControllerHumanVSHuman() {
		super();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
	}

	public Othello getOthello() {
		return this.othello;
	}

	@Override
	public void update(Observable o, Object arg) {
//		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
		this.play();
		// System.out.println("Message: " + o.toString() + " has been " + arg);
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
