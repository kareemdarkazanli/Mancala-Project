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
public class GameFrame extends JFrame implements ChangeListener{

	private GameWithLabels gameWithLabels;
	private Game game;

	public GameFrame(Game g) {
		game = g;
		//game.attachListener(event -> repaint());

		
		
		StatusPanel statusSelectionPanel = new StatusPanel(game);
		game.attachListener(statusSelectionPanel);
		
		add(statusSelectionPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		//pack();
		setSize(410, 245);
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
