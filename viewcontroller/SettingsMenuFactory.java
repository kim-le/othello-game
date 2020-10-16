package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A factory design Creator that creates the desired SettingsMenu.
 * @author Kim Le
 */
public  class SettingsMenuFactory{
	private Stage stage;
	private Stage helpStage;
	private Scene scene;
	private Othello othello;
	private HintButtonPressEventHandler hintHandler;

	
	/**
	 * Create a SettingsMenuFactory object.
	 * @param stage
	 * @param helpStage
	 * @param scene
	 * @param othello
	 * @param hintHandler
	 */
	public SettingsMenuFactory(Stage stage, Stage helpStage, Scene scene, Othello othello, HintButtonPressEventHandler hintHandler) {
		this.stage = stage;
		this.helpStage = helpStage;
		this.scene = scene;
		this.othello = othello;
		this.hintHandler = hintHandler;
	}
	
	/**
	 * Create a SettingsMenuFactory object.
	 */
	public SettingsMenuFactory() {}
	
	/**
	 * Create a HelpSettingsMenuFactory object.
	 * @return VBox
	 */
	public SettingsMenu createPane() {
		HelpSettingsMenuFactory helpMenu = new HelpSettingsMenuFactory();
		return (SettingsMenu)helpMenu.createPane();
	}
	/**
	 * Create a InGameSettingsMenuFactory object or StartSettingsMenuFactory object.
	 * @return VBox
	 */
	public SettingsMenu createPane(boolean inGame) {
		if (inGame){
			InGameSettingsMenuFactory inGameSettingsMenu = new InGameSettingsMenuFactory(stage, helpStage, scene, othello, hintHandler);
			return (SettingsMenu)inGameSettingsMenu.createPane();
		} else {
			StartSettingsMenuFactory settingsMenu = new StartSettingsMenuFactory(stage, helpStage, scene, othello, hintHandler);
			return (SettingsMenu)settingsMenu.createPane();
		}
	}

}
