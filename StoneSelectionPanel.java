package project;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/* Responsibilities
	Tells Game how many stones to start with
*/
public class StoneSelectionPanel extends JPanel {
	
	Game game;
	public StoneSelectionPanel(Game g)
	{
		game = g;
		setLayout(new BorderLayout());
		JButton buttonThree = new JButton("3");
		buttonThree.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setNumberOfStartingStones(3);
				
			}
			
		});
		
		JButton buttonFour = new JButton("4");
	
		buttonFour.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setNumberOfStartingStones(4);
				
			}
			
		});
		add(new JLabel("Select number of starting pebbles: "), BorderLayout.WEST);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(buttonThree, BorderLayout.WEST);
		buttonsPanel.add(buttonFour, BorderLayout.EAST);
		add(buttonsPanel, BorderLayout.CENTER);
		
	}
/* Has
	Game
	JButtons for 3 and 4 stones to start with
	ActionListener for buttons (anonymous class) (controller)
		Verbs: actionPerformed
*/
}
