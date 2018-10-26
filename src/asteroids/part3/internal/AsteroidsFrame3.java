package asteroids.part3.internal;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.internal.AsteroidsFrame2;
import asteroids.part2.internal.FileSoundManager;
import asteroids.part2.internal.NullSound;
import asteroids.part2.internal.Sound;
import asteroids.part3.facade.IFacade;
import asteroids.util.ModelException;

@SuppressWarnings("serial")
public class AsteroidsFrame3 extends AsteroidsFrame2<IFacade> {

	public AsteroidsFrame3(IFacade facade, int width, int height, boolean undecorated, Sound sound) {
		super(facade, width, height, undecorated, sound);
		// throw new NullPointerException(); // There is no AI made to play with -->
		// Functionality is removed
	}

	@Override
	protected AsteroidsMenu3 createMenu() {
		return new AsteroidsMenu3(this);
	}

	@Override
	public void startGame() {
		// Set in a world with 1 ship for the player and a random number
		// of enemy ships.
		World world;
		Ship player;
		IFacade facade = getFacade();
		int width = getWidth();
		int height = getHeight();

		// Create the main ship (the ship for the player).
		try {
			world = facade.createWorld(width, height);
			player = facade.createShip(width / 2., height / 2., 1, 6, 40, 0, 3.9e17);
			facade.addShipToWorld(world, player);
		} catch (ModelException e) {
			handleError(e);
			return;
		}
		// Load 50 bullets onto the players ship.
		for (int i = 1; i < 50; i++) {
			try {
				Bullet bullet = facade.createBullet(width / 2.0, height / 2.0, 0, 0, Math.random() + 2);
				facade.loadBulletOnShip(player, bullet);
			} catch (ModelException e) {
				handleError(e);
			}
		}

		// Spawn a random number of asteroids.
		int nbAsteroids = (int) (Math.random() * 4 + 1);
		Set<Asteroid> asteroids = new HashSet<>();
		for (int j = 0; j < nbAsteroids; j++) {
			try {
				Asteroid asteroid = facade.createAsteroid(Math.random() * width, Math.random() * height, 25, 50,
						10 + Math.random() * 20);
				if (asteroid != null) {
					facade.addAsteroidToWorld(world, asteroid);
					asteroids.add(asteroid);
				}
			} catch (ModelException exc) {
				// so be it (daaaaaaamn gurl)
			}
		}

		// Spawn a random number of planetoids.
		int nbPlanetoids = (int) (Math.random() * 4 + 1);
		Set<Planetoid> planetoids = new HashSet<>();
		for (int j = 0; j < nbPlanetoids; j++) {
			try {
				Planetoid planetoid = facade.createPlanetoid(Math.random() * width, Math.random() * height, 25, 50,
						10.0 + Math.random() * 30, Math.random() * 1000);
				if (planetoid != null) {
					facade.addPlanetoidToWorld(world, planetoid);
					planetoids.add(planetoid);
				}
			} catch (ModelException exc) {
				// so be it
			}
		}

		// Spawn a random number of free bullets.
		int nbBullets = (int) (Math.random() * 3);
		for (int j = 0; j < nbBullets; j++) {
			try {
				Bullet bullet = facade.createBullet(Math.random() * width, Math.random() * height, 10, 12,
						Math.random() * 5 + 3);
				facade.addBulletToWorld(world, bullet);
			} catch (ModelException exc) {
				// so be it
			}
		}

		// Start the game.
		try {
			WorldView3 view = new WorldView3(this, world, player);
			switchContent(view);
			view.startGame();
		} catch (ModelException e) {
			handleError(e);
			return;
		}
	}

	public void startAIGame() {
		IFacade facade = getFacade();
		int width = getWidth();
		int height = getHeight();
		World world;
		Ship playerHuman;

		// Create the world.
		try {
			world = facade.createWorld(width, height);
			playerHuman = facade.createShip(width / 5 * 4, height / 2., 0, 0, 40, Math.PI, 3.9e17);
			facade.addShipToWorld(world, playerHuman);

			// Add bullets to the ship of the player.
			for (int i = 1; i < 50; i++) {
				try {
					Bullet bullet = facade.createBullet(width / 5 * 4, height / 2., 0, 0, Math.random() + 2);
					facade.loadBulletOnShip(playerHuman, bullet);
				} catch (ModelException e) {
					handleError(e);
				}
			}
		} catch (ModelException e) {
			handleError(e);
			return;
		}

		try {
			Asteroid asteroid1 = facade.createAsteroid(width / 2.5, height / 2.5, 25, 50, 75);
			facade.addAsteroidToWorld(world, asteroid1);
			Asteroid asteroid2 = facade.createAsteroid(600, 100, -30, -40, 40);
			facade.addAsteroidToWorld(world, asteroid2);
			Asteroid asteroid3 = facade.createAsteroid(990, 550, -20, -3, 25);
			facade.addAsteroidToWorld(world, asteroid3);
			Asteroid asteroid4 = facade.createAsteroid(40, height - 100, 10, -8, 15);
			facade.addAsteroidToWorld(world, asteroid4);

			Planetoid planetoid = facade.createPlanetoid(80, height - 150, 10, -8, 15, 100);
			facade.addPlanetoidToWorld(world, planetoid);
		} catch (ModelException e) {
			handleError(e);
		}
		try {
			WorldView3 view = new WorldView3(this, world, playerHuman);
			switchContent(view);
			view.startGame();
		} catch (ModelException e) {
			handleError(e);
		}
	}

	public static void run(IFacade facade, boolean tryFullscreen, boolean enableSound) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = env.getDefaultScreenDevice();
		AsteroidsFrame3 asteroids;
		Sound sound = enableSound ? new FileSoundManager("asteroids/resources/sounds.txt") : new NullSound();

		if (tryFullscreen && screen.isFullScreenSupported()) {
			Rectangle dimensions = screen.getDefaultConfiguration().getBounds();
			asteroids = new AsteroidsFrame3(facade, dimensions.width, dimensions.height, true, sound);
			screen.setFullScreenWindow(asteroids);
		} else {
			asteroids = new AsteroidsFrame3(facade, 1024, 768, false, sound);
		}
		asteroids.start();
	}

}
