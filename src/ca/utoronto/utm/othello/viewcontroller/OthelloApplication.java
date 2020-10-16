package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put
	// --module-path "/usr/lib/jvm/java-11-openjdk/lib" --add-modules
	// javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.
	Image black = new Image(getClass().getResourceAsStream("black.png"));
	Image white = new Image(getClass().getResourceAsStream("white.png"));
	ImageView settingsPic = new ImageView(new Image(getClass().getResourceAsStream("settings.png")));
	ImageView restartPic = new ImageView(new Image(getClass().getResourceAsStream("restart.png")));
	ImageView undoPic = new ImageView(new Image(getClass().getResourceAsStream("undo.png")));
	ImageView redoPic = new ImageView(new Image(getClass().getResourceAsStream("redo.png")));
	ImageView pausePic = new ImageView(new Image(getClass().getResourceAsStream("pause.png")));
	
	private Stage stage;
	Stage helpStage;
	private Scene menuScene;
	private Scene inGameMenuScene;
	private Scene helpScene;
	GameTimer gameTimer;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.helpStage = new Stage();
		this.helpStage.setTitle("Othello Instructions");
		// Create and hook up the Model, View and the controller

		// MODEL

		Othello othello = new Othello();

		// VIEW
		
		MenuBar menuBar = new MenuBar();
		// root pane of the main game scene
		VBox root = new VBox(menuBar);
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0))));

		// root.setPadding(new Insets(0, 5, 5, 5));

		gameTimer = GameTimer.getInstance();
		
		// Winner label at top of the game
		VWinner winnerLabel = new VWinner();
		winnerLabel.setPadding(new Insets(8, 15, 0, 15));
		winnerLabel.setTranslateX(95);
		root.getChildren().add(winnerLabel);
		
		// add the board button grid to the root pane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 15, 15, 18));
		root.getChildren().add(grid);

		// Switch opponent menu
		CheckMenuItem modeHuman = new CheckMenuItem("Human");
		CheckMenuItem modeRandom = new CheckMenuItem("Random");
		CheckMenuItem modeGreedy = new CheckMenuItem("Greedy");
		CheckMenuItem modeImproved = new CheckMenuItem("Improved Greedy");
		modeHuman.setSelected(true);
		Menu gameMode = new Menu("Switch Opponent", null, modeHuman, modeRandom, modeGreedy, modeImproved);
		
		GameModeEventHandler gameModeEventHandler = new GameModeEventHandler(gameMode, othello);
		modeHuman.setOnAction(gameModeEventHandler);
		modeRandom.setOnAction(gameModeEventHandler);
		modeGreedy.setOnAction(gameModeEventHandler);
		modeImproved.setOnAction(gameModeEventHandler);

		// Game menu
		MenuItem restartButton = new MenuItem("Restart");
		restartButton.setOnAction(new RestartMenuItemPressEventHandler(this));
		
		MenuItem hintButton = new MenuItem("Hint");
		HintButtonPressEventHandler hintHandler = new HintButtonPressEventHandler(othello, grid);
		hintButton.setOnAction(hintHandler);
		MenuItem helpButton = new MenuItem("Help");
		
		// Settings menu
		MenuItem settingsButton = new MenuItem("Settings");
		settingsButton.setOnAction(new SettingsButtonPressEventHandler(this));
		this.settingsPic.setFitHeight(18);
		this.settingsPic.setFitWidth(18);
		this.settingsPic.setPreserveRatio(true);
		Menu gameMenu = new Menu("Game", null, restartButton, hintButton, helpButton);
		Menu settingsMenu = new Menu(null,this.settingsPic, settingsButton);
		
		// Add menus to menu bar
		menuBar.getMenus().add(gameMode);
		menuBar.getMenus().add(gameMenu);
		menuBar.getMenus().add(settingsMenu);
		
		// playerPane containing player labels
		HBox playerPane = new HBox();
		VPlayerLabel player1Label = new VPlayerLabel("Black", new ImageView(black), OthelloBoard.P1);
		player1Label.setPadding(new Insets(18));
		playerPane.getChildren().add(player1Label);
		
		VPlayerLabel player2Label = new VPlayerLabel("White", new ImageView(white), OthelloBoard.P2);
		player2Label.setPadding(new Insets(18, 0, 18, 18));
		playerPane.getChildren().add(player2Label);
		root.getChildren().add(playerPane);

		// timerPane containing timer labels
		HBox timerPane = new HBox();
		VTimerLabel p1TimeLabel = new VTimerLabel(OthelloBoard.P1, othello);
		VTimerLabel p2TimeLabel = new VTimerLabel(OthelloBoard.P2, othello);
		p1TimeLabel.setPadding(new Insets(0, 30, 0, 10));
		p2TimeLabel.setPadding(new Insets(0, 10, 0, 30));
		
		timerPane.setPadding(new Insets(10));
		timerPane.getChildren().addAll(p1TimeLabel, p2TimeLabel);
		root.getChildren().add(timerPane);

		// Bottom game buttons
		// These buttons want to observe the button presses
		// Also make a factory
		// TODO make grid buttons observable
		HBox buttonPane = new HBox(15);
		GameManipulatorEventHandler gm = new GameManipulatorEventHandler(othello);
		buttonPane.setPadding(new Insets(15, 15, 15, 40));
		this.restartPic.setFitHeight(30);
		this.restartPic.setFitWidth(30);
		this.pausePic.setFitHeight(30);
		this.pausePic.setFitWidth(30);
		this.undoPic.setFitHeight(30);
		this.undoPic.setFitWidth(30);
		this.redoPic.setFitHeight(30);
		this.redoPic.setFitWidth(30);
		Button reset = new Button(null, this.restartPic);
		reset.setBackground(null);
		reset.setOnAction(new RestartMenuItemPressEventHandler(this));
		UndoButton btnUndo = new UndoButton();
		btnUndo.setBackground(null);
		btnUndo.setGraphic(this.undoPic);
		btnUndo.setOnAction(gm);
		RedoButton btnRedo = new RedoButton();
		btnRedo.setBackground(null);
		btnRedo.setGraphic(this.redoPic);
		btnRedo.setOnAction(gm);
		PauseButton btnPause = new PauseButton();
		btnPause.setBackground(null);
		btnPause.setGraphic(this.pausePic);
		btnPause.setOnAction(gm);
		buttonPane.getChildren().add(reset);
		buttonPane.getChildren().add(btnUndo);
		buttonPane.getChildren().add(btnRedo);
		buttonPane.getChildren().add(btnPause);
		root.getChildren().add(buttonPane);
		
		// CONTROLLER		
		
		ComputerVisitorFactory computerMove = new ComputerVisitorFactory();
		GameModeObserver gameModeObserver = new GameModeObserver(gameMode);
		// Observer/Observable, calls update method when button is pressed
		// the update method calls the updateObservers method
		BoardButtonPressEventHandler boardButtonsEventHandler = new BoardButtonPressEventHandler(othello); 

		// POPULATE GRID WITH BUTTONS
		VSpot btn;

		for (int i = 0; i < Othello.DIMENSION; i++)
			for (int j = 0; j < Othello.DIMENSION; j++) {
				btn = new VSpot(i, j);
				btn.setPadding(new Insets(0));
				btn.setPrefSize(35, 35); // added a way to fix the buttons so they're not poorly formatted
				
				// MODEL->VIEW hookup

				othello.attach(btn);
				gameTimer.attach(btn);
				// VIEW->CONTROLLER hookup

				btn.setOnAction(boardButtonsEventHandler);
				grid.add(btn, j, i);
			}

		// VIEW->CONTROLLER hookup

		// MODEL->VIEW hookup
		gameTimer.attach(p1TimeLabel);
		gameTimer.attach(p2TimeLabel);
		gameTimer.attach(winnerLabel);
		othello.attach(gameModeObserver);
		othello.attach(computerMove);
		othello.attach(winnerLabel);
		othello.attach(player1Label);
		othello.attach(player2Label);
		othello.attach(gameTimer);
		othello.notifyObservers(); // call this last
		
		// SCENE
		Scene scene = new Scene(root);
		stage.setTitle("Othello");
		
		// SCENE - SETTINGS MENU
		SettingsMenuFactory settingsFactory = new SettingsMenuFactory(stage, helpStage, scene, othello, hintHandler);
		menuScene = new Scene(settingsFactory.createPane(false));
		inGameMenuScene = new Scene(settingsFactory.createPane(true));
		helpScene = new Scene(settingsFactory.createPane());
		this.helpStage.setScene(helpScene);
		this.helpStage.initModality(Modality.APPLICATION_MODAL); // keeps focus on instructions window
		this.helpStage.setOnCloseRequest(new EventHandler<WindowEvent>() { 
		    @Override
		    public void handle(WindowEvent event) {
		        gameTimer.getTimer().play(); // resumes game when exiting instructions window
		    }
		});
		HelpPressEventHandler helpHandler = new HelpPressEventHandler(this.helpStage);
		helpButton.setOnAction(helpHandler);


		// initial update
		// runs before gui to show the initial board layout

		// LAUNCH THE GUI
		stage.setScene(menuScene);
		stage.show();
	}
	
	/**
	 * Resets the state of the application and the GameTimer.
	 * @throws any exception
	 */
	public void newGame() throws Exception {
		gameTimer.timer.pause();
		gameTimer.setP1Time(300);
		gameTimer.setP2Time(300);
		this.stop();
		this.start(this.stage);
	}
	
	/**
	 * Pause the game timer, and display the settings/menu scene.
	 * @throws any exception
	 */
	public void showSettings() throws Exception {
		this.gameTimer.timer.pause();
		this.stage.setScene(this.inGameMenuScene);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
