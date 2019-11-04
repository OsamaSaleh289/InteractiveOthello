package ca.utoronto.utm.mvcexample;

//import java.util.Observable;
import ca.utoronto.utm.util.*;

import javafx.scene.control.Label;
//import src.ca.utoronto.utm.util.Observer;

public class VCount extends Label implements Observer {

	@Override
	public void update(Observable o) {
		MCounter mcounter = (MCounter) o;
		this.setText("" + mcounter.getCount());
	}

}
