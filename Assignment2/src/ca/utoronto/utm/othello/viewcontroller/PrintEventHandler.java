package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerHuman;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PrintEventHandler implements EventHandler<ActionEvent>{
	private Othello othello;

	PrintEventHandler(Othello othello) {
		this.othello = othello;
	}

	public void handle(ActionEvent event) {
//		System.out.println("AAAAAAA");
		Button button = (Button)event.getSource();
		String text = button.getText();
		String row = text.substring(1, 2);
		String col = text.substring(2);
//		System.out.println("col is" + col);
		othello.setMove(Integer.parseInt(row), Integer.parseInt(col));
		


//		if (balloon.isPopped()) {
//			Button jb = (Button) e.getSource();
//			jb.setDisable(true);
//		}
	}
}