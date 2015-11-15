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
		A1(), A2(), A3(), A4(), A5(), A6(), MANCALA_A(),
		B1(), B2(), B3(), B4(), B5(), B6(), MANCALA_B();

		private Pit() {
			fromOrdinal.put(ordinal(), this);
		}

		/**
		 * Returns the next pit that comes after this one.
		 *
		 * @return the next pit after this one
		 */
		public Pit successor() {
			int index = (ordinal() + 1) % fromOrdinal.size();
			return fromOrdinal.get(index);
		}

		/**
		 * Returns whether this pit is actually a mancala pit.
		 *
		 * @return whether this pit is a mancala pit
		 */
		public boolean isMancala() {
			return this == MANCALA_A || this == MANCALA_B;
		}

		private HashMap<Integer,Pit> fromOrdinal = new HashMap<>();
	}
	private enum Player { A, B };
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
		if (!belongsTo(clicked, currentPlayer)) {
			emit("That's not your pit!");
			return;
		}
		if (isEmpty(clicked)) {
			emit("That pit is empty!");
			return;
		}
		save();
		pickupStones(clicked);
		if (isGameOver()) {
			emit("Game over! " + winningPlayer());
			return;
		}
		if (canUndo) {
			undosTaken = 0;
		}
		canUndo = true;
		emit("OK move by " + playerName(currentPlayer) + ".");
	}

	/**
	 * Attempts to undo the last move, if legal.
	 */
	public void performUndo() {
		if (undosTaken == MAX_UNDOS) {
			emit("Max undos used this turn!");
			return;
		}
		if (!canUndo) {
			emit("You must take a turn first!");
			return;
		}
		canUndo = false;
		undosTaken += 1;
		rollback();
		int remaining = MAX_UNDOS - undosTaken;
		emit("Move undone... " + remaining + " left this turn!");
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
		Arrays.fill(stones, count);
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
		this.message = message + " " + playerName(currentPlayer) + "'s turn!";
		for (ChangeListener listener : listeners)
			listener.stateChanged(new ChangeEvent(this));
	}

	private void save() {
		previousStones = Arrays.copyOf(stones, NUM_PITS);
	}

	private void rollback() {
		stones = Arrays.copyOf(previousStones, NUM_PITS);
	}

	private String playerName(Player player) {
		switch (player) {
		case A: return "Player A";
		case B: return "Player B";
		default: throw new RuntimeException("Unknown player!");
		}
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

	private void pickupStones(Pit pit) {
		for (int pickedUp = stones[pit.ordinal()]; pickedUp > 0; pickedUp--) {
			pit = pit.successor();
			// Skip the opponent's mancala.
			if (pit.isMancala() && !belongsTo(pit, currentPlayer))
				pit = pit.successor();
			stones[pit.ordinal()]++;
		}
		// TODO: If we ended on our own mancala, we get another turn.
	}

	private boolean isGameOver() {
		for (int i = 0; i < NUM_PITS; i++) {
			Pit pit = Pit.fromOrdinal(i);
			if (!isMancala(pit) && !isEmpty(pit))
				return false;
		}
		return true;
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

	private int[] stones = new int[NUM_PITS];
	private int[] previousStones = new int[NUM_PITS];
	private Player currentPlayer = Player.A;
	private boolean canUndo = false; // Can't undo twice in a row.
	private int undosTaken = 0;
	private ArrayList<ChangeListener> listeners = new ArrayList<>();
	private String message = "Welcome to Mancala!";
}
