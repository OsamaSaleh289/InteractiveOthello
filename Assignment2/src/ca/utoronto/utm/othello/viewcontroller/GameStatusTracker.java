package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;

import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;

public class GameStatusTracker extends TextField implements Observer{
	private Othello othello;
	
	public GameStatusTracker(String text) {
		this.setText(text);
	}

	@Override
	public void update(Observable o) {
		othello = (Othello) o;
		if (!othello.isGameOver()) {
			this.setText(othello.getWhosTurn()+"'s" + " Turn");
		} else {
			this.setText("Winner: " + othello.getWinner());
		}
	}
}

