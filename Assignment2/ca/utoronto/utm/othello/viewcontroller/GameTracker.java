package ca.utoronto.utm.othello.viewcontroller;

import java.awt.event.ActionListener;
import ca.utoronto.utm.util.*;

import javax.swing.*;

class GameTracker extends Timer implements Observer {

	public GameTracker(int delay, ActionListener listener) {
		super(delay, listener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		
	}

	
}
