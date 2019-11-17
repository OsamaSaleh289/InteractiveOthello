package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Hints;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HintEventHandler implements EventHandler<ActionEvent> {
	private Hints oc;
	
	public HintEventHandler(Hints oc) {
		this.oc = oc;
	}

	public void handle(ActionEvent event) {		
		HintMenuItem menuitem= (HintMenuItem) event.getSource();
		if(menuitem.getHintType()=="greedy")
			this.oc.toggleGreedyHint();
		else if(menuitem.getHintType()=="random")
			this.oc.toggleRandomHint();
		else if(menuitem.getHintType()=="better")
			this.oc.toggleBetterHint();
	}
}
