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
public class PitLabel extends JComponent {
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
	}

	public Dimension getPreferredSize() {
		return new Dimension(INITIAL_LABEL_WIDTH, INITIAL_LABEL_HEIGHT);
	}

	public void setTheme(VisualTheme theme) {
		this.theme = theme;
	}

	public void paintComponent(Graphics g) {
		if (theme == null)
			return;

		Graphics2D g2 = (Graphics2D) g;
		int width = (int) getSize().getWidth();
		int height = (int) getSize().getHeight();

		paintPit(g2, width, height);
		paintStones(g2, width, height);
	}

	public void paintPit(Graphics2D g, int width, int height) {
		Color pitColor = theme.getPitColor();
		Shape pitShape = theme.getPitShape(width, height);

		g.setColor(pitColor);
		g.draw(pitShape);
	}

	public void paintStones(Graphics2D g, int width, int height) {
		// Draw the stones in two columns. If there are an odd number
		// of stones put the last one in the center of its column.

		Color stoneColor = theme.getStoneColor();
		Shape stoneShape = theme.getStoneShape(0, 0);

		int numStones = game.getNumberOfStones(pit);
		int numFullRows = numStones / 2;
		int numTotalRows = (numStones + 1) / 2;
		boolean isOdd = numStones % 2 == 1;

		int columnWidth = 20; // pixels between the two columns
		int rowHeight; // pixels between two consecutive rows

		if (numTotalRows > 7) {
			rowHeight = 8;
		} else if (numTotalRows > 5) {
			rowHeight = 10;
		} else if (numTotalRows > 3) {
			rowHeight = 15;
		} else {
			rowHeight = 20;
		}

		int stoneWidth = stoneShape.getBounds().width;
		int stoneHeight = stoneShape.getBounds().height;

		int firstColX = width / 2 - columnWidth / 2 - stoneWidth / 2;
		int firstRowY = height / 2 - (numTotalRows - 1) * rowHeight / 2;
		int oddColX = width / 2 - stoneWidth / 2;

		g.setColor(theme.getStoneColor());

		for (int i = 0; i < numFullRows; i++) {
			g.draw(theme.getStoneShape(firstColX,               firstRowY + rowHeight * i));
			g.draw(theme.getStoneShape(firstColX + columnWidth, firstRowY + rowHeight * i));
		}

		if (isOdd) {
			g.draw(theme.getStoneShape(oddColX, firstRowY + rowHeight * numFullRows));
		}
	}

/* Has
	VisualTheme
	Game
	MouseAdapter for itself (anonymous class) (controller)
		Verbs: mouseClicked
*/
}
