package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A factory design ConcreteCreatorA that creates a Settings Menu for the starting screen.
 * @author Kim Le
 */
public class StartSettingsMenuFactory extends SettingsMenuFactory{
	private Stage stage;
	private Stage helpStage;
	private Scene scene;
	private Othello othello;
	private ComboBox hintList;
	private HintButtonPressEventHandler hintHandler;
	private TextField timeInput;
	private ToggleGroup p1Group;
	private ToggleGroup p2Group;
	
	/**
	 * Create a StartSettingsMenuFactory object.
	 * @param stage
	 * @param helpStage
	 * @param scene
	 * @param othello
	 * @param hintHandler
	 */
	public StartSettingsMenuFactory(Stage stage, Stage helpStage, Scene scene, Othello othello, HintButtonPressEventHandler hintHandler) {
		super(stage, helpStage, scene, othello, hintHandler);
		this.stage = stage;
		this.helpStage = helpStage;
		this.scene = scene;
		this.othello = othello;
		this.hintHandler = hintHandler;
	}

	/**
	 * Create a SettingsMenu object for the beginning of the game.
	 * @return SettingsMenu start settings menu
	 */
	public SettingsMenu createPane() {
		SettingsMenu menuRoot = new SettingsMenu();
		menuRoot.getChildren().add(new Label("Settings Menu"));
		menuRoot.getChildren().add(createPlayerSelect());
		// Remove this and just don't allow to select two non-human
		Label playerSelectNote = new Label(
				"Note: Selecting only non-human players is not playable and will run a simulation.");
		playerSelectNote.setFont(new Font("Arial", 11));
		playerSelectNote.setPadding(new Insets(0, 0, 10, 0));
		menuRoot.getChildren().add(playerSelectNote);
		// ---------
		menuRoot.getChildren().add(createTimer());
		menuRoot.getChildren().add(createHintBox());
		
		HBox buttonRoot = new HBox(5);
		
		Button submitButton = new Button("Start");
		submitButton.setOnAction(new MenuSubmitButtonPressEventHandler(stage, scene, othello, timeInput, p1Group, p2Group, hintList, hintHandler));
		buttonRoot.getChildren().add(submitButton);
		
		Button helpButton = new Button("Help");
		HelpPressEventHandler helpHandler = new HelpPressEventHandler(helpStage);
		helpButton.setOnAction(helpHandler);
		buttonRoot.getChildren().add(helpButton);
		
		menuRoot.getChildren().add(buttonRoot);
		
		return menuRoot;
	}
	
	/**
	 * Create a player HBox for the settings menu
	 * @return HBox player select pane
	 */
	protected HBox createPlayerSelect() {
		HBox playerSelect = new HBox();
		playerSelect.setPadding(new Insets(10, 0, 5, 0));
		playerSelect.getChildren().add(new Label("Player 1: "));
		VBox p1Choice = new VBox();
		final ToggleGroup p1Group = new ToggleGroup();
		RadioButton p1Human = new RadioButton("Human");
		p1Human.setPadding(new Insets(0, 30, 5, 5));
		p1Human.setToggleGroup(p1Group);
		p1Human.setSelected(true);
		RadioButton p1Random = new RadioButton("Random");
		p1Random.setPadding(new Insets(0, 30, 5, 5));
		p1Random.setToggleGroup(p1Group);
		RadioButton p1Greedy = new RadioButton("Greedy");
		p1Greedy.setPadding(new Insets(0, 30, 5, 5));
		p1Greedy.setToggleGroup(p1Group);
		RadioButton p1Improved = new RadioButton("Improved Greedy");
		p1Improved.setPadding(new Insets(0, 30, 5, 5));
		p1Improved.setToggleGroup(p1Group);
		p1Choice.getChildren().add(p1Human);
		p1Choice.getChildren().add(p1Random);
		p1Choice.getChildren().add(p1Greedy);
		p1Choice.getChildren().add(p1Improved);
		playerSelect.getChildren().add(p1Choice);
		playerSelect.getChildren().add(new Label("Player 2: "));
		VBox p2Choice = new VBox();
		final ToggleGroup p2Group = new ToggleGroup();
		RadioButton p2Human = new RadioButton("Human");
		p2Human.setPadding(new Insets(0, 5, 5, 5));
		p2Human.setToggleGroup(p2Group);
		p2Human.setSelected(true);
		RadioButton p2Random = new RadioButton("Random");
		p2Random.setPadding(new Insets(0, 5, 5, 5));
		p2Random.setToggleGroup(p2Group);
		RadioButton p2Greedy = new RadioButton("Greedy");
		p2Greedy.setPadding(new Insets(0, 5, 5, 5));
		p2Greedy.setToggleGroup(p2Group);
		RadioButton p2Improved = new RadioButton("Improved Greedy");
		p2Improved.setPadding(new Insets(0, 30, 5, 5));
		p2Improved.setToggleGroup(p2Group);
		p2Choice.getChildren().add(p2Human);
		p2Choice.getChildren().add(p2Random);
		p2Choice.getChildren().add(p2Greedy);
		p2Choice.getChildren().add(p2Improved);
		playerSelect.getChildren().add(p2Choice);
		this.p1Group = p1Group;
		this.p2Group = p2Group;
		return playerSelect;
	}
	
	/**
	 * Create a timer HBox for the settings menu
	 * @return HBox timer pane
	 */
	protected HBox createTimer() {
		HBox timeSettings = new HBox();
		timeSettings.setPadding(new Insets(10, 0, 10, 0));
		Label timeLabel = new Label("Time Limit (seconds): ");
		timeLabel.setPadding(new Insets(5, 0, 0, 0));
		timeSettings.getChildren().add(timeLabel);

		TextField timeInput = new TextField();
		timeInput.setText("300");
		timeSettings.getChildren().add(timeInput);
		this.timeInput = timeInput;
		return timeSettings;
	}
	
	/**
	 * Create a hint HBox for the settings menu
	 * @return HBox hint select pane
	 */
	protected HBox createHintBox() {
		HBox hintPane = new HBox();
		hintPane.setPadding(new Insets(10, 0, 10, 0));
		Label hintLabel = new Label("Hint Strategy: ");
		hintLabel.setPadding(new Insets(5, 0, 0, 0));
		hintPane.getChildren().add(hintLabel);
		
		ComboBox hintList = new ComboBox();
		hintList.getItems().addAll("Greedy", "Random", "Improved Greedy");
		hintList.setValue(hintList.getItems().get(1)); //default Random hint
		this.hintList = hintList;
		hintPane.getChildren().add(hintList);
		return hintPane;
	}

}
