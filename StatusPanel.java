package project;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* Responsibilities
	Shows one of StoneSelectionPanel, ThemeSelectionPanel,
		or JLabel at a time
	Sets game status text on Game stateChange
*/
public class StatusPanel extends JPanel implements ChangeListener{
	
	Game game;
	StoneSelectionPanel stoneSelectionPanel;
	ThemeSelectionPanel themeSelectionPanel;
	JLabel statusLabel;
	public StatusPanel(Game g)
	{
		game = g;
		stoneSelectionPanel = new StoneSelectionPanel(game);
		themeSelectionPanel = new ThemeSelectionPanel(game);
		statusLabel = new JLabel();
		
		//themeSelectionPanel = new ThemeSelectionPanel(game);
		setLayout(new BorderLayout());
		add(stoneSelectionPanel, BorderLayout.CENTER);
	
		//add(themeSelectionPanel, BorderLayout.CENTER);
		//game.attachListener(event -> remove(themeSelectionPanel));
		//JLabel statusLabel = new JLabel(game.getMessageForPlayers());
		//add(statusLabel, BorderLayout.CENTER);
		//game.attachListener(event -> statusLabel.setText(game.getMessageForPlayers()));
	}
/* Has
	StoneSelectionPanel
	ThemeSelectionPanel
	JLabel for displaying game messages
	ChangeListener updating game messages (anonymous class)
		Verbs: stateChanged
	Game
*/
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
