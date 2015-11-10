package project;

import java.util.ArrayList;

/* Responsibilities
	Keep track of the game state
	Notify views/listeners when game state changes
	Understands game logic and knows how to any game move
	Able to undo moves
	Keeps track of if an undo is possible
	Knows when game is over
	Knows player scores
	Receives player moves
	Knows which player’s turn it is
	Knows how many undos a player has used in a single turn
	Given number of starting stones
*/
class Game {
	public void attachListener() {
	}
	public void performMove() {
		// emit change event
	}
	public void performUndo() {
		// emit change event
	}
	public ArrayList<Integer> getNumberOfStones() {
		return new ArrayList<>();
	}
	public int getCurrentPlayer() {
		// Returns 1 or 2.
		return 1;
	}
	public void setNumberOfStartingStones() {
		// emit change event
	}
	public String getMessageForPlayers() {
		return "";
	}

/* Has
	list of stones for pits & mancalas
	list of previous stones for pits & mancalas
	list of ChangeListeners
*/
}
