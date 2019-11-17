package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RestartEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private TimeTracker timer;
	
	public RestartEventHandler(Othello o, TimeTracker timer) {
		othello = o;
		this.timer = timer;
		
		
	}
	

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		TimeQuery query = new TimeQuery(timer);
		othello.resetOthello();
		query.run();
		timer.startTimer();
	}

}
