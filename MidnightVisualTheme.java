package project;

import java.awt.*;
import java.awt.geom.*;

class MidnightVisualTheme implements VisualTheme {
	public Shape getPitShape(int width, int height) {
		return new Rectangle2D.Double(0, 0, width, height);
	}

	public Color getPitColor() {
		return Color.BLUE;
	}

	public Shape getStoneShape(int x, int y) {
		return new Rectangle2D.Double(x, y, 5, 5);
	}

	public Color getStoneColor() {
		return Color.BLUE;
	}
}
