package project;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

/* Responsibilities
	Knows which player it belongs to (e.g. player A)
	Fetches number of stones from Game during paint
	Draws manacala oval (black; shape from VisualTheme)
	Draws stones (color from VisualTheme)
	Draws using VisualTheme
*/
public class MancalaComponent extends JComponent {
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Ellipse2D.Double mancala = new Ellipse2D.Double();
		mancala.height = 120;
		mancala.width = 80;
		
		g2.setStroke(new BasicStroke(2));
		
		g2.draw(mancala);
	}
/* Has
	VisualTheme
	Game
*/
}
