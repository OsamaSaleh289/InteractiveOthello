package ca.utoronto.utm.othello.viewcontroller;

import java.util.Observable;
import java.util.Observer;

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
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		othello = (Othello) o;
		this.setText(token + " : " + othello.getCount(token));
		
		
	}

}