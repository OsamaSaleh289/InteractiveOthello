package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class UndoInvoker implements EventHandler<ActionEvent> {
	Othello game;
	
	public UndoInvoker(Othello o) {
		game = o;
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		UndoCommand undo = new UndoCommand(game);
		undo.execute();
	}

}
