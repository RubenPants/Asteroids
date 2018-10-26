package asteroids.part1.internal;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;
import asteroids.util.internal.InternalUtils;

@SuppressWarnings("serial")
public class AsteroidsFrame extends JFrame {
	private Timer timer;
	private long lastMove;
	private boolean thrust;
	private double angle;
	private int width;
	private int height;

	private static final double THRUST_PER_SECOND = 150;

	public AsteroidsFrame(final IFacade facade, int width, int height, boolean undecorated) {
		super("Asteroids");
		this.width = width;
		this.height = height;
		final Set<Ship> ships = initModel(facade);
		final AsteroidsView view = new AsteroidsView(ships, facade);
		if (!undecorated) {
			view.setPreferredSize(new Dimension(1024, 768));
		}
		timer = new Timer(1000 / 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long now = System.currentTimeMillis();
				long millisSinceLastMove = now - lastMove;
				lastMove = now;
				double dt = millisSinceLastMove / 1000.;
				if (thrust) {
					try {
						facade.thrust(view.getSelected(), THRUST_PER_SECOND * dt);
					} catch (ModelException e1) {
						handleError(e1);
					}
				}
				if (angle != 0) {
					Ship ship = view.getSelected();
					try {
						facade.turn(ship, InternalUtils.toProperAngleDelta(facade.getShipOrientation(ship), angle));
					} catch (ModelException e2) {
						handleError(e2);
					}
				}
				for (Ship ship : ships) {
					try {
						facade.move(ship, dt);
					} catch (ModelException e1) {
						handleError(e1);
					}
				}
				view.repaint();
			}
		});
		this.setFocusTraversalKeysEnabled(false);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_KP_UP:
					thrust = true;
					view.setThrust(true);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_KP_LEFT:
					angle = Math.PI / 20;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_KP_RIGHT:
					angle = -Math.PI / 20;
					break;
				case KeyEvent.VK_TAB:
					view.selectNext();
					break;
				case KeyEvent.VK_C:
					view.setShowCollisions(!view.getShowCollisions());
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_KP_UP:
					thrust = false;
					view.setThrust(false);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_KP_LEFT:
					angle = 0;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_KP_RIGHT:
					angle = 0;
					break;
				}
			}
		});
		this.setUndecorated(undecorated);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(view);
		this.pack();
	}

	public void handleError(ModelException e) {
		e.printStackTrace();
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	private Set<Ship> initModel(IFacade facade) {
		Set<Ship> ships = new HashSet<>();
		try {
			ships.add(facade.createShip(200, 400, 10, 0, 50, 0));
			ships.add(facade.createShip(700, 400, 0, 0, 50, Math.PI));
			ships.add(facade.createShip(450, 600, 0, 0, 75, 3 * Math.PI / 2));
		} catch (ModelException e) {
			handleError(e);
		}
		return ships;
	}

	public void start() {
		this.setFocusable(true);
		this.setAutoRequestFocus(true);
		this.setVisible(true);
		this.requestFocus();
		lastMove = System.currentTimeMillis();
		timer.start();
	}

	public static void run(IFacade facade, boolean tryFullscreen) {

		if (GraphicsEnvironment.isHeadless()) {
			System.out.println("no screen found");
			return;
		}

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = env.getDefaultScreenDevice();
		AsteroidsFrame asteroids;
		if (tryFullscreen && screen.isFullScreenSupported()) {
			Rectangle dimensions = screen.getDefaultConfiguration().getBounds();
			asteroids = new AsteroidsFrame(facade, dimensions.width, dimensions.height, true);
			screen.setFullScreenWindow(asteroids);
		} else {
			asteroids = new AsteroidsFrame(facade, 1024, 768, false);
		}
		asteroids.start();
	}

}
