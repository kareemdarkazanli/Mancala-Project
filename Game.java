/**
 * The Game model.
 * @author Paul Merrill
 */

package project;

import java.util.*;
import javax.swing.event.*;

/**
 * A model for a Mancala game.
 *
 * Responsibilities:
 *   Keep track of the game state
 *   Notify views/listeners when game state changes
 *   Understands game logic and knows how to any game move
 *   Able to undo moves
 *   Keeps track of if an undo is possible
 *   Knows when game is over
 *   Knows player scores
 *   Receives player moves
 *   Knows which player’s turn it is
 *   Knows how many undos a player has used in a single turn
 *   Given number of starting stones
 */
class Game {
	/**
	 * Pit is the list of board pits and board mancalas.
	 */
	public enum Pit {
		A1, A2, A3, A4, A5, A6, MANCALA_A,
		B1, B2, B3, B4, B5, B6, MANCALA_B;

		/**
		 * Returns the next pit that comes after this one.
		 *
		 * @return the next pit after this one
		 */
		public Pit successor() {
			int index = (ordinal() + 1) % NUM_PITS;
			return values()[index];
		}

		/**
		 * Returns whether this pit is actually a mancala pit.
		 *
		 * @return whether this pit is a mancala pit
		 */
		public boolean isMancala() {
			return this == MANCALA_A || this == MANCALA_B;
		}
	}

	private enum Player {
		A, B;

		/**
		 * Returns the next player after the current player.
		 *
		 * @return the next player
		 */
		public Player next() {
			return this == A ? B : A;
		}

		/**
		 * Returns the previous player before the current player.
		 * It does the same thing as next().
		 *
		 * @return the previous player
		 */
		public Player previous() {
			return this == A ? B : A;
		}

		public String toString() {
			return this == A ? "Player A" : "Player B";
		}
	};

	private static int NUM_PITS = 14;
	private static int MAX_UNDOS = 3;

	/**
	 * Adds a ChangeListener to the model.
	 *
	 * @param listener
	 *            the ChangeListener to be added
	 */
	public void attachListener(ChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Attempts to make a move for the current player.
	 *
	 * @param clicked
	 *            the Pit the current player wants to pick up stones from
	 */
	public void performMove(Pit clicked) {
		if (clicked.isMancala()) {
			emit("You cannot pick stones out of a mancala! Still " + currentPlayer + "'s turn.");
			return;
		}
		if (!belongsTo(clicked, currentPlayer)) {
			emit("That's not your pit! Still " + currentPlayer + "'s turn.");
			return;
		}
		if (isEmpty(clicked)) {
			emit("That pit is empty! Still " + currentPlayer + "'s turn.");
			return;
		}
		int[] previousStones = stones.clone();
		boolean endedOnMancala = pickupStones(clicked);
		if (!canMove(Player.A) && !canMove(Player.B)) {
			emit("Game over! " + winningPlayer());
		} else if (endedOnMancala) {
			if (canMove(currentPlayer)) {
				// Previous player can't undo once their opponent has started their turn.
				canUndo = false;
				emit("OK move by " + currentPlayer + ". " +
					currentPlayer + "'s turn again.");
			} else {
				setupUndo(previousStones);
				currentPlayer = currentPlayer.next();
				emit("OK move by " + currentPlayer.previous() + ". " +
					currentPlayer.previous() + " cannot move again. " +
					currentPlayer + "'s turn.");
			}
		} else {
			if (canMove(currentPlayer.next())) {
				setupUndo(previousStones);
				currentPlayer = currentPlayer.next();
				emit("OK move by " + currentPlayer.previous() + ". " +
					"Now " + currentPlayer + "'s turn.");
			} else {
				// Current player can't undo their own last move. Unusual.
				canUndo = false;
				emit("OK move by " + currentPlayer + ". " +
					currentPlayer.next() + " cannot move. " +
					currentPlayer + "'s turn again.");
			}
		}
	}

	/**
	 * Attempts to undo the last move, if legal.
	 */
	public void performUndo() {
		if (undosTaken == MAX_UNDOS) {
			emit("Max undos used this turn! Still " + currentPlayer + "'s turn!");
			return;
		}
		if (!canUndo) {
			emit("You must take a turn first! Still " + currentPlayer + "'s turn!");
			return;
		}
		canUndo = false;
		undosTaken += 1;
		stones = previousStones.clone();
		currentPlayer = currentPlayer.previous();
		int remaining = MAX_UNDOS - undosTaken;
		emit(currentPlayer + " undid their last move! " + remaining + " undos remain! " + currentPlayer + "'s turn.");
	}

	/**
	 * Returns the number of stones that are currently in a particular pit.
	 *
	 * @param pit
	 *            the pit to check
	 * @return the number of stones in that pit
	 */
	public int getNumberOfStones(Pit pit) {
		return stones[pit.ordinal()];
	}
	
	/**
	 * @param theme  sets the theme of the game
	 */
	public void setTheme(VisualTheme theme)
	{
		this.theme = theme;
		isThemeSelected = true;
		for (ChangeListener listener : listeners)
			listener.stateChanged(new ChangeEvent(this));
		
	}
	
	/**
	 * @return  returns true if the theme has been selected.
	 */
	public boolean isThemeSelected()
	{
		return isThemeSelected;
	}
	/**
	 * @return  returns the number of stones per pit.
	 */
	public int getNumberOfStones()
	{
		return numberOfStones;
	}
	
	/**
	 * @return  returns the visual theme of the game
	 */
	public VisualTheme getTheme()
	{
		return theme;
	}
	
	/**
	 * @return  returns true if the game has started
	 */
	public boolean isGameStarted()
	{
		return isGameStarted;
	}
	
	/**
	 * @param b  sets whether or not the game has started. 
	 */
	public void setIsGameStarted(boolean b)
	{
		isGameStarted = b;
	}

	/**
	 * Set the number of stones starting out in each pit. Initially, the
	 * game has zero stones in each pit. The number of stones must be set
	 * before gameplay can commence.
	 *
	 * @param count
	 *            the number of stones to start each pit with
	 */
	public void setNumberOfStartingStones(int count) {
		for(int i = 0; i < 6; i ++)
		{
			stones[i] = count;
		}
		for(int i = 7; i < 13; i ++)
		{
			stones[i] = count;
		}
		numberOfStones = count;
		for (ChangeListener listener : listeners)
			listener.stateChanged(new ChangeEvent(this));
		

		emit("Ready to start. Begin with " + currentPlayer + "'s turn!");

	}

	/**
	 * Returns the latest message for the players of the game. The message
	 * changes as different actions are taken or attempted.
	 *
	 * @return the latest message for the players
	 */
	public String getMessageForPlayers() {
		return message;
	}

	private void emit(String message) {
		this.message = message;
		for (ChangeListener listener : listeners)
			listener.stateChanged(new ChangeEvent(this));
	}

	private boolean belongsTo(Pit pit, Player player) {
		switch (player) {
		case A: return Pit.A1.ordinal() <= pit.ordinal() &&
		               pit.ordinal() <= Pit.MANCALA_A.ordinal();
		case B: return Pit.B1.ordinal() <= pit.ordinal() &&
		               pit.ordinal() <= Pit.MANCALA_B.ordinal();
		default: throw new RuntimeException("Unknown player!");
		}
	}

	private boolean isEmpty(Pit pit) {
		return stones[pit.ordinal()] == 0;
	}

	private boolean pickupStones(Pit pit) {
		int pickedUp = stones[pit.ordinal()];
		stones[pit.ordinal()] = 0;
		for (; pickedUp > 0; pickedUp--) {
			pit = pit.successor();
			// Skip the opponent's mancala.
			if (pit.isMancala() && !belongsTo(pit, currentPlayer))
				pit = pit.successor();
			stones[pit.ordinal()]++;
		}
		return pit.isMancala();
	}

	private boolean canMove(Player player) {
		for (Pit pit : Pit.values()) {
			if (belongsTo(pit, player) && !pit.isMancala() && !isEmpty(pit)) {
				return true;
			}
		}
		return false;
	}

	private void setupUndo(int[] previousStones) {
		this.previousStones = previousStones;
		if (canUndo) {
			undosTaken = 0;
		}
		canUndo = true;
	}

	private String winningPlayer() {
		int cmp = stones[Pit.MANCALA_A.ordinal()] - stones[Pit.MANCALA_B.ordinal()];
		if (cmp > 0) {
			return "Player A wins!";
		} else if (cmp < 0) {
			return "Player B wins!";
		} else {
			return "It's a tie!";
		}
	}

	private int numberOfStones = 0;
	private VisualTheme theme = null;
	private boolean isThemeSelected = false;
	private boolean isGameStarted = false;
	private int[] stones = new int[NUM_PITS];
	private int[] previousStones = new int[NUM_PITS];
	private Player currentPlayer = Player.A;
	private boolean canUndo = false; // To prevent undoing twice in a row, which is illegal.
	private int undosTaken = 0; // A player can only undo MAX_UNDOS times per turn.
	private ArrayList<ChangeListener> listeners = new ArrayList<>();
	private String message = "Welcome to Mancala!";
}
