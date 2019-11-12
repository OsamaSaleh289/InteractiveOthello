package ca.utoronto.utm.othello.model;

/**
 * TODO: Document this class and make minimal changes as necessary.
 * 
 * @author arnold
 *
 */
public class PlayerHuman extends Player {
	
	public PlayerHuman(Othello othello, char player) {
		super(othello, player);
		this.type = "Human";
	}

	public Move getMove(){
		return null;
	}
}
