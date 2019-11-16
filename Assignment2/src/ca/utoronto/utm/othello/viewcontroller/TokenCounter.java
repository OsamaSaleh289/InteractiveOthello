package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;

import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;

public class TokenCounter extends TextField implements Observer  {
	private char token;
	private Othello othello;
			
	public TokenCounter(String text, char token) {
		this.setText(text);
		this.token = token;
	}

	@Override
	public void update(Observable o) {
		othello = (Othello) o;
		this.setText(token + " : " + othello.getCount(token));
	}
}