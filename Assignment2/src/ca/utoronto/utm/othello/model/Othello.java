package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.othello.viewcontroller.OthelloApplication;
import ca.utoronto.utm.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over. It also has two players.
 * 
 * @author arnold
 *
 */
public class Othello extends Observable {
	public static final int DIMENSION = 8; // This is an 8x8 game
	private OthelloBoard board = new OthelloBoard(Othello.DIMENSION);
	private char whosTurn = OthelloBoard.P1;
	private int numMoves = 0;
	private boolean timeout = false;
	private char loser = OthelloBoard.EMPTY;
	private ArrayList<Othello> boards = new ArrayList<Othello>();
	public Player player1 = new Player(this, OthelloBoard.P1);
	public Player player2 = new Player(this, OthelloBoard.P2);
	
	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() {
		return this.whosTurn;
	}
	
	/*
	 * return all moves for the Player who's turn it is
	 */
	public ArrayList<Move> allMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				Othello othelloCopy = this.copy();
				if (othelloCopy.move(row, col))
					moves.add(new Move(row, col));
			}
		}
		return moves;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return whether this row and column is a current Move that can be made
	 */
	public boolean inAllMoves(int row, int col) {
		for(Move move: this.allMoves()) {
			if(move.getRow() == row && move.getCol() == col)
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return the token at position row, col.
	 */
	public char getToken(int row, int col) {
		TokenGetterVisitor tokenVisitor = new TokenGetterVisitor();
		return tokenVisitor.visit(board, row, col);
	}
	/**
	 * 
	 * @return change number of moves committed
	 */
	public void incrementNumMoves() {
		numMoves++;
		
	}
	/**
	 * @param num
	 * @return change number of moves committed
	 */
	public void setWhosTurn(int num) {
		numMoves = num;
		
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return the image at position row, col.
	 */
	public ImageView getImage(int row, int col) {
	
	    InputStream input1 = OthelloApplication.class.getResourceAsStream("black.png");
	    InputStream input2 = OthelloApplication.class.getResourceAsStream("white.png"); 
	    
	    Image black = new Image(input1); 
	    Image white = new Image(input2); 

	    ImageView vblack = new ImageView(black); 
	    ImageView vwhite = new ImageView(white);
	    
	    ImageView result = null;
		
		if(this.getToken(row, col) == 'X') {
			result = vblack;
		} else if(this.getToken(row, col) == 'O') {
			result = vwhite;
		}
		
		return result;
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
		MoveVisitor boardVisitor = new MoveVisitor();
		HasMoveVisitor hasMoveVisitor = new HasMoveVisitor();
		//Visitor that makes visits the board and attempts to make a move
		if (boardVisitor.visit(board, row, col, this.whosTurn)) {
			this.whosTurn = OthelloBoard.otherPlayer(this.whosTurn);
			char allowedMove = hasMoveVisitor.visit(board);
			if (allowedMove != OthelloBoard.BOTH)
				this.whosTurn = allowedMove;
			this.numMoves++;
			this.notifyObservers();
			boards.add(this.copy());
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Undo the last move of a human player
	 */
	public void undo() {
		if (boards.size() == 1) {
			boards.remove(boards.size()-1);
			this.resetOthello();
		}
		else if (boards.size() != 0) {
			boards.remove(boards.size()-1);
			Othello current = boards.get(boards.size()-1);
			board = current.board;
			whosTurn = current.whosTurn;
			numMoves = current.numMoves;
			if (whosTurn == player1.player) {
				if (player1.strategyName != "Human") {
					this.undo();
				}
			}
			else if (whosTurn == player2.player) {
				if (player2.strategyName != "Human") {
					this.undo();
				}
			}
			this.notifyObservers();		
		}
	}

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {
		TokenCountVisitor tokenCounter = new TokenCountVisitor();
		return tokenCounter.visit(board, player);
	}
	
	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount4x4(char player) {
		FourxFour_TokenCountVisitor fourBYfourcounter = new FourxFour_TokenCountVisitor();
		return fourBYfourcounter.visit(board, player);
	}

	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	public char getWinner() {
		if (!this.isGameOver()) 
			return OthelloBoard.EMPTY;
		if (this.getCount(OthelloBoard.P1) > this.getCount(OthelloBoard.P2))
			return OthelloBoard.P1;
		if (this.getCount(OthelloBoard.P1) < this.getCount(OthelloBoard.P2))
			return OthelloBoard.P2;
		return OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		return this.whosTurn == OthelloBoard.EMPTY;
	}
	/**
	 * Alert the game that time has run out for one of the players
	 */
	public void noTime() {
		timeout = true;
		if (this.whosTurn == OthelloBoard.P1) {
			loser = OthelloBoard.P1;
		}
		else if (this.whosTurn == OthelloBoard.P2) {
			loser = OthelloBoard.P2;
		}
		whosTurn = OthelloBoard.EMPTY;
		this.notifyObservers();
	
	}
	/**
	 * 
	 * @return the player whose time has run out
	 */
	public char getLoser() {
		return loser;
	}
	/**
	 * 
	 * @return if time has run out for one of the players
	 */
	public boolean checkTime() {
		return timeout;
	}	

	/**
	 * 
	 * @return a copy of this. The copy can be manipulated without impacting this.
	 */
	public Othello copy() {
		Othello o = new Othello();
		CopyVisitor copy = new CopyVisitor();
		o.board = copy.visit(board);
		o.numMoves = this.numMoves;
		o.whosTurn = this.whosTurn;
		return o;
	}
	
	/**
	 * Fully resets the game - Used by our restart button
	 * 
	 *
	 */
	
	public void resetOthello() {
		board = new OthelloBoard(Othello.DIMENSION);
		whosTurn = OthelloBoard.P1;
		numMoves = 0;
		
		loser = OthelloBoard.EMPTY;
		boards = new ArrayList<Othello>();
		player1 = new Player(this, OthelloBoard.P1);
		player2 = new Player(this, OthelloBoard.P2);
		
		this.notifyObservers();
	}
	
	
}
