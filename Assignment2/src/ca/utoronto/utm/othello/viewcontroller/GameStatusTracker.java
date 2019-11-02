package ca.utoronto.utm.othello.viewcontroller;

import java.util.Observable;
import java.util.Observer;

import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;

public class GameStatusTracker extends TextField implements Observer{
	private Othello othello;
	
	public GameStatusTracker(String text) {
		this.setText(text);
		
		
	}

	@Override
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		othello = (Othello) o;
		if (othello.getWinner() == ' ') {
			this.setText(othello.getWhosTurn()+"'s" + " Turn");
		} else {
			this.setText("Winner : " + othello.getWinner());
		}
	}
	
	

}

