package ca.utoronto.utm.othello.viewcontroller;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class TimeQuery {
	TimeTracker timer;
	
	TimeQuery(TimeTracker timer) {
		this.timer = timer;
	}
	public void run() {
		TextInputDialog dialogp1 = new TextInputDialog();
		dialogp1.setTitle("");
		dialogp1.setHeaderText("Please enter the time (in minutes) for Player 1");
		Optional<String> result = dialogp1.showAndWait();
		result.ifPresent(time -> {int p1time = Integer.parseInt(time);
		TimeTracker.minutesP1 = p1time;});
		TextInputDialog dialogp2 = new TextInputDialog();
		dialogp2.setTitle("");
		dialogp2.setHeaderText("Please enter the time (in minutes) for Player 2");
		Optional<String> result2 = dialogp2.showAndWait();
		result2.ifPresent(time -> {int p2time = Integer.parseInt(time);
		TimeTracker.minutesP2 = p2time;});
	}
}
