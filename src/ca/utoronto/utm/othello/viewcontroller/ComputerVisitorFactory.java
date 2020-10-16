package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;

/**
 * The factory for creating Computer Visitor opponents (greedy, random, improved greedy)
 */
public class ComputerVisitorFactory implements Observer {
	public ComputerVisitorFactory() {}

	/**
	 * Updates the game and performs a move with the correct Visitor when the player is a computer.
	 * Othello game accepts the visitor for them to make a move.
	 * @param o - the observable Othello
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		if (othello.getWhosTurn() == OthelloBoard.P1) {
			if (othello.getP1() == 'G') {
				othello.accept(new GreedyVisitor());
				othello.notifyObservers();
			} else if (othello.getP1() == 'R') {
				othello.accept(new RandomVisitor());
				othello.notifyObservers();
			} else if (othello.getP1() == 'I') {
				othello.accept(new ImprovedVisitor());
				othello.notifyObservers();
			}
		} else if (othello.getWhosTurn() == OthelloBoard.P2) {
			if (othello.getP2() == 'G') {
				othello.accept(new GreedyVisitor());
				othello.notifyObservers();
			} else if (othello.getP2() == 'R') {
				othello.accept(new RandomVisitor());
				othello.notifyObservers();
			} else if (othello.getP2() == 'I') {
				othello.accept(new ImprovedVisitor());
				othello.notifyObservers();
			}
		}
	}
}
