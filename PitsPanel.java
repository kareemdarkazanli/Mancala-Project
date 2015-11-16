package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/* Responsibilities
	Holds PitComponents
*/
public class PitsPanel extends JPanel {

	public PitsPanel(Game game)
	{

		//final JFrame frame = new JFrame();
		setLayout(new GridLayout(2, 6));

		add(new PitLabel(game, Game.Pit.B6));
		add(new PitLabel(game, Game.Pit.B5));
		add(new PitLabel(game, Game.Pit.B4));
		add(new PitLabel(game, Game.Pit.B3));
		add(new PitLabel(game, Game.Pit.B2));
		add(new PitLabel(game, Game.Pit.B1));

		add(new PitLabel(game, Game.Pit.A1));
		add(new PitLabel(game, Game.Pit.A2));
		add(new PitLabel(game, Game.Pit.A3));
		add(new PitLabel(game, Game.Pit.A4));
		add(new PitLabel(game, Game.Pit.A5));
		add(new PitLabel(game, Game.Pit.A6));

		//pitPanel.setBounds(0, 0, 150, 50);	 
		//frame.add(pitPanel,
		//BorderLayout.CENTER);

		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		//frame.pack();
		//frame.setSize(WIDTH, HEIGHT);
		//frame.setVisible(true);
	}

	/* Has
	   VisualTheme
	   twelve PitComponents for each pit
	 */
}
