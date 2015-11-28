package project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/* Responsibilities
	Tells Game how many stones to start with
*/
public class StoneSelectionPanel extends JPanel {
	private ArrayList<ActionListener> listeners = new ArrayList<>();

	public StoneSelectionPanel(Game game) {
		setLayout(new BorderLayout());

		JButton buttonThree = new JButton("3");
		JButton buttonFour = new JButton("4");

		buttonThree.addActionListener(e -> { game.setNumberOfStartingStones(3); emit(); });
		buttonFour.addActionListener(e -> { game.setNumberOfStartingStones(4); emit(); });

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(buttonThree, BorderLayout.WEST);
		buttonsPanel.add(buttonFour, BorderLayout.EAST);

		add(new JLabel("Select number of starting pebbles: "), BorderLayout.WEST);
		add(buttonsPanel, BorderLayout.CENTER);
	}

	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	private void emit() {
		for (ActionListener listener : listeners) {
			listener.actionPerformed(new ActionEvent(this, 0, ""));
		}
	}

/* Has
	Game
	JButtons for 3 and 4 stones to start with
	ActionListener for buttons (anonymous class) (controller)
		Verbs: actionPerformed
*/
}
