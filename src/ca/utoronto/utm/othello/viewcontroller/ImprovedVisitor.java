package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;

import ca.utoronto.utm.othello.model.Othello;
/**
 * An Improved visitor makes a valid improved greedy move when visiting the Visitable Othello class
 * 
 */
public class ImprovedVisitor implements PlayerVisitor{
	
	/**
	 * Performs improved greedy move when visiting (high priority to corners, sides, middle)
	 * @param othello
	 */
	@Override
	public void visit(Othello othello) {
		Othello othelloCopy = othello.copy();
		
		//check for corners
		int corners[][] = {{0,0},{Othello.DIMENSION,0},{0,Othello.DIMENSION},{Othello.DIMENSION,Othello.DIMENSION}};
		for(int i = 0; i < 4; i++) {
			if(othelloCopy.board.hasMoveAt(corners[i][0], corners[i][1], othello.getWhosTurn())){
				othello.move(corners[i][0], corners[i][1]);
				return;
			}
		}
		
		//check for sides
		for(int i = 0; i < Othello.DIMENSION; i++) {
			for(int j = 0; j < Othello.DIMENSION; j++) {
				if((i == 0 || i == Othello.DIMENSION || j == 0 || j == Othello.DIMENSION) && othelloCopy.board.hasMoveAt(i, j, othello.getWhosTurn())) {
					othello.move(i, j);
					return;
				}
			}
		}
		
		//check for middle supremacy :)
		Move bestMove = new Move(0, 0);
		int initMidCount = middle(othello.getWhosTurn(), othelloCopy);
		int midCount = middle(othello.getWhosTurn(), othelloCopy);
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				othelloCopy = othello.copy();
				if (othelloCopy.move(row, col) && middle(othello.getWhosTurn(),othelloCopy) > midCount) {
					midCount = middle(othello.getWhosTurn(),othelloCopy);
					bestMove = new Move(row, col);
				}
			}
		}
		if(midCount > initMidCount) {
			othello.move(bestMove.getRow(), bestMove.getCol());
		}
		
		//default greedy
		else {
			othello.accept(new GreedyVisitor());
		}
	}
	
	/**
	 * checks the amount of player's tokens in the middle 4 pieces on othello
	 * @param player
	 * @param othello
	 * @return number of tokens for player on othello in the middle square
	 */
	private int middle(char player, Othello othello) {
		int count = 0;
		int mid[][] = {{3,3},{4,3},{3,4},{4,4}};
		for(int i = 0; i < 4; i++) {
			if(othello.getToken(mid[i][0],mid[i][1]) == player) {
				count ++;
			}
		}
		return count;
	}
	
}