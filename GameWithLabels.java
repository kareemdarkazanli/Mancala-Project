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
		playerA.add(new JLabel("Player A --->", SwingConstants.CENTER), BorderLayout.CENTER);
		playerA.setBackground(Color.WHITE);
		add(playerA, BorderLayout.SOUTH);
		JPanel playerB = new JPanel();
		playerB.setLayout(new BorderLayout());
		playerB.add(new JLabel("<--- Player B", SwingConstants.CENTER), BorderLayout.CENTER);
		playerB.setBackground(Color.WHITE);
		add(playerB, BorderLayout.NORTH);
		JLabel mancalaA = new JLabel("<html>M<br>A<br>N<br>C<br>A<br>L<br>A<br><br>A</html>");
		mancalaA.setVerticalAlignment(SwingConstants.CENTER);
		add(mancalaA, BorderLayout.EAST);
		JLabel mancalaB = new JLabel("<html>M<br>A<br>N<br>C<br>A<br>L<br>A<br><br>B</html>");
		mancalaB.setVerticalAlignment(SwingConstants.CENTER);
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
