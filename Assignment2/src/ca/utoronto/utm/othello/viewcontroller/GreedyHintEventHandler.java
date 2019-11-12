package ca.utoronto.utm.othello.viewcontroller;

import javafx.event.ActionEvent;
import ca.utoronto.utm.util.Observable;
import javafx.event.EventHandler;


public class GreedyHintEventHandler extends Observable implements EventHandler<ActionEvent> {

	public void handle(ActionEvent event) {
		this.notifyObservers();
	}
}
