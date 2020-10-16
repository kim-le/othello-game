package ca.utoronto.utm.othello.viewcontroller;

import java.util.Timer;
import java.util.TimerTask;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * A singleton model that tracks the amount of time both players have to make all of their moves.
 *
 */
public class GameTimer extends Observable implements Observer {
	
	private static GameTimer instance = null;
	
	private int p1Time;
	private int p2Time;
	char whosTurn;
	
	Timeline timer;
	
	/**
	 * Instantiates a new game timer. 
	 * @param The amount of time player 1 has, as a positive integer.
	 * @param The amount of time player 2 has, as a positive integer.
	 */
	private GameTimer(int p1Time, int p2Time) {
		super();
		
		this.setP1Time(p1Time);
		this.setP2Time(p2Time);
		
		timer = new Timeline();
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), GameTimerTask.getInstance(this)));
		
	}
	
	/**
	 * Retrieves the only instance of GameTimer. If it does not exist, then instantiate a new one.
	 * @return the GameTimer instance.
	 */
	public static synchronized GameTimer getInstance() {
		if (GameTimer.instance == null) 
			GameTimer.instance = new GameTimer(300, 300);
		return GameTimer.instance;
	}
	
	/**
	 * @return the Timeline timer object.
	 */
	public Timeline getTimer() {
		return this.timer;
	}
	
	/**
	 * @return the amount of time player 1 has left.
	 */
	public int getP1Time() {
		return this.p1Time;
	}
	
	/**
	 * @return the amount of time player 2 has left.
	 */
	public int getP2Time() {
		return this.p2Time;
	}
	
	/**
	 * @param the amount of time player 1 has, as a positive integer.
	 */
	public void setP1Time(int time) {
		if (time >= 0) {
			this.p1Time = time;
		}
	}
	
	/**
	 * @param the amount of time player 2 has, as a positive integer.
	 */
	public void setP2Time(int time) {
		if (time >= 0) {
			this.p2Time = time;
		}
	}
	
	/**
	 * Subtract 1 from the amount of time player 1 has left.
	 * Then, notify all observers that the state of this model has changed.
	 */
	public void p1TickDown() {
		if (this.p1Time == 0) {
			this.timer.pause();
		} else {
			this.p1Time--;
		}
		this.notifyObservers();
	}
	
	/**
	 * Subtract 1 from the amount of time player 2 has left.
	 * Then, notify all observers that the state of this model has changed.
	 */
	public void p2TickDown() {
		if (this.p2Time == 0) {
			this.timer.pause();
		} else {
			this.p2Time--;
		}
		this.notifyObservers();
	}
	
	/**
	 * Observe the Othello game model to see who's turn it is. 
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello)o;
		this.whosTurn = othello.getWhosTurn();
	}
	
	
}
