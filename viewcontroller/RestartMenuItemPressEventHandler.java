package ca.utoronto.utm.othello.viewcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * The event handler for the restart button.
 */
public class RestartMenuItemPressEventHandler implements EventHandler<ActionEvent> {

	private OthelloApplication application;
	
	/**
	 * Instantiates a new RestartMenuItemPressEventHandler.
	 * @param the OthelloApplication instance.
	 */
	public RestartMenuItemPressEventHandler(OthelloApplication application) {
		this.application = application;
	}
	
	/**
	 * Call the newGame() method in OthelloApplication, which restarts the GameTimer, and the state of OthelloApplication.
	 */
	@Override
	public void handle(ActionEvent arg0) {
		try {
			application.newGame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
