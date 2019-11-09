package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;
import java.util.Timer; import java.util.TimerTask;

class TimerDisplay extends TextField implements Observer{
	private Othello othello;
	int minute;
	int second;
	Timer timer;
	
	public TimerDisplay() {
		timer = new Timer();
		
	}
	
	public void update(Observable o) {
		
		othello = (Othello) o;
		if (!othello.isGameOver()) {
			
		}
		
	}
}

