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
		if (!gameStarted) {
			emit("The game has not started yet.");
			return;
		}
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
		int[] originalStones = stones.clone();
		boolean endedOnMancala = pickupStones(clicked);
		if (isGameOver()) {
			emit("Game over! " + winningPlayer());
		} else if (endedOnMancala) {
			canUndo = false; // Previous player can't undo once their opponent has started their turn.
			emit("OK move by " + currentPlayer + ". " +
				currentPlayer + "'s turn again.");
		} else {
			advanceTurn(originalStones);
			emit("OK move by " + currentPlayer.previous() + ". " +
				"Now " + currentPlayer + "'s turn.");
		}
	}

	/**
	 * Attempts to undo the last move, if legal.
	 */
	public void performUndo() {
		if (!gameStarted) {
			emit("The game has not started yet.");
			return;
		}
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
		rollback();
		int remaining = MAX_UNDOS - undosTaken;
		emit("Move undone... " + remaining + " left this turn! Still " + currentPlayer + "'s turn!");
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
	 * Set the number of stones starting out in each pit. Initially, the
	 * game has zero stones in each pit. The number of stones must be set
	 * before gameplay can commence.
	 *
	 * @param count
	 *            the number of stones to start each pit with
	 */
	public void setNumberOfStartingStones(int count) {
		gameStarted = true;
		Arrays.fill(stones, count);
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

	private void save() {
		previousStones = Arrays.copyOf(stones, NUM_PITS);
	}

	private void rollback() {
		stones = Arrays.copyOf(previousStones, NUM_PITS);
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

	private boolean isGameOver() {
		for (Pit pit : Pit.values()) {
			if (!pit.isMancala() && !isEmpty(pit))
				return false;
		}
		return true;
	}

	private void advanceTurn(int[] previousStones) {
		// Create new undo button state.
		this.previousStones = previousStones;
		if (canUndo) {
			// The player cannot undo any more. It is now the next
			// player's turn.
			undosTaken = 0;
		}
		canUndo = true;

		currentPlayer = currentPlayer.next();
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

	private boolean gameStarted = false;
	private int[] stones = new int[NUM_PITS];
	private int[] previousStones = new int[NUM_PITS];
	private Player currentPlayer = Player.A;
	private boolean canUndo = false; // To prevent undoing twice in a row, which is illegal.
	private int undosTaken = 0; // A player can only undo MAX_UNDOS times per turn.
	private ArrayList<ChangeListener> listeners = new ArrayList<>();
	private String message = "Welcome to Mancala!";
}
