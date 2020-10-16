package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * The event handler for the submit/start button in the settings menu.
 */
public class MenuSubmitButtonPressEventHandler implements EventHandler<ActionEvent> {
	
	private TextField timeInput;
	private Stage stage;
	private Scene mainScene;
	private ToggleGroup p1Toggle;
	private ToggleGroup p2Toggle;
	private Othello othello;
	private ComboBox strategyInput;
	private HintButtonPressEventHandler hintHandler;
	
	/**
	 * Instantiates a new MenuSubmitButtonPressEventHandler.
	 * Takes in as parameters the OthelloApplication stage, the main game scene, the Othello game model
	 * and all controls in the menuScene that are used to change the settings of the game.
	 */
	public MenuSubmitButtonPressEventHandler(Stage stage, Scene mainScene, Othello o, 
						TextField timeInput, ToggleGroup p1Toggle, ToggleGroup p2Toggle, 
						ComboBox strategyInput, HintButtonPressEventHandler hintHandler) {
		super();
		this.othello = o;
		this.timeInput = timeInput;
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
		try 
		{
			GameTimer.getInstance().setP1Time(Integer.valueOf(timeInput.getText()));
			GameTimer.getInstance().setP2Time(Integer.valueOf(timeInput.getText()));
		} 
		catch (java.lang.NumberFormatException e) {}
		RadioButton p1Type = (RadioButton)this.p1Toggle.getSelectedToggle();
		RadioButton p2Type = (RadioButton)this.p2Toggle.getSelectedToggle();

		if (p1Type.getText() == "Human") {
			this.othello.setP1('H');
		} else if (p1Type.getText() == "Random") {
			this.othello.setP1('R');
		} else {
			this.othello.setP1('G');
		}
		if (p2Type.getText() == "Human") {
			this.othello.setP2('H');
		} else if (p2Type.getText() == "Random") {
			this.othello.setP2('R');
		} else {
			this.othello.setP2('G');
		}
		
		if (this.strategyInput.getValue() == "Greedy")
			hintHandler.setStrategy('G');
		else if(this.strategyInput.getValue() == "Improved Greedy")
			hintHandler.setStrategy('I');
		else
			hintHandler.setStrategy('R');
		
		this.othello.notifyObservers();
		
		GameTimer.getInstance().getTimer().play();
		stage.setScene(mainScene);
	}

}
