package project;

import javax.swing.event.ChangeListener;

/* Responsibilities
	Stores game color
	Shape of the pits
	Tells listeners that visual theme was changed
*/
class VisualTheme {
	void attachListener(ChangeListener listener) {
	}
	// The shape and color can be changed from "int" to something else!
	void setPitShape(int shape) {
		// emit change event
	}
	void setGameColor(int color) {
		// emit change event
	}
	int getPitShape() {
		return 0;
	}
	int getGameColor() {
		return 0;
	}
/* Has
	list of ChangeListeners
*/
}
