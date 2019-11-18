package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.TimeTrackerSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * 
 * Implmentation of event handler that deals with restarting the game upon clicking the restart button
 * 
 * @author Osama Saleh 
 */

public class RestartEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private TimeTrackerSingleton timer;
	
	/**
	 * 
	 * Counstructs a hint event handler
	 * @param Othello othello
	 * @param TimeTrackerSingleton
	 * 
	 * @author Osama Saleh
	 */
	
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
