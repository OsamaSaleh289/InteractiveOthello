package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;

/**
 * 
 * Counts the number of tokens on the board and displays it in the view
 * 
 * @author Osama Saleh
 */

import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;

public class TokenCounter extends TextField implements Observer  {
	private char token;
	private Othello othello;
			
	public TokenCounter(String text, char token) {
		this.setText(text);
		this.token = token;
	}
	
	/**
	 * 
	 * Updates the view with the new token values 
	 * @param Observable o
	 * 
	 * @author Osama Saleh
	 */

	@Override
	public void update(Observable o) {
		othello = (Othello) o;
		this.setText(token + " : " + othello.getCount(token));
	}
}