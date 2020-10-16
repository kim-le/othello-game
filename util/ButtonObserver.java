package ca.utoronto.utm.util;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Button;

/*
 * An abstract button class that observes an observable model
 */
public abstract class ButtonObserver extends Button implements Observer {
	
	/*
	 * Default constructor, no params
	 */
	public ButtonObserver() {
		super();
	}
	
	/*
	 * Constructor with string param
	 */
	public ButtonObserver(String s) {
		super(s);
	}
	
	/*
	 * update in accordance to the observable object in question
	 * 
	 * @param o	The observable object
	 */
	public abstract void update(Observable o);
	
}
