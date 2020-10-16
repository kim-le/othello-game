package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
/**
 * An EventHandler that handles changes in game mode (player switch)
 *
 */
public class GameModeEventHandler implements EventHandler<ActionEvent> {
	private Menu menu;
	private CheckMenuItem item;
	private Othello othello;
	/**
	 * Create a GameModeEventHandler
	 * @param menu	Game menu
	 * @param o		Othello
	 */
	public GameModeEventHandler(Menu menu, Othello o) {
		super();
		this.menu = menu;
		this.othello = o;
	}
	
	/**
	 * Set the opponent to the chosen Player
	 * @param event		the clicked menu option
	 */
	@Override
	public void handle(ActionEvent event) {
		this.item = (CheckMenuItem) event.getSource();
		this.item.setSelected(true);
		char player = this.othello.getWhosTurn();
		if (this.item.getText() == "Human") {
			if (OthelloBoard.otherPlayer(player) == OthelloBoard.P1) {
				this.othello.setP1('H');
			} else {
				this.othello.setP2('H');
			}
		} else if (this.item.getText() == "Greedy") {
			if (OthelloBoard.otherPlayer(player) == OthelloBoard.P1) {
				this.othello.setP1('G');
			} else {
				this.othello.setP2('G');
			}
		} else if (this.item.getText() == "Random") {
			if (OthelloBoard.otherPlayer(player) == OthelloBoard.P1) {
				this.othello.setP1('R');
			} else {
				this.othello.setP2('R');
			}
		} else if (this.item.getText() == "Improved Greedy") {
			if (OthelloBoard.otherPlayer(player) == OthelloBoard.P1) {
				this.othello.setP1('I');
			} else {
				this.othello.setP2('I');
			}
		}

		ObservableList<MenuItem> items = this.menu.getItems();
		// uncheck all other menu items
		for (MenuItem menuItem : items) {
			if (menuItem != this.item) {
				CheckMenuItem otherMenuItem = (CheckMenuItem) menuItem;
				otherMenuItem.setSelected(false);
			}
		}
		othello.notifyObservers();
	}
}
