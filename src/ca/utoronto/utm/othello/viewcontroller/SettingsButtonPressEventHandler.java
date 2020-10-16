package ca.utoronto.utm.othello.viewcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * The event handler for the settings button.
 */
public class SettingsButtonPressEventHandler implements EventHandler<ActionEvent> {

	OthelloApplication application;
	
	/**
	 * Instantiates a new SettingsButtonPressEventHandler.
	 * @param the OthelloApplication
	 */
	public SettingsButtonPressEventHandler(OthelloApplication application) {
		this.application = application;
	}
	
	/**
	 * Calls the showSettings() method in OthelloApplication which pauses the Game Timer, and changes the scene to the menu/settings scene.
	 */
	@Override
	public void handle(ActionEvent arg0) {
		try {
			GameTimer.getInstance().getTimer().pause();
			application.showSettings();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
