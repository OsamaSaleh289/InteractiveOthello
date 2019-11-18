package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class UndoInvoker implements EventHandler<ActionEvent> {
	private Othello game;
	
	public UndoInvoker(Othello othello) {
		this.game = othello;
	}
	@Override
	public void handle(ActionEvent event) {
		UndoCommand undo = new UndoCommand(game);
		undo.execute();
	}

}
