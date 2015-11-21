package project;

import java.awt.BorderLayout;

import javax.swing.*;

/* Responsibilities
	Shows “Player A”, “Player B”, “Mancala A”, and “Mancala B” labels
*/
class GameWithLabels extends JPanel {

	private GameBoard board;

	public GameWithLabels(Game game) {
		board = new GameBoard(game);

		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(new JTextArea("Player A --->"), BorderLayout.SOUTH);
		add(new JTextArea("<--- Player B"), BorderLayout.NORTH);
		add(new JTextArea("M\nA\nN\nC\nA\nL\nA\n\nA"), BorderLayout.EAST);
		add(new JTextArea("M\nA\nN\nC\nA\nL\nA\n\nB"), BorderLayout.WEST);
	
	}

	public void setTheme(VisualTheme theme) {
		board.setTheme(theme);
	}

/* Has
	player and mancala labels (black)
	GameBoard
*/
}
