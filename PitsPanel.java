package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/* Responsibilities
	Holds PitComponents
*/
public class PitsPanel extends JPanel {
	
	public PitsPanel()
	{
		 
		 //final JFrame frame = new JFrame();
	     setLayout(new GridLayout(2, 6));
	      
	      for(int i = 0; i < 12; i++)
		    {
	    	   	PitLabel pit = new PitLabel();
		        add(pit);
		    }
	      
	    
	      
	     // pitPanel.setBounds(0, 0, 150, 50);	 
	      //frame.add(pitPanel,
	         //BorderLayout.CENTER);
	      
	      
	      
	      	//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      	//frame.setResizable(false);
		    //frame.pack();
	      	//frame.setSize(WIDTH, HEIGHT);
		    //frame.setVisible(true);
	}

	  
	
	
	
	
	
	
/* Has
	VisualTheme
	twelve PitComponents for each pit
*/
}
