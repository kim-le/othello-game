package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
/**
 * Observes the state of the Othello game and whether a player change has occurred.
 * @author Kim Le
 */
public class GameModeObserver implements Observer {
	Menu menu;

	/**
	 * Create a GameModeObserver
	 * @param menu 	the game menu
	 */
	public GameModeObserver(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Ensures the correct menu is checked
	 * @param o		Othello
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		ObservableList<MenuItem> items = this.menu.getItems();

		if (othello.getWhosTurn() == OthelloBoard.P1) {
			if (othello.getP2() == 'G') {
				for (MenuItem menuItem : items) {
					if (menuItem.getText() == "Greedy") {
						CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
						checkMenuItem.setSelected(true);
					} else {
						CheckMenuItem otherMenuItem = (CheckMenuItem) menuItem;
						otherMenuItem.setSelected(false);
					}
				}
			} else if (othello.getP2() == 'R') {
				for (MenuItem menuItem : items) {
					if (menuItem.getText() == "Random") {
						CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
						checkMenuItem.setSelected(true);
					} else {
						CheckMenuItem otherMenuItem = (CheckMenuItem) menuItem;
						otherMenuItem.setSelected(false);
					}
				}
			} else if (othello.getP2() == 'I') {
				for (MenuItem menuItem : items) {
					if (menuItem.getText() == "Improved Greedy") {
						CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
						checkMenuItem.setSelected(true);
					} else {
						CheckMenuItem otherMenuItem = (CheckMenuItem) menuItem;
						otherMenuItem.setSelected(false);
					}
				}
			}
		} else if (othello.getWhosTurn() == OthelloBoard.P2) {
			if (othello.getP1() == 'G') {
				for (MenuItem menuItem : items) {
					if (menuItem.getText() == "Greedy") {
						CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
						checkMenuItem.setSelected(true);
					} else {
						CheckMenuItem otherMenuItem = (CheckMenuItem) menuItem;
						otherMenuItem.setSelected(false);
					}
				}
			} else if (othello.getP1() == 'R') {
				for (MenuItem menuItem : items) {
					if (menuItem.getText() == "Random") {
						CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
						checkMenuItem.setSelected(true);
					} else {
						CheckMenuItem otherMenuItem = (CheckMenuItem) menuItem;
						otherMenuItem.setSelected(false);
					}
				}
			} else if (othello.getP1() == 'I') {
				for (MenuItem menuItem : items) {
					if (menuItem.getText() == "Improved Greedy") {
						CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
						checkMenuItem.setSelected(true);
					} else {
						CheckMenuItem otherMenuItem = (CheckMenuItem) menuItem;
						otherMenuItem.setSelected(false);
					}
				}
			}
		}
	}
}
