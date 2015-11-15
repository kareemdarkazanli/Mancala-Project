package project;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MancalaPanel extends JPanel {
	
	public MancalaPanel()
	{
		setLayout(new BorderLayout());
		MancalaLabel mancalaLabel = new MancalaLabel();
		add(mancalaLabel, BorderLayout.CENTER);
	}

}
