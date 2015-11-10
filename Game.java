import java.util.*;
import javax.swing.event.*;

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
	public enum Player { A, B };
	public enum Pit {
		A1, A2, A3, A4, A5, A6, MANCALA_A,
		B1, B2, B3, B4, B5, B6, MANCALA_B
	}
	private static int NUM_STONES = 14;

	public void attachListener(ChangeListener listener) {
		listeners.add(listener);
	}

	public void performMove(Pit clicked) {
		if (!belongsTo(clicked, currentPlayer)) {
			emit("That's not your pit!");
			return;
		}
		if (isEmpty(clicked)) {
			emit("That pit is empty!");
			return;
		}
		pickupStones(clicked);
		if (isGameOver()) {
			emit("Game over! " + winningPlayer());
		} else {
			// Reset undo.
			save();
			emit("OK move by " + playerName(currentPlayer) + ".");
		}
	}

	public void performUndo() {
		int remaining = 3 - undosTaken;
		if (undosTaken == 3) {
			emit("Max undos used this turn!");
			return;
		}
		if (!canUndo) {
			emit("You must take a turn first!");
			return;
		}
		canUndo = false; // Can't undo twice in a row.
		undosTaken += 1;
		rollback();
		emit("Move undone... " + (remaining - 1) + " left this turn!");
	}

	public int getNumberOfStones(Pit pit) {
		return stones[pit.ordinal()];
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setNumberOfStartingStones(int count) {
		Arrays.fill(stones, count);
	}

	public String getMessageForPlayers() {
		return message;
	}

	private void emit(String message) {
		this.message = message + " " + playerName(currentPlayer) + "'s turn!";
		for (ChangeListener listener : listeners)
			listener.stateChanged(new ChangeEvent(this));
	}

	private void save() {
		previousStones = Arrays.copyOf(stones, NUM_STONES);
	}

	private void rollback() {
		stones = Arrays.copyOf(previousStones, NUM_STONES);
	}

	private String playerName(Player player) {
		switch (player) {
		case A: return "Player A";
		case B: return "Player B";
		}
		return null;
	}

	private boolean belongsTo(Pit pit, Player player) {
		switch (player) {
		case A: return Pit.A1.ordinal() <= pit.ordinal() &&
		               pit.ordinal() <= Pit.MANCALA_A.ordinal();
		case B: return Pit.B1.ordinal() <= pit.ordinal() &&
		               pit.ordinal() <= Pit.MANCALA_B.ordinal();
		}
		return false;
	}

	private boolean isEmpty(Pit pit) {
		return stones[pit.ordinal()] == 0;
	}

	private void pickupStones(Pit pit) {
	}

	private boolean isGameOver() {
		return false;
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

	private int[] stones = new int[NUM_STONES];
	private int[] previousStones = new int[NUM_STONES];
	private Player currentPlayer = Player.A;
	private boolean canUndo = false;
	private int undosTaken = 0;
	private ArrayList<ChangeListener> listeners = new ArrayList<>();
	private String message = "Welcome to Mancala!";
}
