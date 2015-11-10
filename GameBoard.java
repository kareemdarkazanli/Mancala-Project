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
	
	private static int ICON_WIDTH = 60;
	private static int ICON_HEIGHT = 60;
	
	public GameBoard(){
			
		Icon pit = new Icon()
	      {
	         public int getIconWidth() { return ICON_WIDTH; }
	         public int getIconHeight() { return ICON_HEIGHT; }
	         
	         public void paintIcon(Component c, Graphics g, int x, int y)
	         {
	            Graphics2D g2 = (Graphics2D) g;

	         
	            //Get number of pebbles from model and then draw them
	            
	            Ellipse2D.Double pit = new Ellipse2D.Double();
	            pit.height = 60;
	            pit.width = 40;
	            g2.draw(pit);
	            
	         }
	                    
	      };
	      
	      Icon mancala = new Icon()
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
	      
	      Icon border = new Icon()
	      {

		         public int getIconWidth() { return ICON_WIDTH; }
		         public int getIconHeight() { return ICON_HEIGHT; }
		         
		         public void paintIcon(Component c, Graphics g, int x, int y)
		         {
		            Graphics2D g2 = (Graphics2D) g;       
		            Rectangle2D.Double pit = new Rectangle2D.Double();
		            pit.height = 160;
		            pit.width = 40;
		            g2.draw(pit);
		            
		         }
	      };

		//mancalaPanel1.setLayout(new BorderLayout());
		//mancalaPanel1.add(new MancalaComponent());
		
		JPanel mancalaPanel2 = new JPanel();
		mancalaPanel2.setLayout(new BorderLayout());
		mancalaPanel2.add(new JLabel(mancala), BorderLayout.CENTER);
		
		JPanel mancalaPanel1 = new JPanel();
		mancalaPanel1.setLayout(new BorderLayout());
		mancalaPanel1.add(new JLabel(mancala), BorderLayout.CENTER);
		//mancalaPanel2.setLayout(new BorderLayout());
		//mancalaPanel2.add(new MancalaComponent(), BorderLayout.CENTER);
		
		JPanel pitsPanel = new JPanel();
		pitsPanel.setLayout(new GridLayout(2, 6));
	      
	      for(int i = 0; i < 12; i++)
		    {
	    	   	//PitComponent pit = new PitComponent();
	    	  	JLabel pitLabel = new JLabel(pit);
	    	  	//give each pitLabal a mouseclick listener
		        pitsPanel.add(pitLabel);
		    }
		
	      JLabel boardEdge = new JLabel(border);
	     setLayout(new BorderLayout());
	     add(boardEdge);
	     add(mancalaPanel1, BorderLayout.EAST);
	     add(mancalaPanel2, BorderLayout.WEST);
	     add(pitsPanel, BorderLayout.CENTER);
		/*setLayout(new BorderLayout());
		PitsPanel pitsPanel = new PitsPanel();
		MancalaPanel mancala1 = new MancalaPanel();
		MancalaPanel mancala2 = new MancalaPanel();
		
		
		add(pitsPanel,
		         BorderLayout.CENTER);
		add(mancala1, BorderLayout.EAST);
		add(mancala2, BorderLayout.WEST);*/
	}
	
/* Has
	two MancalaPanels
	PitsPanel
*/
}
