package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author Kareem Darkazanli
* @version 1
*/

/**
* The GameFrame class is responsible for drawing the game board
* onto a JFrame
*/
public class GameFrame extends JFrame implements ChangeListener{

	private GameWithLabels gameWithLabels;
	private Game game;

	/**
	 * Constructor for the GameFrame
	 * @param game  the model object holding the game's data.
	 */
	public GameFrame(Game g) {
		game = g;
	
		StatusPanel statusSelectionPanel = new StatusPanel(game);
		
		game.attachListener(statusSelectionPanel);
		
		add(statusSelectionPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(450, 360);

		setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(game.isThemeSelected() && !game.isGameStarted())
		{
			gameWithLabels = new GameWithLabels(game);
			gameWithLabels.setTheme(game.getTheme());
			add(gameWithLabels, BorderLayout.CENTER);	
			JButton undoButton = new JButton("UNDO");
			undoButton.addActionListener(event -> game.performUndo());
			add(undoButton, BorderLayout.NORTH);
			game.setIsGameStarted(true);
		}
		
		repaint();
	}

}
