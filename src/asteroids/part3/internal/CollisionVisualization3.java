package asteroids.part3.internal;

import java.util.function.Supplier;

import asteroids.model.Asteroid;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part2.internal.CollisionVisualization;
import asteroids.part3.facade.IFacade;
import asteroids.util.ModelException;

public class CollisionVisualization3 extends CollisionVisualization<IFacade> {

	public CollisionVisualization3(Ship ship, Supplier<Boolean> showCollisions) {
		super(ship, showCollisions);
	}

	@Override
	protected double[] getPosition(IFacade facade, Object object) throws ModelException {
		if (object instanceof Asteroid) {
			return facade.getAsteroidPosition((Asteroid) object);
		}
		if (object instanceof Planetoid) {
			return facade.getPlanetoidPosition((Planetoid) object);
		}
		return super.getPosition(facade, object);
	}

	@Override
	protected double[] getVelocity(IFacade facade, Object object) throws ModelException {
		if (object instanceof Asteroid) {
			return facade.getAsteroidVelocity((Asteroid) object);
		} else if (object instanceof Planetoid) {
			return facade.getPlanetoidVelocity((Planetoid) object);
		}
		return super.getVelocity(facade, object);
	}

	@Override
	protected double getRadius(IFacade facade, Object object) throws ModelException {
		if (object instanceof Asteroid) {
			return facade.getAsteroidRadius((Asteroid) object);
		} else if (object instanceof Planetoid) {
			return facade.getPlanetoidRadius((Planetoid) object);
		}
		return super.getRadius(facade, object);
	}
}
