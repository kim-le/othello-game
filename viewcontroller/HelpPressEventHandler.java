package ca.utoronto.utm.othello.viewcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * The event handler for the help button in the settings menu.
 * @author Kim Le
 */
public class HelpPressEventHandler implements EventHandler<ActionEvent> {

	Stage stage;
	
	/**
	 * Instantiates a new HelpPressEventHandler.
	 * @param stage		the help stage
	 */
	public HelpPressEventHandler(Stage stage) {
		super();
		this.stage = stage;

	}
	
	/**
	 * Opens the stage window
	 * @param arg0
	 */
	@Override
	public void handle(ActionEvent arg0) {
		try {
			GameTimer.getInstance().getTimer().pause();
			this.stage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
