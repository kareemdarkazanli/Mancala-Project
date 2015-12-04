package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author Kareem Darkazanli
* @version 1
*/

/**
* The GameBoard class is responsible for drawing the pits and
* the two mancalas
*/
public class GameBoard extends JPanel {

	private PitLabel mancalaA, mancalaB;
	private PitsPanel pits;
	
	/**
	 * Constructor for the GameBoard
	 * @param game  the model object holding the game's data.
	 */
	public GameBoard(Game game) {
	     mancalaA = new PitLabel(game, Game.Pit.MANCALA_A);
	     mancalaB = new PitLabel(game, Game.Pit.MANCALA_B);
	     pits = new PitsPanel(game);

	     setBorder(BorderFactory.createLineBorder(Color.black));   
	     setLayout(new BorderLayout()); 
	     add(mancalaA, BorderLayout.EAST);
	     add(mancalaB, BorderLayout.WEST);
	     add(pits, BorderLayout.CENTER);
	}

	/**
	 * @param theme  the gameboard theme selected by the user
	 * postcondition: [The game boards theme will be set to the parameter].
	 */
	public void setTheme(VisualTheme theme) {
		mancalaA.setTheme(theme);
		mancalaB.setTheme(theme);
		pits.setTheme(theme);
	}
	
}
