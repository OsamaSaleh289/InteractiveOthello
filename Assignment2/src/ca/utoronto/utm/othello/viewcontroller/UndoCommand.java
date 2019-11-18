package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;

/**
 * An UndoCommand undoes the last move or the last two moves if playing 
 * against an AI
 *
 */
public class UndoCommand implements GameStatusCommand {
	private Othello game;
	
	/**
	 * Creates an undo command object
	 * @param othello  the Othello game 
	 */
	public UndoCommand(Othello othello) {
		this.game = othello;
	}

	/**
	 * executes an undo command
	 */
	@Override
	public void execute() {
		this.game.undo();
	}

}
