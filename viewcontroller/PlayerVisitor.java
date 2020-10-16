package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;

/**
 * PlayerVisitor makes a move when visiting Othello.
 * @author Kim Le
 */
public interface PlayerVisitor {
	/**
	 * Visit Othello and make a move.
	 * @param o 	Visitable Othello
	 */
	void visit(Othello o);
}
