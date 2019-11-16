package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.Player;
import javafx.scene.control.TextField;
public class MoveStrategyTracker extends TextField implements Observer{
	private Player player;
	
	public MoveStrategyTracker(String text) {
		this.setText(text);
	}

	@Override
	public void update(Observable o) {
		player = (Player) o;
		this.setText(player.getPlayer() + " : "+this.player.getStrategyName());
	}
}

