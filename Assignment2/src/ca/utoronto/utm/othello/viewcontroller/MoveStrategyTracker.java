package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.Player;
import javafx.scene.control.TextField;

/**
 * a MoveStrategyTracker keeps track of this Player's current MoveStrategy
 * 
 * @author katri
 *
 */
public class MoveStrategyTracker extends TextField implements Observer{
	private Player player;
	
	/**
	 * Constructs a MoveStrategyTracker to keep track of this Player's MoveStrategy
	 * 
	 * @param text the String representation of this Player's MoveStrategy
	 */
	public MoveStrategyTracker(String text) {
		this.setText(text);
	}

	/**
	 * updates the MoveStrategy of this Player
	 */
	@Override
	public void update(Observable o) {
		player = (Player) o;
		this.setText(player.getPlayer() + " : "+this.player.getStrategyName());
	}
}

