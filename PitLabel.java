package project;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/* Responsibilities
	Knows which pit it is (e.g. 10th pit)
	Fetches number of stones from Game during paint
	Draws pit circle (black; shape from VisualTheme)
	Draws stones (color from VisualTheme)
	Tells Game that a move was made on click
*/
public class PitLabel extends JLabel {

	private static int ICON_WIDTH = 40;
	private static int ICON_HEIGHT = 60;

	private Game game;
	private Game.Pit pit;
	private VisualTheme theme;

	public PitLabel(Game game, Game.Pit pit)
	{
		this.game = game;
		this.pit = pit;

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				game.performMove(pit);
			}
		});

		Icon icon = new Icon() {
			public int getIconWidth() { return ICON_WIDTH; }
			public int getIconHeight() { return ICON_HEIGHT; }

			public void paintIcon(Component c, Graphics g, int x, int y)
			{
				if (theme == null)
					return;

				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(theme.getPitColor());
				g2.draw(theme.getPitShape(ICON_WIDTH, ICON_HEIGHT));

				int stones = game.getNumberOfStones(pit);

				// TODO: Draw stones.
			}
		};
		setIcon(icon);
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
