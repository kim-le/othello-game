package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;

/**
 * A Greedy visitor makes a valid greedy move when visiting the Visitable Othello class
 * 
 */
public class GreedyVisitor implements PlayerVisitor{

	public GreedyVisitor() {
	}
	
	/**
	 * Performs greedy move when visiting
	 * @param othello
	 */
	@Override
	public void visit(Othello othello) {
		Othello othelloCopy = othello.copy();
		Move bestMove = new Move(0, 0);
		int bestMoveCount = othelloCopy.getCount(othello.getWhosTurn());
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				othelloCopy = othello.copy();
				if (othelloCopy.move(row, col) && othelloCopy.getCount(othello.getWhosTurn()) > bestMoveCount) {
					bestMoveCount = othelloCopy.getCount(othello.getWhosTurn());
					bestMove = new Move(row, col);
				}
			}
		}
		othello.move(bestMove.getRow(), bestMove.getCol());
		
		
	}
}
