package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer; import java.util.TimerTask;

class TimerDisplay extends TextField implements ActionListener{
	private Othello othello;
	int minute;
	int second;
	Timer timer;
	
	public TimerDisplay() {
		timer = new Timer();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

