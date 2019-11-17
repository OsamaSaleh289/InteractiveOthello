package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.Hints;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

public class HintHighlight extends InnerShadow{
	private Othello othello;
	private int row, col;
	private Hints hints;

	public HintHighlight(Othello othello, int row, int col, Hints hints) {
		this.othello = othello;
		this.hints = hints;
		this.row = row;
		this.col = col;
		
		this.setHighlightColor();
		
		this.setOffsetX(0f);
		this.setOffsetY(0f);
		this.setWidth(25);
		this.setHeight(25);
		this.setBlurType(BlurType.ONE_PASS_BOX);
	}
	
	private void setHighlightColor() {
		if(this.othello.getImage(row, col)!=null)
			this.setColor(null);
		
		if(this.hints.isButtonBetterHint(row,col))
			this.setColor(Color.rgb(11, 222, 211));
		else if(this.hints.isButtonGreedyHint(row,col))
			this.setColor(Color.rgb(222, 2, 2));
		else if(this.hints.isButtonRandomHint(row,col)) 
			this.setColor(Color.rgb(164, 8, 199));
		else if(this.othello.inAllMoves(row, col))
			this.setColor(Color.rgb(67, 209, 6));
		else
			this.setColor(null);
	}
}
