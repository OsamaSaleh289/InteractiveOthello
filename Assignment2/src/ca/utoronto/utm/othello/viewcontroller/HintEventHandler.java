package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Hints;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HintEventHandler implements EventHandler<ActionEvent> {
	private Hints hints;
	
	public HintEventHandler(Hints hints) {
		this.hints = hints;
	}

	public void handle(ActionEvent event) {		
		HintMenuItem menuitem= (HintMenuItem) event.getSource();
		if(menuitem.getHintType()=="greedy")
			this.hints.toggleGreedyHint();
		else if(menuitem.getHintType()=="random")
			this.hints.toggleRandomHint();
		else if(menuitem.getHintType()=="better")
			this.hints.toggleBetterHint();
	}
}
