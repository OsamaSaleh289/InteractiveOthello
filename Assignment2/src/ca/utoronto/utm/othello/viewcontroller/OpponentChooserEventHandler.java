package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OpponentChooserEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private TimeTracker timer;
	
	public OpponentChooserEventHandler(Othello othello, TimeTracker timer) {
		this.othello = othello;
		this.timer = timer;
	}
	
	public void handle(ActionEvent event) {
		
		Button button = (Button) event.getSource();

		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);
		
		if(row==4 && col==9) {
			this.othello.setOpponent(othello.getWhosTurn(),"Human");
			timer.startTimer();
		} else if(row==5 && col==9) {
			this.othello.setOpponent(othello.getWhosTurn(),"Random");
			timer.startTimer();
		} else if(row==6 && col==9) {
			this.othello.setOpponent(othello.getWhosTurn(),"Greedy");
			timer.startTimer();
		}
	}
}

