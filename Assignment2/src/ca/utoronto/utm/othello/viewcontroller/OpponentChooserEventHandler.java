package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OpponentChooserEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	
	public OpponentChooserEventHandler(Othello othello) {
		this.othello = othello;
	}
	
	public void handle(ActionEvent event) {
		
		Button button = (Button) event.getSource();

		int row = GridPane.getRowIndex(button);
		int col = GridPane.getColumnIndex(button);
		
		// for some reason row and column are switched
		if(row==4 && col==9) {
			System.out.println("Human vs. Human Selected");
			this.othello.setOpponent(othello.getWhosTurn(),"Human");
		} else if(row==5 && col==9) {
			System.out.println("Human vs. Random Selected");
			this.othello.setOpponent(othello.getWhosTurn(),"Random");
		} else if(row==6 && col==9) {
			System.out.println("Human vs. Greedy Selected");
			this.othello.setOpponent(othello.getWhosTurn(),"Greedy");
		}
	}
}

