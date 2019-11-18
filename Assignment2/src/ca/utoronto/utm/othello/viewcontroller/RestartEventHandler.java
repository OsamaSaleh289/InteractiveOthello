package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.TimeTrackerSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RestartEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private TimeTrackerSingleton timer;
	
	public RestartEventHandler(Othello othello, TimeTrackerSingleton timer) {
		this.othello = othello;
		this.timer = timer;	
	}

	@Override
	public void handle(ActionEvent event) {
		TimeQuery query = new TimeQuery();
		this.othello.resetOthello();
		query.run();
		this.timer.startTimer();
	}

}
