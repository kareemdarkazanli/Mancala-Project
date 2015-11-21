package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

/* Responsibilities
	Listens for Game changes and issues repaint
	Repaints on Game stateChange
*/
public class GameFrame extends JFrame {

	private GameWithLabels gameWithLabels;

	public GameFrame(Game game) {
		game.attachListener(event -> repaint());

		gameWithLabels = new GameWithLabels(game);
		gameWithLabels.setTheme(new CitrusVisualTheme()); // Remove once the VisualThemeSelector is finished!
		add(gameWithLabels, BorderLayout.CENTER);

		JLabel statusLabel = new JLabel(game.getMessageForPlayers());
		game.attachListener(event -> statusLabel.setText(game.getMessageForPlayers()));
		add(statusLabel, BorderLayout.SOUTH);

		JButton undoButton = new JButton("UNDO");
		undoButton.addActionListener(event -> game.performUndo());
		add(undoButton, BorderLayout.NORTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		//pack();
		setSize(400, 235);
		setVisible(true);
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
