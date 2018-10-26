package asteroids.part2.internal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.*;

import javax.swing.JFrame;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

@SuppressWarnings("serial")
public class AsteroidsFrame2<F extends IFacade> extends JFrame {

	private AsteroidsMenu2 menu;
	private F facade;
	private int width;
	private int height;
	private Sound sound;

	public AsteroidsFrame2(F facade, int width, int height, boolean undecorated, Sound sound) {
		super("Asteroids");
		this.sound = sound;
		this.width = width;
		this.height = height;
		this.facade = facade;
		this.menu = createMenu();
		setUndecorated(undecorated);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add(menu);
		pack();
	}

	protected AsteroidsMenu2 createMenu() {
		return new AsteroidsMenu2(this);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public Sound getSound() {
		return sound;
	}

	public F getFacade() {
		return facade;
	}

	public void start() {
		menu.reset();
		sound.start();
		setVisible(true);
		menu.requestFocusInWindow();
	}

	public void startGame() {
		// Set op a world with 1 ship for the player and a random number
		// of enemy ships.
		World world;
		Ship player;
		try {
			world = facade.createWorld(width, height);
			player = facade.createShip(width / 2., height / 2., 1, 6, 40, 0, 3e17);
			facade.addShipToWorld(world, player);
		} catch (ModelException e) {
			handleError(e);
			return;
		}
		// Load 50 bullets onto the players ship.
		for (int i = 1; i < 50; i++) {
			try {
				Bullet bullet = facade.createBullet(width / 2.0, height / 2.0, 0, 0, Math.random() * 10 + 5);
				facade.loadBulletOnShip(player, bullet);
			} catch (ModelException e) {
				handleError(e);
			}
		}
		int nbEnemyShips = (int) (Math.random() * 10 + 1);
		Set<Ship> enemies = new HashSet<>();
		for (int j = 0; j < nbEnemyShips; j++) {
			try {
				Ship enemyShip = facade.createShip(Math.random() * width, Math.random() * height, 25, 50,
						10.0 + Math.random() * 40, Math.random() * 2 * Math.PI / 3, 1.0E22);
				facade.addShipToWorld(world, enemyShip);
				for (int i = 1; i < 10; i++) {
					Bullet bullet = facade.createBullet(facade.getShipPosition(enemyShip)[0],
							facade.getShipPosition(enemyShip)[1], 0, 0, Math.PI);
					facade.loadBulletOnShip(enemyShip, bullet);
				}
				enemies.add(enemyShip);
			} catch (ModelException exc) {
				// so be it
			}
		}
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
		try {
			WorldView2<F> view = new WorldView2<>(this, world, player, enemies);
			switchContent(view);
			view.startGame();
		} catch (ModelException e) {
			handleError(e);
			return;
		}
	}

	protected void switchContent(Component view) {
		getContentPane().removeAll();
		if (view != null) {
			if (!isUndecorated())
				view.setPreferredSize(new Dimension(width, height));
			getContentPane().add(view);
		}
		// //revalidate();
		validate();
		repaint();
		if (view != null) {
			view.requestFocusInWindow();
		}
	}

	public void handleError(ModelException e) {
		e.printStackTrace();
		sound.play("torpedo");
	}

	public void showMenu() {
		menu.reset();
		switchContent(menu);
	}

	public static void run(IFacade facade, boolean tryFullscreen, boolean enableSound) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = env.getDefaultScreenDevice();
		AsteroidsFrame2<IFacade> asteroids;
		Sound sound = enableSound ? new FileSoundManager("asteroids/resources/sounds.txt") : new NullSound();

		if (tryFullscreen && screen.isFullScreenSupported()) {
			Rectangle dimensions = screen.getDefaultConfiguration().getBounds();
			asteroids = new AsteroidsFrame2<>(facade, dimensions.width, dimensions.height, true, sound);
			screen.setFullScreenWindow(asteroids);
		} else {
			asteroids = new AsteroidsFrame2<>(facade, 1024, 768, false, sound);
		}
		asteroids.start();
	}
}
