package project;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author kareem_darkazanli
* @version 1
*/

/**
* The StatusPanel is responsible for updating the
* user about the status of the game.
*/
public class StatusPanel extends JPanel implements ChangeListener{
	
	Game game;
	StoneSelectionPanel stoneSelectionPanel;
	ThemeSelectionPanel themeSelectionPanel;
	JLabel statusLabel;
	
	/**
	 * Constructor for the StoneSelectionPanel
	 * @param g  the model object holding the game's data.
	 */
	public StatusPanel(Game g)
	{
		game = g;
		stoneSelectionPanel = new StoneSelectionPanel(game);
		themeSelectionPanel = new ThemeSelectionPanel(game);
		statusLabel = new JLabel();
		setLayout(new BorderLayout());
		add(stoneSelectionPanel, BorderLayout.CENTER);
	
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(game.getNumberOfStones() == 0)
		{
			return;
		}
		else if(game.getNumberOfStones() > 0 && !game.isThemeSelected())
		{	
			remove(stoneSelectionPanel);
			add(themeSelectionPanel, BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if(game.isThemeSelected() && !game.isGameStarted())
		{
			remove(themeSelectionPanel);
			statusLabel = new JLabel(game.getMessageForPlayers());
			add(statusLabel, BorderLayout.CENTER);
			statusLabel.setText(game.getMessageForPlayers());
			
			revalidate();
			repaint();
		}
		else
		{
			remove(statusLabel);
			statusLabel = new JLabel(game.getMessageForPlayers());
			add(statusLabel, BorderLayout.CENTER);
			statusLabel.setText(game.getMessageForPlayers());
			revalidate();
			repaint();
		}
	
		
	}
}
