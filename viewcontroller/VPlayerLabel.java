package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A label for player stats (type, colour, whether or not its their turn, and number of pieces)
 * @author Kim Le
 *
 */
public class VPlayerLabel extends Label implements Observer {
	Image black = new Image(getClass().getResourceAsStream("black.png"));
	Image white = new Image(getClass().getResourceAsStream("white.png"));
	private char player;
	private String name;

	/**
	 * Creates a new VPlayerLabel with the given message, graphic, and information to pull the player's stats.
	 * @param message 	Player 1 or Player 2
	 * @param graphic	Player token graphic
	 * @param player	OthelloBoard.P1 or OthelloBoard.P2
	 */
	public VPlayerLabel(String message, Node graphic, char player) {
		super(message, graphic);
		this.player = player;
		this.name = message;
		this.setMinWidth(158);
	}
	/**
	 * Update the label to the current player of the game.
	 * @param the Othello game to observe
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello)o;
		char whosTurn = othello.getWhosTurn();
		String playerType = "";
		if(whosTurn == this.player) {
			DropShadow glow = new DropShadow(); 
			glow.setColor(Color.YELLOW);
			glow.setSpread(0.6);
			glow.setOffsetX(0f);
			glow.setOffsetY(0f);
			glow.setHeight(30);
			glow.setWidth(30);
			this.getGraphic().setEffect(glow);
			if (this.player == OthelloBoard.P1) {
				if (othello.getP1() == 'H') {
					playerType = "Human";
				} else if (othello.getP1() == 'G') {
					playerType = "Greedy";
				} else if (othello.getP1() == 'R') {
					playerType = "Random";
				} else if (othello.getP1() == 'I') {
					playerType = "Improved";
				}
			} else if (this.player == OthelloBoard.P2) {
				if (othello.getP2() == 'H') {
					playerType = "Human";
				} else if (othello.getP2() == 'G') {
					playerType = "Greedy";
				} else if (othello.getP2() == 'R') {
					playerType = "Random";
				} else if (othello.getP2() == 'I') {
					playerType = "Improved";
				}
			}
			this.setText(this.name +" ("+ playerType + ")\n" + othello.getCount(this.player));
		} else {
			this.getGraphic().setEffect(null);
			if (this.player == OthelloBoard.P1) {
				if (othello.getP1() == 'H') {
					playerType = "Human";
				} else if (othello.getP1() == 'G') {
					playerType = "Greedy";
				} else if (othello.getP1() == 'R') {
					playerType = "Random";
				} else if (othello.getP1() == 'I') {
					playerType = "Improved";
				}
			} else if (this.player == OthelloBoard.P2) {
				if (othello.getP2() == 'H') {
					playerType = "Human";
				} else if (othello.getP2() == 'G') {
					playerType = "Greedy";
				} else if (othello.getP2() == 'R') {
					playerType = "Random";
				} else if (othello.getP2() == 'I') {
					playerType = "Improved";
				}
			}
			this.setText(this.name +" ("+ playerType + ")\n" + othello.getCount(this.player));
			
		}
	}
}