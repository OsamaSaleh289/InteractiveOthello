package ca.utoronto.utm.othello.viewcontroller;

import java.util.Optional;

import ca.utoronto.utm.othello.model.TimeTracker;
import javafx.scene.control.TextInputDialog;

public class TimeQuery {
	/**
	 * creates two dialogs that allow the user to set time
	 */
	public void run() {
		TextInputDialog dialogp1 = new TextInputDialog();
		dialogp1.setTitle("");
		dialogp1.setHeaderText("Please enter the time (in minutes) for Player 1");
		dialogp1.setGraphic(null);
		Optional<String> result = dialogp1.showAndWait();
		result.ifPresent(time -> {try {int p1time = Integer.parseInt(time);TimeTracker.setMinutesP1(p1time);} catch(Exception e) {
			System.out.println("invalid, time must be an integer");};
		});
		TextInputDialog dialogp2 = new TextInputDialog();
		dialogp2.setTitle("");
		dialogp2.setHeaderText("Please enter the time (in minutes) for Player 2");
		dialogp2.setGraphic(null);
		Optional<String> result2 = dialogp2.showAndWait();
		result2.ifPresent(time -> {try {int p2time = Integer.parseInt(time);TimeTracker.setMinutesP2(p2time);} catch(Exception e) {
			System.out.println("invalid, time must be an integer");}
		});
		TimeTracker.setSecondsP1(0);
		TimeTracker.setSecondsP2(0);
	}
}
