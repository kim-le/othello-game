package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * The event handler for the submit button in the in-game settings menu.
 */
public class InGameMenuSubmitButtonPressEventHandler implements EventHandler<ActionEvent> {
	
	private Stage stage;
	private Scene mainScene;
	private ToggleGroup p1Toggle;
	private ToggleGroup p2Toggle;
	private Othello othello;
	private ComboBox strategyInput;
	private HintButtonPressEventHandler hintHandler;
	
	/**
	 * Instantiates a new InGameMenuSubmitButtonPressEventHandler.
	 * @param stage			the game stage
	 * @param mainScene		the game scene
	 * @param o				Othello
	 * @param p1Toggle		Player 1 ToggleGroup
	 * @param p2Toggle		Player 2 ToggleGroup
	 * @param strategyInput	Game hint strategy input
	 * @param hintHandler	Hint button event handler
	 */
	public InGameMenuSubmitButtonPressEventHandler(Stage stage, Scene mainScene, Othello o, 
						ToggleGroup p1Toggle, ToggleGroup p2Toggle, ComboBox strategyInput, 
						HintButtonPressEventHandler hintHandler) {
		super();
		this.othello = o;
		this.stage = stage;
		this.mainScene = mainScene;
		this.p1Toggle = p1Toggle;
		this.p2Toggle = p2Toggle;
		this.strategyInput = strategyInput;
		this.hintHandler = hintHandler;
	}
	
	/**
	 * Obtains the input of all of the controls in the menu scene, and 
	 * sets their respective variables appropriately. 
	 */
	@Override
	public void handle(ActionEvent arg0) {
		RadioButton p1Type = (RadioButton)this.p1Toggle.getSelectedToggle();
		RadioButton p2Type = (RadioButton)this.p2Toggle.getSelectedToggle();

		if (p1Type.getText() == "Human") {
			this.othello.setP1('H');
		} else if (p1Type.getText() == "Random") {
			this.othello.setP1('R');
		} else if (p1Type.getText() == "Improved Greedy") {
			this.othello.setP1('I');
		} else {
			this.othello.setP1('G');
		}
		if (p2Type.getText() == "Human") {
			this.othello.setP2('H');
		} else if (p2Type.getText() == "Random") {
			this.othello.setP2('R');
		} else if (p2Type.getText() == "Improved Greedy") {
			this.othello.setP2('I');
		} else {
			this.othello.setP2('G');
		}
		
		if (this.strategyInput.getValue() == "Greedy")
			this.hintHandler.setStrategy('G');
		else if(this.strategyInput.getValue() == "Improved Greedy")
			this.hintHandler.setStrategy('I');
		else 
			this.hintHandler.setStrategy('R');
		
		this.othello.notifyObservers();
		
		GameTimer.getInstance().getTimer().play();
		stage.setScene(mainScene);
	}

}
