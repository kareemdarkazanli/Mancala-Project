package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* Responsibilities
	Listens for Game changes and issues repaint
	Repaints on Game stateChange
*/
public class GameFrame extends JFrame {
	private Game game;
	private StatusPanel statusSelectionPanel;

	public GameFrame(Game game) {
		this.game = game;

		StatusPanel statusSelectionPanel = new StatusPanel(this, game);
		add(statusSelectionPanel, BorderLayout.SOUTH);

		JButton undoButton = new JButton("UNDO");
		undoButton.addActionListener(event -> game.performUndo());
		add(undoButton, BorderLayout.NORTH); 

		game.attachListener(e -> repaint());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 260);
		setVisible(true);
	}

	public void setTheme(VisualTheme theme) {
		GameWithLabels gameWithLabels = new GameWithLabels(game);
		gameWithLabels.setTheme(theme);
		add(gameWithLabels, BorderLayout.CENTER);
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
