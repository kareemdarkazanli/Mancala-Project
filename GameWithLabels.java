package project;

import java.awt.BorderLayout;

import javax.swing.*;

/* Responsibilities
	Shows “Player A”, “Player B”, “Mancala A”, and “Mancala B” labels
*/
class GameWithLabels extends JPanel {

	public GameWithLabels(Game game) {
		setLayout(new BorderLayout());
		add(new GameBoard(game), BorderLayout.CENTER);
		add(new JTextArea("Player A --->"), BorderLayout.SOUTH);
		add(new JTextArea("<--- Player B"), BorderLayout.NORTH);
		add(new JTextArea("M\nA\nN\nC\nA\nL\nA\n\nA"), BorderLayout.EAST);
		add(new JTextArea("M\nA\nN\nC\nA\nL\nA\n\nB"), BorderLayout.WEST);
	
	}


	/* Maybe! If not done by layout & children...
	public void paintComponent(Graphics g) {
	}
	*/
/* Has
	player and mancala labels (black)
	GameBoard
*/
}
