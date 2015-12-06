import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author Paul Merrill
* @version 1
*/

/**
* The PitLabel is responsible for drawing the number
* of stones within itself.
*/
public class PitLabel extends JComponent {
	private static int INITIAL_LABEL_WIDTH = 40;
	private static int INITIAL_LABEL_HEIGHT = 60;

	private Game game;
	private Game.Pit pit;
	private VisualTheme theme;

	/**
	 * Constructor for the PitsPanel
	 * @param game, pit  the model object holding the game's data and its id.
	 */
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

	/**
	 * @return returns a Dimension Object containing the pit's width and height
	 */
	public Dimension getPreferredSize() {
		return new Dimension(INITIAL_LABEL_WIDTH, INITIAL_LABEL_HEIGHT);
	}

	/**
	 * 
	 * @param theme  sets the size and color of the pit and its stones.
	 */
	public void setTheme(VisualTheme theme) {
		this.theme = theme;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (theme == null)
			return;

		Graphics2D g2 = (Graphics2D) g;
		int width = (int) getSize().getWidth();
		int height = (int) getSize().getHeight();

		paintPit(g2, width, height);
		paintStones(g2, width, height);
	}

	private void paintPit(Graphics2D g, int width, int height) {
		Color pitColor = theme.getPitColor();
		Shape pitShape = theme.getPitShape(width, height);

		g.setColor(pitColor);
		g.draw(pitShape);
	}

	private void paintStones(Graphics2D g, int width, int height) {
		Color stoneColor = theme.getStoneColor();
		Shape stoneShape = theme.getStoneShape(0, 0);

		int numStones = game.getNumberOfStones(pit);
		int numFullRows = numStones / 2;
		int numTotalRows = (numStones + 1) / 2;
		boolean isOdd = numStones % 2 == 1;

		int columnWidth = 20; 
		int rowHeight; 

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

}
