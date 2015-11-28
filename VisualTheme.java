package project;

import java.awt.*;

interface VisualTheme {
	public Shape getPitShape(int width, int height);
	public Color getPitColor();

	public Shape getStoneShape(int x, int y);
	public Color getStoneColor();
}
