package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.TimeTracker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class PlayEventHandler implements EventHandler<ActionEvent> {
	private TimeTracker timer;
	
	PlayEventHandler(TimeTracker timer) {
		this.timer = timer;
	}
	
	@Override
	public void handle(ActionEvent event) {
		TimeQuery query = new TimeQuery();
		((Node) event.getSource()).setVisible(false);
		query.run();
		this.timer.startTimer();
	}
	

}
