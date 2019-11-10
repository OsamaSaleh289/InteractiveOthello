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

class TimeTracker extends Observable implements Observer {

	public static int minutesP1 = 5;
	public static int secondsP1 = 0;
	public static int minutesP2 = 5;
	public static int secondsP2 = 0;
	
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

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		if (countdownP1.getStatus() == Animation.Status.RUNNING) {
			countdownP1.pause();
			countdownP2.play();
		}
		else if (countdownP2.getStatus() == Animation.Status.RUNNING) {
			countdownP2.pause();
			countdownP1.play();
		}
	}

	
}


