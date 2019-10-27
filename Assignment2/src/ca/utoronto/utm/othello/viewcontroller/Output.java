package ca.utoronto.utm.othello.viewcontroller;

import java.util.Observable;
import java.util.Observer;

public class Output implements Observer{
	public void update(Observable o, Object arg) {
		System.out.println("Message: " + o.toString() + " has been " + arg);
	}
}
