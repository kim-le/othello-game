package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * label that displays the running time for each player (if both human)
 */
public class VTimerLabel extends Label implements Observer {

	private char player;
	private Othello othello;
	private ImageView timerPic = new ImageView(new Image(getClass().getResourceAsStream("timer.png")));
	/**
	 * create timer label
	 * @param player 
	 * @param othello
	 * 
	 */
	public VTimerLabel(char player, Othello othello) {
		this.timerPic.setFitHeight(16);
		this.timerPic.setFitWidth(16);
		this.timerPic.setPreserveRatio(true);
		this.setGraphic(this.timerPic);
		this.setMinWidth(140);
		this.player = player;
		this.othello = othello;
	}
	/**
	 * update timer label every second
	 */
	@Override
	public void update(Observable o) {
		GameTimer gameTimer = (GameTimer)o;
		
		if (this.player == OthelloBoard.P1)
			if (othello.getP1() != 'H') {
				this.setText(null);
				this.setGraphic(null);
			} else {
				this.setGraphic(timerPic);
				this.setText(String.valueOf(gameTimer.getP1Time()));
			}
		else if (this.player == OthelloBoard.P2)
			if (othello.getP2() != 'H') {
				this.setText(null);
				this.setGraphic(null);
			} else {
				this.setGraphic(timerPic);
				this.setText(String.valueOf(gameTimer.getP2Time()));
			}	
	}
}