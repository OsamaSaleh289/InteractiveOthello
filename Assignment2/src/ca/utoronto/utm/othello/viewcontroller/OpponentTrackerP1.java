package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.scene.control.TextField;

public class OpponentTrackerP1 extends TextField implements Observer{
	private Othello othello;
	
	public OpponentTrackerP1(String text) {
		this.setText(text);
	}

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		othello = (Othello) o;
		this.setText("P1: "+othello.getOpponent(OthelloBoard.P2));
	}
}

