package project;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/* Responsibilities
	Tells VisualTheme what stone color and pit shape to use
*/
public class ThemeSelectionPanel extends JPanel {
	
	Game game;
	public ThemeSelectionPanel(Game g)
	{
		game = g;
		setLayout(new BorderLayout());
		JButton buttonCitrus = new JButton("Citrus Theme");
		buttonCitrus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setTheme(new CitrusVisualTheme());
				
			}
			
		});
		
		JButton buttonMidnight = new JButton("Midnight Theme");
		buttonMidnight.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setTheme(new MidnightVisualTheme());;
				
			}
			
		});
		
		add(new JLabel("Select theme: "), BorderLayout.WEST);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(buttonCitrus, BorderLayout.WEST);
		buttonsPanel.add(buttonMidnight, BorderLayout.EAST);
		add(buttonsPanel, BorderLayout.CENTER);
		
		
	}
/* Has
	VisualTheme
	JButtons for two different themes
	ActionListener for buttons (anonymous class) (controller)
		Verbs: actionPerformed
*/
}
