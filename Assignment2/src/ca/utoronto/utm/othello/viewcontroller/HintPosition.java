package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;

public class HintPosition extends TextField implements Observer {
	
	private Othello othello;
			
	public HintPosition(Othello othello) {
		this.othello = othello;	
	}

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub

	    if (o instanceof HintEventHandler) {
	    	Move m = othello.getRandomHint();
	    	this.setText("(" + m.getRow()+ ','+' ' + m.getCol() + ")");
	    } else if (o instanceof MoveAttemptEventHandler) {
	        this.clear();
	    } 

	}
}
