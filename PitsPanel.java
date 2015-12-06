import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.*;

import javax.swing.*;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author Paul Merrill
* @version 1
*/

/**
* The PitsPanel is responsible for drawing the pits
* onto the Mancala Board.
*/
public class PitsPanel extends JPanel {

	private List<PitLabel> labels = new ArrayList<>();

	/**
	 * Constructor for the PitsPanel
	 * @param game  the model object holding the game's data.
	 */
	public PitsPanel(Game game)
	{
		setLayout(new GridLayout(2, 6));

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

	}

	private void addLabel(Game game, Game.Pit pit) {
		PitLabel label = new PitLabel(game, pit);
		labels.add(label);
		add(label);
		
	}

	/**
	 * 
	 * @param theme  sets the size and color of the pit and its stones.
	 */
	public void setTheme(VisualTheme theme) {
		for (PitLabel label : labels)
			label.setTheme(theme);
	}

}
