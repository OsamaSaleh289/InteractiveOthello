package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RestartEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	
	public RestartEventHandler(Othello o) {
		othello = o;
		
		
	}
	

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		othello.resetOthello();
		
	}

}
