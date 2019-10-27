package ca.utoronto.utm.othello.model;
import ca.utoronto.utm.util.*;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over.
 * 
 * See the following for a short, simple introduction.
 * https://www.youtube.com/watch?v=Ol3Id7xYsY4
 * 
 * @author arnold
 *
 */
public class Othello extends Observable{
	public static final int DIMENSION=8; // This is an 8x8 game

	private OthelloBoard board=new OthelloBoard(Othello.DIMENSION);
	private char whosTurn = OthelloBoard.P1;
	private int numMoves = 0;
	private int newr = 0;
	private int newc = 0;

	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() {
		return this.whosTurn;
	}
	
	public Move getNewMove() {
//		this.notifyObservers();
		return new Move(this.newr, this.newc);
		
	}
	
	
	/**
	 * 
	 * @param row 
	 * @param col
	 * @return the token at position row, col.
	 */
	public char getToken(int row, int col) {
		return this.board.get(row, col);
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.
	 * 
	 * @param row
	 * @param col
	 * @return whether the move was successfully made.
	 */
	public boolean move(int row, int col) {
		if(this.board.move(row, col, this.whosTurn)) {
			this.whosTurn = OthelloBoard.otherPlayer(this.whosTurn);
			char allowedMove = board.hasMove();
			if(allowedMove!=OthelloBoard.BOTH)this.whosTurn=allowedMove;
			this.numMoves++;
//			this.notifyObservers();
			return true;
		} else {
			return false;
		}
	}
	
//	public Move getMove() {
////		int row = getMove("row: ");
////		int col = getMove("col: ");
//		Move move = 
//		return new Move(row, col);
//	}

//	public int getMove(String message) {
//		int move, lower = 0, upper = 7;
//		while (true) {
//			try {
//				System.out.print(message);
//				String line = PlayerHuman.stdin.readLine();
//				move = Integer.parseInt(line);
//				if (lower <= move && move <= upper) {
//					return move;
//				} else {
//					System.out.println(INVALID_INPUT_MESSAGE);
//				}
//			} catch (IOException e) {
//				System.out.println(INVALID_INPUT_MESSAGE);
//				break;
//			} catch (NumberFormatException e) {
//				System.out.println(INVALID_INPUT_MESSAGE);
//			}
//		}
//		return -1;
//	}
	
//	public Move getMove() {
//		int row = getMove("row: ");
//		int col = getMove("col: ");
//		return new Move(row, col);
//	}
//	
	public void getMove(String r,String c) {
		this.newr = Integer.parseInt(r);
		this.newc = Integer.parseInt(c);
		this.notifyObservers();
		System.out.println("Enter here");
		
//		return new Move(row, col);
}


	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {
		return board.getCount(player);
	}


	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	public char getWinner() {
		if(!this.isGameOver())return OthelloBoard.EMPTY;
		if(this.getCount(OthelloBoard.P1)> this.getCount(OthelloBoard.P2))return OthelloBoard.P1;
		if(this.getCount(OthelloBoard.P1)< this.getCount(OthelloBoard.P2))return OthelloBoard.P2;
		return OthelloBoard.EMPTY;
	}


	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		return this.whosTurn==OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return a copy of this. The copy can be manipulated without impacting this.
	 */
	public Othello copy() {
		Othello o= new Othello();
		o.board=this.board.copy();
		o.numMoves = this.numMoves;
		o.whosTurn = this.whosTurn;
		return o;
	}


	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString() {
		return board.toString()+"\n";
	}


	/**
	 * run this to test the current class. We play a completely random game. DO NOT
	 * MODIFY THIS!! See the assignment page for sample outputs from this.
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
		Random rand = new Random();


		Othello o = new Othello();
		System.out.println(o.getBoardString());
		while(!o.isGameOver()) {
			int row = rand.nextInt(8);
			int col = rand.nextInt(8);

			if(o.move(row, col)) {
				System.out.println("makes move ("+row+","+col+")");
				System.out.println(o.getBoardString()+ o.getWhosTurn()+" moves next");
			}
		}

	}
	
	public synchronized void addObserver(Observer o) {
		// Here we use "synchronized" because there could be multiple observers 
		// being added to the same observable concurrently, synchronization is 
		// needed to prevent concurrency issues.
		
		super.addObserver(o);
		
		// What's the difference if just use the parent's implementation 
		// without adding the following two lines.
		this.setChanged();
		this.notifyObservers("observed");
	}
	
}


