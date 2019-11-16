package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloController;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardSquare extends Button implements Observer{
	private Othello othello;
	private OthelloController oc;
	
	public BoardSquare(Othello othello, int row, int col, OthelloController oc) {
		this.setText("");
		this.othello = othello;
		this.oc = oc;
		this.setGraphic(othello.getImage(row,col));
		
		HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.oc);
		if(hintHighlight.getColor()!=null)
			this.setEffect(hintHighlight);
	}
	
	@Override
	public void update(Observable o) {
		int row = GridPane.getRowIndex(this);
		int col = GridPane.getColumnIndex(this);
		this.setGraphic(this.othello.getImage(row,col));
		this.setEffect(null);

		HintHighlight hintHighlight = new HintHighlight(this.othello,row,col,this.oc);
		if(hintHighlight.getColor()!=null && !this.othello.isGameOver())
			this.setEffect(hintHighlight);
	}

}
