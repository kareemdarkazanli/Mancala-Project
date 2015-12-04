package project;

import java.awt.*;
import java.awt.geom.*;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author Paul Merrill
* @version 1
*/

/**
* The CitrusVisualTheme class is responsible for setting the
* game's theme to have a red color and ellipse shape.
*/
public class CitrusVisualTheme implements VisualTheme {
	/**
	 * @param width  the width of the pit
	 * @param height the height of the pit
	 * @return  returns an ellipse sized using the width and height parameters.
	 */
	public Shape getPitShape(int width, int height) {
		return new Ellipse2D.Double(0, 0, width, height);
	}

	/**
	 * @return  returns the color red
	 */
	public Color getPitColor() {
		return Color.RED;
	}

	/**
	 * @param x  the x coordinate of the stone
	 * @param y  the y coordinate of the stone
	 * @return  returns an ellipse with the given coordinates
	 */
	public Shape getStoneShape(int x, int y) {
		return new Ellipse2D.Double(x, y, 6, 6);
	}

	/**
	 * @return  returns the color red
	 */
	public Color getStoneColor() {
		return Color.RED;
	}

	
}
