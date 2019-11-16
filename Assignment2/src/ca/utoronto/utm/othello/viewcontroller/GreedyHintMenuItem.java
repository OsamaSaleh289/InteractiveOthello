package ca.utoronto.utm.othello.viewcontroller;

import java.io.InputStream;

import ca.utoronto.utm.othello.model.OthelloController;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GreedyHintMenuItem extends MenuItem implements Observer{
	private OthelloController oc;
	
	public GreedyHintMenuItem(OthelloController oc) {
		this.oc = oc;
		
		this.setText("greedy hint");
		this.setGraphic(this.getImage("redoutline.png"));
	}
	
	private ImageView getImage(String name) {
		InputStream input1 = OthelloApplication.class.getResourceAsStream(name);
	    Image image = new Image(input1); 
	    ImageView vimage = new ImageView(image); 
	    		
		return vimage;
	}

	@Override
	public void update(Observable o) {
		if(this.oc.greedyHintOn)
			this.setGraphic(this.getImage("redcheckbox.png"));
		else
			this.setGraphic(this.getImage("redoutline.png"));
		
	}

}
