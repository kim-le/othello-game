package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.ButtonObserver;
import ca.utoronto.utm.util.Observable;

/**
 * An undo button that observes changes in the othello model
 * Instantiates the abstract ButtonObserver class
 */
public class RedoButton extends ButtonObserver {
	
	/**
	 * Default constructor, no params
	 */
	public RedoButton() {
		super();
	}
	
	/**
	 * Constructor with string param
	 */
	public RedoButton(String s) {
		super(s);
	}
	
	@Override
	public void update(Observable o) { // while this isn't technically used, it's still here in case an observable object wants to use this
		System.out.println("Redo Button Pressed");
		
	}

}
