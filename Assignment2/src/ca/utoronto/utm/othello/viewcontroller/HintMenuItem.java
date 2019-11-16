package ca.utoronto.utm.othello.viewcontroller;

import java.io.InputStream;

import ca.utoronto.utm.othello.model.OthelloController;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HintMenuItem extends MenuItem implements Observer{
	private OthelloController oc;
	private String hintType;
	
	public HintMenuItem(OthelloController oc, String hintType) {
		this.oc = oc;
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
			if(this.oc.greedyHintOn)
				this.setGraphic(this.getImage(this.hintType + "checkbox.png"));
			else
				this.setGraphic(this.getImage(this.hintType + "outline.png"));
		}
		else if(this.hintType == "random") {
			if(this.oc.randomHintOn)
				this.setGraphic(this.getImage(this.hintType + "checkbox.png"));
			else
				this.setGraphic(this.getImage(this.hintType + "outline.png"));
		}
		else if(this.hintType == "better") {
			if(this.oc.betterHintOn)
				this.setGraphic(this.getImage(this.hintType + "checkbox.png"));
			else
				this.setGraphic(this.getImage(this.hintType + "outline.png"));
		}
	}

}
