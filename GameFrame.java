package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

/* Responsibilities
	Listens for Game changes and issues repaint
	Repaints on Game stateChange
*/
public class GameFrame{
	
	public GameFrame()
	{
		
		
		/*JPanel mancalaPanel1 = new JPanel();
		mancalaPanel1.setLayout(new BorderLayout());
		mancalaPanel1.add(new MancalaComponent(), BorderLayout.CENTER);
		
		JPanel mancalaPanel2 = new JPanel();
		mancalaPanel2.setLayout(new BorderLayout());
		mancalaPanel2.add(new MancalaComponent(), BorderLayout.CENTER);
		
		JPanel pitsPanel = new JPanel();
		pitsPanel.setLayout(new GridLayout(2, 6));
	      
	      for(int i = 0; i < 12; i++)
		    {
	    	   	PitComponent pit = new PitComponent();
		        pitsPanel.add(pit);
		    }
		
	    JPanel gameBoard = new JPanel();
	    //gameBoard.setLayout(new BorderLayout());
	    gameBoard.add(mancalaPanel1, BorderLayout.EAST);
	    gameBoard.add(mancalaPanel2, BorderLayout.WEST);
	    gameBoard.add(pitsPanel, BorderLayout.CENTER);*/
		
		JFrame frame = new JFrame();
		frame.add(new GameBoard(), BorderLayout.CENTER); 	      
		      
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      	//frame.setResizable(false);
			    // frame.pack();
		frame.setSize(605, 200);
		frame.setVisible(true);
	}
/* Has
	GameWithLabels
	JButton for undo button
	ActionListener for undo button (anonymous class) (controller)
		Verbs: actionPerformed
	ChangeListener for repainting when Game updates (anonymous class)
		Verbs: stateChanged
	ChangeListener for when ThemeSelectionPanel is done (anonymous class)
		Verbs: stateChanged
	StatusPanel
	Game
*/
}
