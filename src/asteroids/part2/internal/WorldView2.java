package asteroids.part2.internal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.JPanel;
import javax.swing.Timer;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;
import asteroids.util.internal.InternalUtils;
import asteroids.util.internal.ResourceUtils;

@SuppressWarnings("serial")
public class WorldView2<F extends IFacade> extends JPanel implements KeyListener, ActionListener, CollisionListener {

	private static final int TIMER_DELAY = 1000 / 30;

	private AsteroidsFrame2<F> game;
	private F facade;
	private World world;
	private Ship player;
	private double deltaAngle = Double.NaN;
	private boolean thrusterChange = false;
	private boolean fire;
	private Timer timer;
	private long timeAfterLastEvolve;
	private Image background;
	private String msg = null;
	private Map<Object, Visualization<F, ?>> visualizations = new HashMap<>();
	private Set<Explosion> explosions = new HashSet<>();

	private boolean showCollisions = false;

	public WorldView2(AsteroidsFrame2<F> game, World world, Ship player, Set<Ship> enemies) throws ModelException {
		this.game = game;
		this.facade = game.getFacade();
		this.world = world;
		this.player = player;
		this.timer = new Timer(TIMER_DELAY, this);
		setBackground(Color.BLACK);
		background = ResourceUtils.loadImage("asteroids/resources/game-background.jpg")
				.getScaledInstance(game.getWidth(), game.getHeight(), Image.SCALE_DEFAULT);
		if (player != null) {
			visualizations.put(player, createPlayerVisualization(player));
		}
		for (Ship enemy : enemies) {
			if (enemy != null)
				visualizations.put(enemy, createEnemyVisualization(enemy));
		}
		addKeyListener(this);
	}

	public Ship getPlayer() {
		return player;
	}

	protected Visualization<F, Ship> createEnemyVisualization(Ship enemy) {
		int size = 1;
		try {
			size = (int) (2 * facade.getShipRadius(enemy));
		} catch (ModelException e) {
			handleError(e);
		}
		Image image = ResourceUtils.loadImage("asteroids/resources/sphere.png").getScaledInstance(size, size,
				Image.SCALE_DEFAULT);
		return new ShipVisualization<>(Color.GREEN, enemy, image);
	}

	public boolean getShowCollisions() {
		return showCollisions;
	}

	protected Visualization<F, Ship> createPlayerVisualization(Ship player) {
		int size = 1;
		try {
			size = (int) (2 * facade.getShipRadius(player));
		} catch (ModelException e) {
			handleError(e);
		}
		Image image = ResourceUtils.loadImage("asteroids/resources/sphere.png").getScaledInstance(size, size,
				Image.SCALE_DEFAULT);
		assert image != null : "No player image!";
		return new CompositeVisualization<>(player, //
				new ShipVisualization<>(Color.WHITE, player, image),
				createCollisionVisualization(player, () -> getShowCollisions()));
	}

	protected Visualization<F, Ship> createCollisionVisualization(Ship object, Supplier<Boolean> showCollisions) {
		return new CollisionVisualization<>(object, showCollisions);
	}

	@Override
	public boolean isFocusable() {
		return true;
	}

	private void drawCenteredString(Graphics2D g2d, String txt, int y) {
		int width = getWidth();
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(txt, g2d);
		g2d.drawString(txt, width / 2 - (int) bounds.getCenterX(), y);
	}

	private void drawCenteredString(Graphics2D g2d, String txt) {
		int width = getWidth();
		int height = getHeight();
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(txt, g2d);
		g2d.drawString(txt, width / 2 - (int) bounds.getCenterX(), height / 2 - (int) bounds.getCenterY());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(background, 0, 0, null);
		g2d.setColor(Color.WHITE);
		DrawContext<F> ctx = createDrawContext(g2d);
		drawObjects(ctx);
		drawMessage(g2d);
	}

	private DrawContext<F> createDrawContext(Graphics2D g2d) {
		return new DrawContext<F>() {

			@Override
			public Graphics2D getGraphics() {
				return g2d;
			}

			@Override
			public double worldToScreenY(double y) {
				return getHeight() - y;
			}

			@Override
			public double worldToScreenX(double x) {
				return x;
			}

			@Override
			public F getFacade() {
				return facade;
			}

			@Override
			public void handleError(ModelException e) {
				WorldView2.this.handleError(e);
			}
		};
	}

	private void drawMessage(Graphics2D g2d) {
		if (msg != null) {
			g2d.setColor(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(40f));
			drawCenteredString(g2d, msg);
			g2d.setFont(g2d.getFont().deriveFont(20f));
			drawCenteredString(g2d, "Press ESC to go quit the game", getHeight() / 3 * 2);
			drawCenteredString(g2d, "Press ENTER to go back to the homescreen", getHeight() / 3 * 2 + 36);
		}
	}

	protected void drawObjects(DrawContext<F> ctx) {
		drawShips(ctx);
		drawBullets(ctx);
		for (Explosion explosion : explosions) {
			explosion.draw(ctx);
		}
	}

	protected void drawBullets(DrawContext<F> ctx) {
		Set<? extends Bullet> bullets;
		try {
			bullets = facade.getWorldBullets(world);
		} catch (ModelException e) {
			handleError(e);
			return;
		}
		for (Bullet bullet : bullets) {
			getOrCreateVisualization(bullet, this::createBulletVisualization).draw(ctx);
		}
	}

	protected Visualization<F, Bullet> createBulletVisualization(Bullet bullet) {
		Ship ship = null;
		try {
			ship = facade.getBulletSource(bullet);
		} catch (ModelException e) {
			handleError(e);
		}
		Color bulletColor = visualizations.get(ship) == null ? Color.RED : visualizations.get(ship).getColor();
		return new BulletVisualization<>(bulletColor, bullet);
	}

	protected void drawShips(DrawContext<F> ctx) {
		Set<? extends Ship> ships;
		try {
			ships = facade.getWorldShips(world);
		} catch (ModelException e) {
			handleError(e);
			return;
		}

		for (Ship ship : ships) {
			getOrCreateVisualization(ship, this::createEnemyVisualization).draw(ctx);
		}
	}

	protected void handleError(ModelException e) {
		game.handleError(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.out.println("exiting...");
			timer.stop();
			System.exit(0);
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			deltaAngle = Math.PI / 12.0;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			deltaAngle = -Math.PI / 12.0;
			break;
		case KeyEvent.VK_C:
			showCollisions = !showCollisions;
			break;
		case KeyEvent.VK_SPACE:
			fire = true;
			break;
		case KeyEvent.VK_ENTER:
			game.showMenu();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			deltaAngle = Double.NaN;
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			thrusterChange = true;
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long now = System.currentTimeMillis();
		long millisSinceLastEvolve = now - timeAfterLastEvolve;
		timeAfterLastEvolve = now;

		doActions();

		evolveWorld(millisSinceLastEvolve);

		updateExplosions(millisSinceLastEvolve);

		checkGameOver();
		repaint();
	}

	private void doActions() {
		doTurn();
		doThrust();
		doFire();
	}

	protected void doFire() {
		try {
			if (fire && isPlayerActive(player)) {
				facade.fireBullet(player);
				doFireEnemy();
				game.getSound().play("torpedo");
			}
		} catch (ModelException exc) {
			handleError(exc);
		} finally {
			fire = false;
		}
	}

	protected void doFireEnemy() throws ModelException {
		for (Ship enemy : facade.getWorldShips(world))
			if ((enemy != player) && (Math.random() > 0.75))
				facade.fireBullet(enemy);
	}

	protected boolean isPlayerActive(Ship ship) {
		try {
			Set<? extends Ship> ships = facade.getWorldShips(world);
			return ships != null && ships.contains(ship);
		} catch (ModelException e) {
			handleError(e);
		}
		return false;
	}

	private void doThrust() {
		if (thrusterChange) {
			try {
				boolean thrusterOn = !facade.isShipThrusterActive(player);
				facade.setThrusterActive(player, thrusterOn);
			} catch (ModelException exc) {
				handleError(exc);
			} finally {
				thrusterChange = false;
			}
		}
	}

	private void doTurn() {
		double deltaAngle = this.deltaAngle;
		if (!Double.isNaN(deltaAngle)) {
			try {
				if (deltaAngle != 0) {
					facade.turn(player,
							InternalUtils.toProperAngleDelta(facade.getShipOrientation(player), deltaAngle));
				}
			} catch (ModelException exc) {
				handleError(exc);
			}
		}
	}

	private void evolveWorld(long millisSinceLastEvolve) {
		try {
			millisSinceLastEvolve = Math.max(5, millisSinceLastEvolve);
			facade.evolve(world, millisSinceLastEvolve / 1000., this);
		} catch (ModelException exc) {
			handleError(exc);
		}
	}

	private void updateExplosions(long millisSinceLastEvolve) {
		Iterator<Explosion> iter = explosions.iterator();
		while (iter.hasNext()) {
			boolean done = iter.next().evolve(millisSinceLastEvolve / 1000.);
			if (done)
				iter.remove();
		}
	}

	protected void checkGameOver() {
		try {
			if (isPlayerActive(player)) {
				if (facade.getWorldShips(world).size() == 1) {
					gameOver("You win!");
				}
			} else {
				gameOver("You lose!");
			}
		} catch (ModelException exc) {
			handleError(exc);
		}
	}

	protected void gameOver(String string) {
		timer.stop();
		msg = string;
	}

	public void startGame() {
		game.getSound().loop("game-theme");
		timeAfterLastEvolve = System.currentTimeMillis();
		timer.start();
	}

	@Override
	public void boundaryCollision(Object entity, double x, double y) {

	}

	@Override
	public void objectCollision(Object entity1, Object entity2, double x, double y) {
		try {
			if ((entity1 instanceof Bullet && !(entity2 instanceof Bullet))
					|| (entity2 instanceof Bullet && !(entity1 instanceof Bullet))) {
				game.getSound().play("explosion");
				explosions.add(new Explosion(x, facade.getWorldSize(world)[1] - y));
			}
		} catch (ModelException exc) {
			handleError(exc);
		}
	}

	protected AsteroidsFrame2<F> getGame() {
		return game;
	}

	protected World getWorld() {
		return world;
	}

	protected F getFacade() {
		return facade;
	}

	@SuppressWarnings("unchecked")
	protected <T> Visualization<F, T> getOrCreateVisualization(T object, Function<T, Visualization<F, T>> creator) {
		if (!visualizations.containsKey(object)) {
			visualizations.put(object, creator.apply(object));
		}
		return (Visualization<F, T>) visualizations.get(object);
	}
}
