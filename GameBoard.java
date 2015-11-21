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

/* Responsibilities
	Draws game board border (black)
*/
public class GameBoard extends JPanel {

	private MancalaLabel mancalaA, mancalaB;
	private PitsPanel pits;
	
	public GameBoard(Game game) {
	     mancalaA = new MancalaLabel(game);
	     mancalaB = new MancalaLabel(game);
	     pits = new PitsPanel(game);

	     setBorder(BorderFactory.createLineBorder(Color.black));   
	     setLayout(new BorderLayout()); 
	     add(mancalaA, BorderLayout.EAST);
	     add(mancalaB, BorderLayout.WEST);
	     add(pits, BorderLayout.CENTER);
	}

	public void setTheme(VisualTheme theme) {
		mancalaA.setTheme(theme);
		mancalaB.setTheme(theme);
		pits.setTheme(theme);
	}
	
/* Has
	two MancalaPanels
	PitsPanel
*/
}
