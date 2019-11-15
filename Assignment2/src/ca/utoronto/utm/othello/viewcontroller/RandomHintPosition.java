package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RandomHintPosition extends TextField implements Observer {
	
	private Othello othello;
	private GridPane gridPane;
			
	public RandomHintPosition(Othello othello,GridPane gridPane) {
		this.othello = othello;	
		this.gridPane = gridPane;
	}

	@Override
	public void update(Observable o) {

	    if (o instanceof RandomHintEventHandler) {
	        for (Node node : gridPane.getChildren()) {

	        	node.setStyle("-fx-background-insets: 0 0 0 0, 0, 1, 2"); 
	        }
	    	
	    	Move m = othello.getRandomHint();
	    	int r =  m.getRow();
	    	int c =  m.getCol();
	    	
	        for (Node node : gridPane.getChildren()) {
	            if(GridPane.getRowIndex(node) == r && GridPane.getColumnIndex(node) == c) {

	            	node.setStyle("-fx-background-color: green");
	                break;
	            }
	        }
//	    	this.setText("(" + m.getRow()+ ','+' ' + m.getCol() + ")");
	    } else if (o instanceof MoveAttemptEventHandler) {
	        for (Node node : gridPane.getChildren()) {

	        	node.setStyle("-fx-background-insets: 0 0 0 0, 0, 1, 2"); 
	        }
	    } 

	}
}
