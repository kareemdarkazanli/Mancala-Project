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
	
	public GameBoard(Game game) {
					
	     setBorder(BorderFactory.createLineBorder(Color.black));   
	     setLayout(new BorderLayout()); 
	     add(new MancalaPanel(game), BorderLayout.EAST);
	     add(new MancalaPanel(game), BorderLayout.WEST);
	     add(new PitsPanel(game), BorderLayout.CENTER);
	}
	
/* Has
	two MancalaPanels
	PitsPanel
*/
}
