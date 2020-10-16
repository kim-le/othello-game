package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * The event handler for the hint button.
 */
public class HintButtonPressEventHandler implements EventHandler<ActionEvent> {

	private Othello othello;
	private char strategy;
	private GridPane grid;
	
	/**
	 * Instanciates a new HintButtonPressEventHandler.
	 * @param the current othello game.
	 * @param the board button grid.
	 */
	public HintButtonPressEventHandler(Othello othello, GridPane grid) {
		this.othello = othello;
		this.grid = grid;
	}
	
	/**
	 * When the hint button is pressed, call the simulate function to simulate a move following the supplied strategy from setStrategy().
	 * Then, highlight, in red, the space on the board with the x and y coordinates that match that of the Move object returned by simulate().
	 */
	@Override
	public void handle(ActionEvent arg0) {
		Move hint = this.simulate();
		BackgroundFill back = new BackgroundFill(Color.RED, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0)); 
		((Button) grid.getChildren().get((hint.getRow() * Othello.DIMENSION) + hint.getCol())).setBackground(new Background(back));
	}
	
	/*
	 * Sets the strategy that the computer player should use to generate the hint.
	 * @param 'G' for the greedy strategy or 'R' for the random strategy. Defaults to 'R' if the input is not appropriate.
	 */
	public void setStrategy(char strategy) {
		if (strategy == 'G' || strategy == 'R' || strategy == 'I')
			this.strategy = strategy;
		else
			this.strategy = 'R';
	}
	
	/**
	 * Creates a clone of the current state of the Othello game. 
	 * Then, play out one move made by a computer player following the strategy set in setStrategy(), and return that move.
	 */
	private Move simulate() {
		
		Othello tmpOthello = this.othello.copy();
		
		if (tmpOthello.getWhosTurn() == OthelloBoard.P1)
			tmpOthello.setP1(strategy);
		else 
			tmpOthello.setP2(strategy);
		
		tmpOthello.attach(new ComputerVisitorFactory());
		tmpOthello.notifyObservers();
		
		for (int i = 0; i < Othello.DIMENSION; i++) 
			for (int j = 0; j < Othello.DIMENSION; j++) {
				if (tmpOthello.getToken(i, j) != OthelloBoard.EMPTY && this.othello.getToken(i, j) == OthelloBoard.EMPTY) 
					return new Move(i, j);
			}
	
		return null;
	}

}
