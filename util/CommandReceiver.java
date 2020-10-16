package ca.utoronto.utm.util;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.viewcontroller.GameTimer;

/*
 * This acts as the receiver in the command design pattern
 * 
 * This class receives the command to be executed and then executes them accordingly
 */
public class CommandReceiver {
	
	Othello othello;
	
	/*
	 * Constructor class for the CommandReceiver class
	 * 
	 * Stores a reference to the Othello game itself
	 */
	public CommandReceiver(Othello o) {
		this.othello = o;
	}
	
	/*
	 * Undoes a previously performed move
	 */
	public void UndoMove() {
		// Set the current board to be what the previous board was
		// Then set the current player to be the other player (effectively undoing the current player's turn)
		// Switches the positions of the othello.board and othello.lastboard
		OthelloBoard ob = othello.board.copy();
		this.othello.board = this.othello.lastBoard.copy();
		this.othello.lastBoard = ob.copy();
		this.othello.setOtherPlayer();
		this.othello.notifyObservers();
	}
	
	/*
	 * After undoing, redo can revert the changes done by the redo method
	 */
	public void RedoMove() {
		OthelloBoard ob = othello.board.copy();
		this.othello.board = this.othello.lastBoard.copy();
		this.othello.lastBoard = ob.copy();
		this.othello.setOtherPlayer();
		this.othello.notifyObservers();
	}
	
	/*
	 * Pause the game
	 * 
	 * A paused game means:
	 *  - Game is not playable
	 *  - Cannot place tokens
	 *  - Timers stop counting down
	 */
	public void PauseGame() {
		if (this.othello.isPaused) { // unpause
			GameTimer.getInstance().getTimer().play();
		} else { // is not paused, pause it
			GameTimer.getInstance().getTimer().pause();
		}
		this.othello.isPaused = !this.othello.isPaused; // toggle the pause variable
	}
	
}
