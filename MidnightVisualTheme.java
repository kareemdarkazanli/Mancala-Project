import java.awt.*;
import java.awt.geom.*;

/**
* CS 151 Mancala Project solution for MancalaTest
* @author Paul Merrill
* @version 1
*/

/**
* The MidnightVisualTheme class is responsible for setting the
* game's theme to have a purple color and rectangular shape.
*/
public class MidnightVisualTheme implements VisualTheme {
	
	/**
	 * @param width  the width of the pit
	 * @param height the height of the pit
	 * @return  returns a rectangle sized using the width and height parameters.
	 */
	@Override
	public Shape getPitShape(int width, int height) {
		return new Rectangle2D.Double(0, 0, width, height);
	}

	/**
	 * @return  returns the color blue
	 */
	@Override
	public Color getPitColor() {
		return Color.BLUE;
	}

	/**
	 * @param x  the x coordinate of the stone
	 * @param y  the y coordinate of the stone
	 * @return  returns a rectangle with the given coordinates
	 */
	@Override
	public Shape getStoneShape(int x, int y) {
		return new Rectangle2D.Double(x, y, 5, 5);
	}

	/**
	 * @returns  returns the color blue
	 */
	@Override
	public Color getStoneColor() {
		return Color.BLUE;
	}
}
