package ca.utoronto.utm.othello.viewcontroller;

import java.util.Timer;
import java.util.TimerTask;

import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * A singleton controller which manipulates the singleton GameTimer, ticking down the amount of time the current player has left.
 */
public class GameTimerTask implements EventHandler<ActionEvent> {
	
	private static GameTimerTask instance;
	private GameTimer model;
	
	/**
	 * Instantiates a new timerTask.
	 * @param The game timer which will be manipulated.
	 */
	private GameTimerTask(GameTimer model) {
		super();
		this.model = model;
	}
	
	/**
	 * Retrieves the only instance of GameTimerTask. If it does not exist, then instantiate a new one.
	 * @return the GameTimerTask instance.
	 */
	public static synchronized GameTimerTask getInstance(GameTimer model) {
		if (GameTimerTask.instance == null) 
			GameTimerTask.instance = new GameTimerTask(model);
		return GameTimerTask.instance;
	}
	
	/**
	 * Every second, determine which player's turn it is, and then tick down the amount of time that player has left by 1.
	 */
	@Override
	public void handle(ActionEvent arg0) {
		if (this.model.whosTurn == OthelloBoard.P1) 
			this.model.p1TickDown();
		else if (this.model.whosTurn == OthelloBoard.P2)
			this.model.p2TickDown();
	}
}
