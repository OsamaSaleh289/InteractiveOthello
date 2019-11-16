package ca.utoronto.utm.othello.viewcontroller;

import javafx.event.ActionEvent;
import ca.utoronto.utm.othello.model.OthelloController;
import javafx.event.EventHandler;


public class GreedyHintEventHandler implements EventHandler<ActionEvent> {
	private OthelloController oc;
	
	public GreedyHintEventHandler(OthelloController oc) {
		this.oc = oc;
	}

	public void handle(ActionEvent event) {		
		this.oc.toggleGreedyHint();
	}
}
