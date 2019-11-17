package ca.utoronto.utm.othello.viewcontroller;

import java.io.InputStream;

import ca.utoronto.utm.othello.model.Hints;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HintMenuItem extends MenuItem implements Observer{
	private Hints hints;
	private String hintType;
	
	public HintMenuItem(Hints oc, String hintType) {
		this.hints = oc;
		this.hintType = hintType;
		
		this.setText(this.hintType + " hint");
		this.setGraphic(this.getImage(this.hintType + "outline.png"));
	}
	
	private ImageView getImage(String name) {
		InputStream input1 = OthelloApplication.class.getResourceAsStream(name);
	    Image image = new Image(input1); 
	    ImageView vimage = new ImageView(image); 
		return vimage;
	}
	
	public String getHintType() {
		return this.hintType;
	}

	@Override
	public void update(Observable o) {
		if(this.hintType == "greedy") {
			if(this.hints.isGreedyHintOn())
				this.setGraphic(this.getImage(this.hintType + "checkbox.png"));
			else
				this.setGraphic(this.getImage(this.hintType + "outline.png"));
		}
		else if(this.hintType == "random") {
			if(this.hints.isRandomHintOn())
				this.setGraphic(this.getImage(this.hintType + "checkbox.png"));
			else
				this.setGraphic(this.getImage(this.hintType + "outline.png"));
		}
		else if(this.hintType == "better") {
			if(this.hints.isBetterHintOn())
				this.setGraphic(this.getImage(this.hintType + "checkbox.png"));
			else
				this.setGraphic(this.getImage(this.hintType + "outline.png"));
		}
	}

}
