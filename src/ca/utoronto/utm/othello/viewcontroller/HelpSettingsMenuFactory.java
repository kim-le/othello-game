package ca.utoronto.utm.othello.viewcontroller;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



/**
 * A factory design ConcreteCreatorB that creates a Help Settings Menu.
 *
 */
public class HelpSettingsMenuFactory extends SettingsMenuFactory {
	private Stage stage;
	
	public HelpSettingsMenuFactory() {}
	
	/**
	 * Create a Help SettingsMenu for Othello instructions
	 * @return SettingsMenu help menu
	 */
	public SettingsMenu createPane() {
		this.stage = new Stage();
		SettingsMenu menuRoot = new SettingsMenu();
		menuRoot.setPadding(new Insets(20));
		
		Label titleLabel = new Label("How to play");
		titleLabel.setFont(new Font("Arial", 16));
		titleLabel.setPadding(new Insets(10, 10, 0, 10));
		menuRoot.getChildren().add(titleLabel);

		Label help1Label = new Label("Both players begin the game with two pieces on the board in the four centre squares. "
				+ "Players take turns placing one piece down in a legally acceptable position and then flipping "
				+ "any of the opposing player’s pieces where applicable. ");
		help1Label.setMaxWidth(500);
		help1Label.setWrapText(true);
		help1Label.setPadding(new Insets(10, 10, 0, 10));
		menuRoot.getChildren().add(help1Label);
		
		Label help2Label = new Label("A legal move is one that consists of, for example, a straight line "
				+ "(vertical, horizontal, or diagonal) on the board made up of a black piece at both ends and only "
				+ "white pieces in between.");
	
		help2Label.setMaxWidth(500);
		help2Label.setWrapText(true);
		help2Label.setPadding(new Insets(5, 10, 0, 10));
		menuRoot.getChildren().add(help2Label);
		
		Label help3Label = new Label("When a player achieves this, they flip all the white "
				+ "pieces in between the two black pieces so that the line becomes entirely black.");
		help3Label.setMaxWidth(500);
		help3Label.setWrapText(true);
		help3Label.setPadding(new Insets(5, 10, 10, 10));
		menuRoot.getChildren().add(help3Label);
		
		Label winningLabel = new Label("Winning the game");
		winningLabel.setFont(new Font("Arial", 16));
		winningLabel.setPadding(new Insets(10, 10, 0, 10));
		menuRoot.getChildren().add(winningLabel);
		
		Label help4Label = new Label(
				"The game is finished when there are no more possible legal moves for either competitor, "
				+ "or when a player's timer has run out. If there are no moves left, the player with the"
				+ "higher number of game pieces on wins. If a player's timer runs out, the other player wins.");
		help4Label.setMaxWidth(500);
		help4Label.setWrapText(true);
		help4Label.setPadding(new Insets(10));
		menuRoot.getChildren().add(help4Label);
		
		Label note = new Label(
				"(Close this window to play the game)");
		note.setFont(new Font("Arial", 11));
		note.setPadding(new Insets(10));
		menuRoot.getChildren().add(note);
		
		return menuRoot;
	}
}