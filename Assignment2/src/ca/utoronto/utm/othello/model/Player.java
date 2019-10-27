package ca.utoronto.utm.othello.model;

import java.util.Observable;
import java.util.Observer;

public abstract class Player {
	protected Othello othello;
	protected char player;

	public Player(Othello othello, char player) {
		this.othello=othello;
		this.player=player;
	}
	public char getPlayer() {
		return this.player;
	}
	public abstract Move getMove();
//	public abstract Move getMove(String r,String c);
	
}
