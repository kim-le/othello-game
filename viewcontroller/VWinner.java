package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
/**
 * displays a winner message after game has been won
 */
public class VWinner extends Label implements Observer {

	/**
	 * create winner label
	 */
	public VWinner() {
		super();
		this.setFont(new Font("Arial", 18));
		
	}
	/**
	 * Updates label to display corresponding winner
	 * @param the othello game to observe
	 */
	
	@Override
	public void update(Observable o) {
		if(o instanceof Othello) {
			Othello othello = (Othello)o;
			char winner = othello.getWinner();
			if (winner == OthelloBoard.P1) this.setText("Black Wins!");
			else if (winner == OthelloBoard.P2) this.setText("White Wins!"); 
		} else {
			if (GameTimer.getInstance().getP1Time() == 0) this.setText("White Wins!");
			else if (GameTimer.getInstance().getP2Time() == 0) this.setText("Black Wins!"); 
		}
	}

}