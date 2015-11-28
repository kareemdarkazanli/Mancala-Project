package project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/* Responsibilities
	Tells VisualTheme what stone color and pit shape to use
*/
public class ThemeSelectionPanel extends JPanel {
	private ArrayList<ActionListener> listeners = new ArrayList<>();

	public ThemeSelectionPanel(GameFrame frame) {
		setLayout(new BorderLayout());

		JButton buttonCitrus = new JButton("Citrus Theme");
		JButton buttonMidnight = new JButton("Midnight Theme");

		buttonCitrus.addActionListener(e -> { frame.setTheme(new CitrusVisualTheme()); emit(); });
		buttonMidnight.addActionListener(e -> { frame.setTheme(new MidnightVisualTheme()); emit(); });

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(buttonCitrus, BorderLayout.WEST);
		buttonsPanel.add(buttonMidnight, BorderLayout.EAST);

		add(new JLabel("Select theme: "), BorderLayout.WEST);
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
	VisualTheme
	JButtons for two different themes
	ActionListener for buttons (anonymous class) (controller)
		Verbs: actionPerformed
*/
}
