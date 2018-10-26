package asteroids.part3.internal;

import asteroids.part2.internal.AsteroidsMenu2;

@SuppressWarnings("serial")
public class AsteroidsMenu3 extends AsteroidsMenu2 {

	public AsteroidsMenu3(AsteroidsFrame3 game) {
		super(game);
	}

	@Override
	public AsteroidsFrame3 getGame() {
		return (AsteroidsFrame3) super.getGame();
	}

	@Override
	protected String[] createOptions() {
		return new String[] { "Player vs Asteroids - Version 1", "Player vs Asteroids - Version 2", "Exit" };
	}

	@Override
	protected void optionSelected(int selectedIndex) {
		switch (selectedIndex) {
		case 0:
			getGame().startGame();
			break;
		case 1:
			getGame().startAIGame();
			break;
		case 2:
			System.exit(0);
		}
	}
}
