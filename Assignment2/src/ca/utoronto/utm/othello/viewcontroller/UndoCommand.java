package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;

public class UndoCommand implements GameStatusCommand {
	Othello game;
	//TimeTracker timer;
	
	public UndoCommand(Othello o) {
		game = o;
		//this.timer = timer;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		game.undo();
	}

}
