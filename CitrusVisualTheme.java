package project;

import java.awt.*;
import java.awt.geom.*;

class CitrusVisualTheme implements VisualTheme {
	public Shape getPitShape(int width, int height) {
		return new Ellipse2D.Double(0, 0, width, height);
	}

	public Color getPitColor() {
		return Color.RED;
	}

	public Shape getStoneShape() {
		return new Ellipse2D.Double(0, 0, 6, 6);
	}

	public Color getStoneColor() {
		return Color.RED;
	}
}
