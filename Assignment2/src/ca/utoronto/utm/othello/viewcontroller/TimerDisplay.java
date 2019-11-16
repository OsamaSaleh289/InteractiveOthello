package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer; import java.util.TimerTask;

class TimerDisplay extends TextField implements Observer{
	private Othello othello;
	int minute;
	int second;
	TimeTracker timer;
	
	public TimerDisplay(TimeTracker tracker) {
		this.setText(minute + ":"+ second);
		this.timer = tracker;
		
	}

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		this.setText(timer.getMinutes() + ":" + timer.getSeconds() );
	}
}

