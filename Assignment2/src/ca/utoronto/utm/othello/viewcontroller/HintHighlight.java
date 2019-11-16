package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.BetterMoveStrategy;
import ca.utoronto.utm.othello.model.GreedyMoveStrategy;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloController;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

public class HintHighlight extends InnerShadow{
	public Othello othello;
	private int row, col;
	private OthelloController oc;

	public HintHighlight(Othello othello, int row, int col, OthelloController oc) {
		this.othello = othello;
		this.oc = oc;
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
		if(this.othello.getImage(row, col)==null)
			this.setColor(null);
		
		Move greedyHint = (new GreedyMoveStrategy(this.othello)).getMove();
		Move betterHint = (new BetterMoveStrategy(this.othello)).getMove();
		
		if(this.oc.betterHintOn && (betterHint.getRow()==this.row && betterHint.getCol()==this.col))
			this.setColor(Color.rgb(11, 222, 211));
		else if(this.oc.greedyHintOn && (greedyHint.getRow()==this.row && greedyHint.getCol()==this.col))
			this.setColor(Color.rgb(222, 2, 2));
		else if(this.oc.randomHintOn && (this.oc.randomHint.getRow()==this.row && this.oc.randomHint.getCol()==this.col)) 
			this.setColor(Color.rgb(164, 8, 199));
		else if(this.othello.inAllMoves(row, col))
			this.setColor(Color.rgb(67, 209, 6));
		else
			this.setColor(null);
	}
}
