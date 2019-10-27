package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

class ButtonPressEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	public ButtonPressEventHandler(Othello othello) {
		this.othello = othello;
	}
	public void handle(ActionEvent event) {
		Button button = (Button)event.getSource();
		String text = button.getText();
		String row = text.substring(1, 2);
		String col = text.substring(2);
		System.out.println("(" + row+","+col +")");
	}
}
