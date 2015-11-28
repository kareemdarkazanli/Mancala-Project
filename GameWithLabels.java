package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

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
		JPanel playerA = new JPanel();
		playerA.setLayout(new BorderLayout());
		playerA.add(new JLabel("																																				Player A --->"), BorderLayout.CENTER);
		playerA.setBackground(Color.WHITE);
		add(playerA, BorderLayout.SOUTH);
		JPanel playerB = new JPanel();
		playerB.setLayout(new BorderLayout());
		playerB.add(new JLabel("																																				<--- Player B"), BorderLayout.CENTER);
		playerB.setBackground(Color.WHITE);
		add(playerB, BorderLayout.NORTH);
		JTextArea mancalaA = new JTextArea("M\nA\nN\nC\nA\nL\nA\n\nA");
		mancalaA.setFocusable(false);
		add(mancalaA, BorderLayout.EAST);
		JTextArea mancalaB = new JTextArea("M\nA\nN\nC\nA\nL\nA\n\nB");
		mancalaB.setFocusable(false);
		add(mancalaB, BorderLayout.WEST);
		
	
	}

	public void setTheme(VisualTheme theme) {
		board.setTheme(theme);
	}

/* Has
	player and mancala labels (black)
	GameBoard
*/
}
