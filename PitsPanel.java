package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.*;

import javax.swing.*;

/* Responsibilities
	Holds PitComponents
*/
public class PitsPanel extends JPanel {

	private List<PitLabel> labels = new ArrayList<>();
	Game game;

	public PitsPanel(Game game)
	{

		this.game = game;
		//final JFrame frame = new JFrame();
		setLayout(new GridLayout(2, 6));

		/*JPanel b6 = new JPanel();
		b6.setLayout(new BorderLayout());
		((PitsPanel) b6).addLabel(game, Game.Pit.B6);
		addLabel();*/
		addLabel(game, Game.Pit.B6);
		addLabel(game, Game.Pit.B5);
		addLabel(game, Game.Pit.B4);
		addLabel(game, Game.Pit.B3);
		addLabel(game, Game.Pit.B2);
		addLabel(game, Game.Pit.B1);

		addLabel(game, Game.Pit.A1);
		addLabel(game, Game.Pit.A2);
		addLabel(game, Game.Pit.A3);
		addLabel(game, Game.Pit.A4);
		addLabel(game, Game.Pit.A5);
		addLabel(game, Game.Pit.A6);

		//pitPanel.setBounds(0, 0, 150, 50);	 
		//frame.add(pitPanel,
		//BorderLayout.CENTER);

		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		//frame.pack();
		//frame.setSize(WIDTH, HEIGHT);
		//frame.setVisible(true);
	}

	public void addLabel(Game game, Game.Pit pit) {
		PitLabel label = new PitLabel(game, pit);
		labels.add(label);
		add(new JLabel(pit.toString()));
		add(label);
		
	}

	public void setTheme(VisualTheme theme) {
		for (PitLabel label : labels)
			label.setTheme(theme);
	}

	/* Has
	   VisualTheme
	   twelve PitComponents for each pit
	 */
}
