package project;

import java.awt.BasicStroke;
import java.awt.Component;
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
public class MancalaLabel extends JLabel {
	
	
	private static int ICON_WIDTH = 40;
	private static int ICON_HEIGHT = 160;
	
	public MancalaLabel(Game game) {
		super(mancala);
	}
	
	static Icon mancala = new Icon()
	{
         public int getIconWidth() { return ICON_WIDTH; }
         public int getIconHeight() { return ICON_HEIGHT; }
         
         public void paintIcon(Component c, Graphics g, int x, int y)
         {
            Graphics2D g2 = (Graphics2D) g;       
            Ellipse2D.Double pit = new Ellipse2D.Double();
            pit.height = 160;
            pit.width = 40;
            g2.draw(pit);
            
         }
	};
	
	
	
	/*public void paintComponent(Graphics g) {
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
