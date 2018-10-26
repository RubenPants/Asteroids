package asteroids.part3.internal;

import java.awt.Image;
import java.util.HashSet;
import java.util.function.Supplier;

import asteroids.model.Asteroid;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.internal.DrawContext;
import asteroids.part2.internal.Visualization;
import asteroids.part2.internal.WorldView2;
import asteroids.part3.facade.IFacade;
import asteroids.util.ModelException;
import asteroids.util.internal.ResourceUtils;

@SuppressWarnings("serial")
public class WorldView3 extends WorldView2<IFacade> {

	public WorldView3(AsteroidsFrame3 game, World world, Ship player) throws ModelException {
		super(game, world, player, new HashSet<Ship>());
	}

	@Override
	public AsteroidsFrame3 getGame() {
		return (AsteroidsFrame3) super.getGame();
	}

	@Override
	protected void doFireEnemy() throws ModelException {
		// they should use programs now
	}

	@Override
	protected void drawObjects(DrawContext<IFacade> ctx) {
		super.drawObjects(ctx);
		drawAsteroids(ctx);
		drawPlanetoids(ctx);
	}

	@Override
	protected void checkGameOver() {
		try {
			if (isPlayerActive(getPlayer())) {
				if (getFacade().getWorldShips(getWorld()).size() == 1
						&& getFacade().getWorldAsteroids(getWorld()).isEmpty()) {
					gameOver("You win!");
				}
			} else {
				gameOver("You lose!");
			}
		} catch (ModelException exc) {
			handleError(exc);
		}
	}

	@Override
	protected Visualization<IFacade, Ship> createCollisionVisualization(Ship object, Supplier<Boolean> showCollisions) {
		return new CollisionVisualization3(object, showCollisions);
	}

	protected void drawAsteroids(DrawContext<IFacade> ctx) {
		try {
			for (Asteroid asteroid : getFacade().getWorldAsteroids(getWorld())) {
				Visualization<IFacade, Asteroid> vis = getOrCreateVisualization(asteroid,
						this::createAsteroidVisualization);
				vis.draw(ctx);
			}
		} catch (ModelException e) {
			handleError(e);
		}
	}

	protected AsteroidVisualization createAsteroidVisualization(Asteroid asteroid) {
		int size = 1;
		try {
			size = (int) (2 * getFacade().getAsteroidRadius(asteroid));
		} catch (ModelException e) {
			handleError(e);
		}
		Image image = ResourceUtils.loadImage("asteroids/resources/asteroid1.png").getScaledInstance(size, size,
				Image.SCALE_DEFAULT);
		return new AsteroidVisualization(asteroid, image);
	}

	protected void drawPlanetoids(DrawContext<IFacade> ctx) {
		try {
			for (Planetoid planetoid : getFacade().getWorldPlanetoids(getWorld())) {
				Visualization<IFacade, Planetoid> vis = getOrCreateVisualization(planetoid,
						this::createPlanetoidVisualization);
				vis.draw(ctx);
			}
		} catch (ModelException e) {
			handleError(e);
		}
	}

	protected PlanetoidVisualization createPlanetoidVisualization(Planetoid planetoid) {
		int size = 1;
		try {
			size = (int) (2 * getFacade().getPlanetoidRadius(planetoid));
		} catch (ModelException e) {
			handleError(e);
		}
		Image image = ResourceUtils.loadImage("asteroids/resources/deathstar.png").getScaledInstance(size, size,
				Image.SCALE_DEFAULT);
		return new PlanetoidVisualization(planetoid, image);
	}

}
