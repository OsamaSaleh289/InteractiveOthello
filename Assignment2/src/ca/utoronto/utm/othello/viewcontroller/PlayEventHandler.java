package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.TimeTrackerSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class PlayEventHandler implements EventHandler<ActionEvent> {
	private TimeTrackerSingleton timer;
	
	PlayEventHandler(TimeTrackerSingleton timer) {
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
