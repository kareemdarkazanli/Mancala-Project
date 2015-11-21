package project;

import java.awt.*;
import java.awt.geom.*;

class CitrusVisualTheme implements VisualTheme {
	public Shape getPitShape() {
		return new Ellipse2D.Double(0, 0, 6, 6);
	}

	public Color getGameColor() {
		return Color.RED;
	}
}
