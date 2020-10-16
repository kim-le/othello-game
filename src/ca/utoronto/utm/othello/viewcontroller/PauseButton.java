package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.util.ButtonObserver;
import ca.utoronto.utm.util.Observable;

/**
 * An undo button that observes changes in the othello model
 * Instantiates the abstract ButtonObserver class
 */
public class PauseButton extends ButtonObserver {
	
	/**
	 * Default constructor, no params
	 */
	public PauseButton() {
		super();
	}
	
	/**
	 * Constructor with string param
	 */
	public PauseButton(String s) {
		super(s);
	}

	@Override
	public void update(Observable o) { // while this isn't technically used, it's still here in case an observable object wants to use this
		System.out.println("Undo Button Pressed");
		
	}

}
