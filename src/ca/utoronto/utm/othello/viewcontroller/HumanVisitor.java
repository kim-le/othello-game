package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.layout.GridPane;
/**
 * HumanVisitor makes a move when visiting Othello.
 * @author Kim Le
 */
public class HumanVisitor implements PlayerVisitor{
	private VSpot src;
	/**
	 * Create a HumanVisitor
	 * @param src	the button that was clicked to make the move
	 */
	public HumanVisitor(VSpot src){
		this.src = src;
	}
	
	/**
	 * Make a move at the indicated location
	 * @param othello
	 */
	@Override
	public void visit(Othello othello) {
		othello.move(GridPane.getRowIndex(src), GridPane.getColumnIndex(src));
		othello.undone = false;
	}
}
