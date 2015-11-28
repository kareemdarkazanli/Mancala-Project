package project;

/* Responsibilities
	Creates Game
	Creates GameFrame
*/
public class MancalaTest {
	public static void main(String[] args) {
		Game game = new Game();
		GameFrame gameFrame = new GameFrame(game);
		game.attachListener(gameFrame);
	}
}
