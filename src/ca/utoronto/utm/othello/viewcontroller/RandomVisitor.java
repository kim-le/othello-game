package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;
import java.util.Random;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
/**
 * A Random visitor makes a random valid move when visiting the Visitable Othello class
 * @author Kim Le
 */
public class RandomVisitor implements PlayerVisitor{
	Random rand = new Random();

	public RandomVisitor() {}
	
	/**
	 * A Random visitor makes a random valid move when visiting the Visitable Othello class
	 */
	@Override
	public void visit(Othello othello) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				Othello othelloCopy = othello.copy();
				if (othelloCopy.move(row, col))
					moves.add(new Move(row, col));
			}
		}
		Move randMove = moves.get(this.rand.nextInt(moves.size()));
		othello.move(randMove.getRow(), randMove.getCol());

		othello.notifyObservers();
		
	}
}
