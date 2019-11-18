package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * An UndoInvoker handles an undo button click
 *
 */
class UndoInvoker implements EventHandler<ActionEvent> {
	private Othello game;
	
	/**
	 * Constructs an UndoInvoker 
	 * @param othello  this Othello game
	 */
	public UndoInvoker(Othello othello) {
		this.game = othello;
	}
	
	/**
	 * handles an undo button click, creates an undo command object and 
	 * calls execute on it
	 */
	@Override
	public void handle(ActionEvent event) {
		UndoCommand undo = new UndoCommand(game);
		undo.execute();
	}

}
