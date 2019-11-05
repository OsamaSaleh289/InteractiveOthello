package ca.utoronto.utm.othello.model;

import java.util.Observable;

public abstract class Player {
	protected Othello othello;
	protected char player;
	protected String type;

	public Player(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}

	public char getPlayer() {
		return this.player;
	}

	public String getType() {
		return this.type;
	}
	
	public abstract Move getMove();
}
