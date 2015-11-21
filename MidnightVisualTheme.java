package project;

import java.awt.*;
import java.awt.geom.*;

class MidnightVisualTheme implements VisualTheme {
	public Shape getPitShape() {
		return new Rectangle2D.Double(0, 0, 5, 5);
	}

	public Color getGameColor() {
		return Color.BLUE;
	}
}
