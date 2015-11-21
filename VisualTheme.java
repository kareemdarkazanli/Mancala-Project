package project;

import java.awt.*;

interface VisualTheme {
	public Shape getPitShape(int width, int height);
	public Color getPitColor();

	public Shape getStoneShape();
	public Color getStoneColor();
}
