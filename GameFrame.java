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
		
		
		
		JFrame frame = new JFrame();
		frame.add(new GameWithLabels(), BorderLayout.CENTER);
		
		JLabel statusLabel = new JLabel("The status goes here");
		frame.add(statusLabel, BorderLayout.SOUTH);
		
		JButton undoButton = new JButton("UNDO");
		frame.add(undoButton, BorderLayout.NORTH);
		      
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      	//frame.setResizable(false);
			    // frame.pack();
		frame.setSize(400, 235);
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
