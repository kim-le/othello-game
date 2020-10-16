package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BoardButtonPressEventHandler implements EventHandler<ActionEvent> {

	Othello othello;

	/**
	 * Handler constructor
	 * 
	 * @param othello: the othello game that we want to have attached
	 */
	public BoardButtonPressEventHandler(Othello othello) {
		this.othello = othello;
	}

	/**
	 * This method gets called upon a button being pressed, then updates the grid by
	 * calling observers
	 * 
	 * @param event: the event being handled
	 */
	public void handle(ActionEvent event) {
		VSpot src = (VSpot) event.getSource();
		if (!(othello.isPaused)) { // only if not paused, perform move
			othello.lastBoard = this.othello.board.copy();
			//replace line below with visitor pattern
			//othello.move(GridPane.getRowIndex(src), GridPane.getColumnIndex(src));
			HumanVisitor humanVisitor = new HumanVisitor(src); //pass in src as args
			othello.accept(humanVisitor);
			othello.notifyObservers();
			
			// Below line is being kept incase the issue rises again
			// othello.lastBoard = this.othello.board.copy(); // to prevent double animation
		}
		return;
		
	}
}
