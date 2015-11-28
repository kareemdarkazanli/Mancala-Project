package project;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

/* Responsibilities
	Knows which pit it is (e.g. 10th pit)
	Fetches number of stones from Game during paint
	Draws pit circle (black; shape from VisualTheme)
	Draws stones (color from VisualTheme)
	Tells Game that a move was made on click
*/
public class PitLabel extends JLabel {
	private static int INITIAL_LABEL_WIDTH = 40;
	private static int INITIAL_LABEL_HEIGHT = 60;

	private Game game;
	private Game.Pit pit;
	private VisualTheme theme;

	public PitLabel(Game game, Game.Pit pit) {
		PitLabel self = this;
		this.game = game;
		this.pit = pit;

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				game.performMove(pit);
			}
		});

		Icon icon = new Icon() {
			public int getIconWidth() { return self.getSize().width; }
			public int getIconHeight() { return self.getSize().height; }

			public void paintIcon(Component c, Graphics g, int x, int y)
			{
				if (theme == null)
					return;

				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(theme.getPitColor());
				g2.draw(theme.getPitShape(getIconWidth(), getIconHeight()));

				int stones = game.getNumberOfStones(pit);

				// TODO: Draw stones better.
				g2.drawString("" + stones, 20, 20);
			}
		};
		setIcon(icon);
	}

	public Dimension getPreferredSize() {
		return new Dimension(INITIAL_LABEL_WIDTH, INITIAL_LABEL_HEIGHT);
	}

	public void setTheme(VisualTheme theme) {
		this.theme = theme;
	}

	/*public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		Rectangle2D.Double edges = new Rectangle2D.Double();
		edges.height = 60;
		edges.width = 60;
		//edges.height = 60;
		//edges.width = 60;
		//pit.height = 50;
		//pit.width = 40;
		Ellipse2D.Double pit = new Ellipse2D.Double(edges.getHeight()/6, edges.getWidth()/10, 40,50);
	
		g2.setStroke(new BasicStroke(2));
		g2.draw(pit);
		g2.draw(edges);
		//g2.draw(edges);
	}*/

/* Has
	VisualTheme
	Game
	MouseAdapter for itself (anonymous class) (controller)
		Verbs: mouseClicked
*/
}
