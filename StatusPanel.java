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
public class StatusPanel extends JPanel {
	private Game game;
	private StoneSelectionPanel stoneSelectionPanel;
	private ThemeSelectionPanel themeSelectionPanel;
	private JLabel statusLabel;

	public StatusPanel(GameFrame frame, Game game) {
		this.game = game;

		setLayout(new BorderLayout());

		ThemeSelectionPanel themePanel = new ThemeSelectionPanel(frame);
		themePanel.addActionListener(e -> themeClicked(themePanel));
		add(themePanel, BorderLayout.CENTER);
	}

	private void themeClicked(ThemeSelectionPanel themePanel) {
		remove(themePanel);

		StoneSelectionPanel stonePanel = new StoneSelectionPanel(game);
		stonePanel.addActionListener(e -> stoneClicked(stonePanel));
		add(stonePanel, BorderLayout.CENTER);

		revalidate();
		repaint();
	}

	private void stoneClicked(StoneSelectionPanel stonePanel) {
		remove(stonePanel);

		JLabel statusLabel = new JLabel(game.getMessageForPlayers());
		game.attachListener(e -> statusLabel.setText(game.getMessageForPlayers()));
		add(statusLabel, BorderLayout.CENTER);

		revalidate();
		repaint();
	}

/* Has
	StoneSelectionPanel
	ThemeSelectionPanel
	JLabel for displaying game messages
	ChangeListener updating game messages (anonymous class)
		Verbs: stateChanged
	Game
*/
}
