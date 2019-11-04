package ca.utoronto.utm.othello.viewcontroller;

import java.util.Observable;
import java.util.Observer;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.scene.control.TextField;
public class OpponentTrackerP2 extends TextField implements Observer{
	private Othello othello;
	
	public OpponentTrackerP2(String text) {
		this.setText(text);
	}

	@Override
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		othello = (Othello) o;
		this.setText("P2: "+othello.getOpponent(OthelloBoard.P1));
	}
}

