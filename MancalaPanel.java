package project;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MancalaPanel extends JPanel {
	public MancalaPanel()
	{
		setLayout(new BorderLayout());
		MancalaComponent mancalaComponent = new MancalaComponent();
		add(mancalaComponent, BorderLayout.CENTER);
	}

}
