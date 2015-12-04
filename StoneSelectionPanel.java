package project;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author kareem_darkazanli
* @version 1
*/

/**
* The StoneSelectionPanel is responsible for drawing
* two buttons that allow the user to choose the starting number of stones in each pit.
*/
public class StoneSelectionPanel extends JPanel {
	
	Game game;
	/**
	 * Constructor for the StoneSelectionPanel
	 * @param g  the model object holding the game's data.
	 */
	public StoneSelectionPanel(Game g)
	{
		game = g;
		setLayout(new BorderLayout());
		JButton buttonThree = new JButton("3");
		buttonThree.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setNumberOfStartingStones(3);
				
			}
			
		});
		
		JButton buttonFour = new JButton("4");
	
		buttonFour.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setNumberOfStartingStones(4);
				
			}
			
		});
		add(new JLabel("Select number of starting pebbles: "), BorderLayout.WEST);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(buttonThree, BorderLayout.WEST);
		buttonsPanel.add(buttonFour, BorderLayout.EAST);
		add(buttonsPanel, BorderLayout.CENTER);
		
	}
}
