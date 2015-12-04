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
* The ThemeSelectionPanel is responsible for drawing
* two buttons that allow the user to choose the game's theme.
*/
public class ThemeSelectionPanel extends JPanel {
	
	Game game;
	
	/**
	 * Constructor for the ThemeSelectionPanel
	 * @param g  the model object holding the game's data.
	 */
	public ThemeSelectionPanel(Game g)
	{
		game = g;
		setLayout(new BorderLayout());
		JButton buttonCitrus = new JButton("Citrus Theme");
		buttonCitrus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setTheme(new CitrusVisualTheme());
				
			}
			
		});
		
		JButton buttonMidnight = new JButton("Midnight Theme");
		buttonMidnight.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setTheme(new MidnightVisualTheme());;
				
			}
			
		});
		
		add(new JLabel("Select theme: "), BorderLayout.WEST);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(buttonCitrus, BorderLayout.WEST);
		buttonsPanel.add(buttonMidnight, BorderLayout.EAST);
		add(buttonsPanel, BorderLayout.CENTER);
	
	}

}
