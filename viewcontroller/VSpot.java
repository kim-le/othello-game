package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import javafx.scene.image.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import ca.utoronto.utm.util.Observer;
import javafx.animation.Animation;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 * A button on the othello board. Will display the player token at its location.
 * @author Patrick Yevych
 *
 */
public class VSpot extends Button implements Observer {
	
	private int i, j;
	private BackgroundFill greenBG = new BackgroundFill(Color.SEAGREEN, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
	
	public VSpot(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	
	/**
	 * 
	 * @return the row the button is in.
	 */
	public int getRow() {
		return i;
	}
	
	/**
	 * 
	 * @return the column the button is in.
	 */
	public int getCol() {
		return j;
	}
	
	/**
	 * Updates the button to display the correct player token.
	 * @param the Othello game.
	 * @throws InterruptedException 
	 */
	
	@Override
	public void update(Observable o) {
		if (o instanceof Othello) {
			Othello othello = (Othello) o;
			//P2 == O P1 == X
			char player1 = othello.getP1();
			char player2 = othello.getP2();
			boolean isP1Cpu = false;
			boolean isP2Cpu = false;
			
			if(player1 != 'H' && player2 == 'H') {
				isP1Cpu = true;
			}
			if(player2 != 'H' && player1 == 'H') {
				isP2Cpu = true;
			}
			
			char whosPiece = othello.getToken(i, j);
			char lastPiece = othello.lastBoard.get(i, j);
			BackgroundFill yellowBG = new BackgroundFill(Color.YELLOW, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));
			//for changes
			
			if(whosPiece == 'X' && lastPiece == ' ') {
				setPic("black.png");
			}
			else if(whosPiece == 'O' && lastPiece == ' ') {
				setPic("white.png");
			}
			else if(whosPiece == 'X' && whosPiece != lastPiece) {
				if(isP1Cpu) {
					setPic("startwhite.gif");
					}
				else {
					setPic("hstartwhite.gif");
				}
			}
			else if(whosPiece == 'O' && whosPiece != lastPiece) {
				if(isP2Cpu) {
					setPic("startblack.gif");
					}
				else {
					setPic("hstartblack.gif");
				}
			}
			
			//for the start
			else if(whosPiece == 'X') {
				setPic("black.png");
			}
			else if(whosPiece == 'O') {
				setPic("white.png");
			}
	
			//for possible moves
			else if(othello.board.hasMoveAt(i, j, othello.getWhosTurn())) {
				this.setBackground(new Background(yellowBG));
				this.setGraphic(null); // check, works
				this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
			}
			else {
				this.setBackground(new Background(greenBG));
				this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
			}
		} else {
			if (GameTimer.getInstance().getP1Time()==0 || GameTimer.getInstance().getP2Time()==0) {
				setMouseTransparent(true);
			}
		}
	}
	
	public void setPic(String image) {
		Image piece = new Image(getClass().getResourceAsStream(image));
		this.setGraphic(new ImageView(piece));
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		this.setBackground(new Background(greenBG));
	}
}