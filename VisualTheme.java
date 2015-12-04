package project;

import java.awt.*;

/**
 * CS 151 Mancala Project solution for MancalaTest
 * @author Paul Merrill
 * @version 1
 */

/**
 * The VisualTheme interface is responsible for having methods
 * that get the game's pit and stone shapes, as well as their colors.
 */
interface VisualTheme {
	
	/**
	 * @param width  the width of the pit
	 * @param height the height of the pit
	 * @return  returns a shape sized using the width and height parameters.
	 */
	public Shape getPitShape(int width, int height);
	
	/**
	 * @return  returns the color of the pit
	 */
	public Color getPitColor();

	/**
	 * @param x  the x coordinate of the stone
	 * @param y  the y coordinate of the stone
	 * @return  returns a shape with the given coordinates
	 */
	public Shape getStoneShape(int x, int y);
	
	/**
	 * @return  returns the color of the stones
	 */
	public Color getStoneColor();
}
