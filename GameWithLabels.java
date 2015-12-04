package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author Kareem Darkazanli
* @version 1
*/

/**
* The GameWithLabels class is responsible for drawing the game board
* with its surrounding labels
*/
class GameWithLabels extends JPanel {

	private GameBoard board;
	
	/**
	 * Constructor for the GameWithLabels
	 * @param game  the model object holding the game's data.
	 */
	public GameWithLabels(Game game) {
		
		board = new GameBoard(game);

		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		JPanel playerA = new JPanel();
		playerA.setLayout(new BorderLayout());
		playerA.add(new JLabel("Player A --->", SwingConstants.CENTER), BorderLayout.SOUTH);
		playerA.add(new JLabel("																			A1									A2										A3											A4									A5										A6"), BorderLayout.NORTH);
		playerA.setBackground(Color.WHITE);	
		add(playerA, BorderLayout.SOUTH);
		JPanel playerB = new JPanel();
		playerB.setLayout(new BorderLayout());
		playerB.add(new JLabel("<--- Player B", SwingConstants.CENTER), BorderLayout.NORTH);
		playerB.add(new JLabel("																			B6										B5											B4											B3										B2										B1"), BorderLayout.SOUTH);
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

	/**
	 * @param theme  the gameboard theme selected by the user
	 * postcondition: [The game boards theme will be set to the parameter].
	 */
	public void setTheme(VisualTheme theme) {
		board.setTheme(theme);
	}
}
