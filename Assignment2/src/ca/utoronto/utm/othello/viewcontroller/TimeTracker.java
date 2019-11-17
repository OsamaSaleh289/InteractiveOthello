package ca.utoronto.utm.othello.viewcontroller;

import java.awt.event.ActionListener;
import ca.utoronto.utm.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javax.swing.*;
import ca.utoronto.utm.othello.model.Othello;

public class TimeTracker extends Observable implements Observer {
	private Othello othello;
	public static int minutesP1 = 5;
	public static int secondsP1 = 0;
	public static int minutesP2 = 5;
	public static int secondsP2 = 0;
	private char whosTurn = 'X';
	
	
	Timeline countdownP1 = new Timeline();
	Timeline countdownP2 = new Timeline();
	
	EventHandler timeEventHandlerP1 = new EventHandler() {
		
		public void handle(Event event) {
			secondsP1--;
			notifyObservers();
			if (secondsP1<=0) {
				minutesP1 -=1;
				secondsP1 = 59;
			}
			if (minutesP1<0) {
				countdownP1.stop();
			}
			
		}
	};
	
	EventHandler timeEventHandlerP2 = new EventHandler() {
		
		@Override
		public void handle(Event event) {
			secondsP2--;
			notifyObservers();
			if (secondsP2<=0) {
				minutesP2 -=1;
				secondsP2 =59;
			}
			if (minutesP2<0) {
				countdownP2.stop();
			}
		}

	};
	
	public TimeTracker() {
		countdownP1.setCycleCount(Timeline.INDEFINITE);
		countdownP2.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame secondsFrameP1 = new KeyFrame(Duration.seconds(1), timeEventHandlerP1);
		KeyFrame secondsFrameP2 = new KeyFrame(Duration.seconds(1), timeEventHandlerP2);
		
		countdownP1.getKeyFrames().add(secondsFrameP1);
		countdownP2.getKeyFrames().add(secondsFrameP2);
		
	}
	public int getMinutes() {
		if (countdownP1.getStatus() == Animation.Status.RUNNING) {
			return minutesP1; }
		else {
			return minutesP2;
		}
	}
	public int getSeconds() {
		if (countdownP1.getStatus() == Animation.Status.RUNNING) {
			return secondsP1;
		}
		else {
			return secondsP2;
		}
	}
	public void startTimer() {
		countdownP1.play();
	}
	@Override
	public void update(Observable o) {
		
		othello = (Othello) o;
		if (othello.isGameOver()) {
			countdownP1.stop();
			countdownP2.stop();
		}
		else if (othello.getWhosTurn() != whosTurn) {
			whosTurn = othello.getWhosTurn();
			if (whosTurn == 'X') {
				countdownP2.pause();
				countdownP1.play();
			}
			else {
				countdownP1.pause();
				countdownP2.play();
			}
		}
		/*if (othello.getNumMoves() == 0) {
			
		}*/
	}

	
}


