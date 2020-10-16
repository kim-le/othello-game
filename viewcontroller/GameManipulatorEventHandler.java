package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;
import java.lang.Class;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.ButtonObserver;
import ca.utoronto.utm.util.CommandInvoker;
import ca.utoronto.utm.util.CommandReceiver;
import ca.utoronto.utm.util.PauseCommand;
import ca.utoronto.utm.util.RedoCommand;
import ca.utoronto.utm.util.UndoCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This class is for game manipulators (undo, redo, pause, etc...)
 * 
 * @author Michael Kwan
 */
public class GameManipulatorEventHandler implements EventHandler<ActionEvent> {
	
	Othello othello; // store the game
	CommandInvoker cmdInvoker = new CommandInvoker();
	
	/**
	 * Constructor
	 * 
	 * @param othello	We want to keep track of the othello game
	 */
	public GameManipulatorEventHandler(Othello othello) {
		this.othello = othello;
	}
	
	/**
	 * This method gets called upon this event handler being called (Button being pressed)
	 * The pause, undo, and redo button will implement this
	 * 
	 * This is the "client" in the command design pattern
	 * 
 	 * There are 3 cases as to when this method should do something:
	 * 
	 *	1. When the event's source is an undo
	 * 	2. When the event's source is a redo
	 * 	3. When the event's source is a pause
	 * 
	 *  Otherwise, just return, doing nothing
	 *  
	 * 
	 * The reason why receivers and commands are created at every point instead of being set as a field, is because it crashes
	 * 
	 */
	@Override
	public void handle(ActionEvent e) {
		ButtonObserver src = (ButtonObserver)e.getSource();
		if (src instanceof UndoButton && !othello.undone && othello.getNumMoves() > 0) { // Undo button pressed
			CommandReceiver cmdReceiver = new CommandReceiver(othello); // Store the receiver
			UndoCommand undoCommand = new UndoCommand(cmdReceiver); // store an instance of an undo command
			cmdInvoker.ExecuteCommand(undoCommand);
			othello.undone = !othello.undone;
			
		} if (src instanceof RedoButton && othello.undone && othello.getNumMoves() > 0) { // Redo button pressed
			CommandReceiver cmdReceiver = new CommandReceiver(othello); // Store the receiver
			RedoCommand redoCommand = new RedoCommand(cmdReceiver); // store an instance of a redo command
			cmdInvoker.ExecuteCommand(redoCommand);
			othello.undone = !othello.undone;
			
		} if (src instanceof PauseButton) { // Pause button pressed
			CommandReceiver cmdReceiver = new CommandReceiver(othello); // Store the receiver
			PauseCommand pauseCommand = new PauseCommand(cmdReceiver); // store an instance of a pause command
			cmdInvoker.ExecuteCommand(pauseCommand);
		}
		return;
		
	}
}
