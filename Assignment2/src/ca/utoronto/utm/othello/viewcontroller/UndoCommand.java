package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;

public class UndoCommand implements GameStatusCommand {
	private Othello game;
	
	public UndoCommand(Othello othello) {
		this.game = othello;
	}

	@Override
	public void execute() {
		this.game.undo();
	}

}
