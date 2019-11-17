package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.TimeTracker;
import ca.utoronto.utm.util.*;
import javafx.scene.control.TextField;

class TimerDisplay extends TextField implements Observer{
	private int minute;
	private int second;
	private TimeTracker timer;
	
	public TimerDisplay(TimeTracker tracker) {
		this.setText(minute + ":"+ second);
		this.timer = tracker;
	}

	@Override
	public void update(Observable o) {
		this.setText(timer.getMinutes() + ":" + timer.getSeconds() );
	}
}

