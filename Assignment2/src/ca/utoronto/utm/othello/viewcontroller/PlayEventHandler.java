package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class PlayEventHandler implements EventHandler<ActionEvent> {
	Othello game;
	TimeTracker timer;
	
	PlayEventHandler(Othello o, TimeTracker timer) {
		game = o;
		this.timer = timer;
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		TimeQuery query = new TimeQuery(timer);
		((Node) event.getSource()).setVisible(false);
		query.run();
		timer.startTimer();
	}
	

}
