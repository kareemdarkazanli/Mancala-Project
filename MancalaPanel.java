package project;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MancalaPanel extends JPanel {
	
	public MancalaPanel(Game game)
	{
		setLayout(new BorderLayout());
		MancalaLabel mancalaLabel = new MancalaLabel(game);
		add(mancalaLabel, BorderLayout.CENTER);
	}

}
