package asteroids.part2.facade;

import java.util.*;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

/**
 * Implement this interface to connect your code to the graphical user interface
 * (GUI).
 * 
 * <ul>
 * <li>For separating the code that you wrote from the code that was provided to
 * you, put <b>ALL your code in the <code>src</code> folder.</b> The code that
 * is provided to you stays in the <code>src-provided</code> folder. Only if you
 * modify the provided code, it's advised to move the modified code to the
 * <code>src</code> folder, so that your changes cannot be accidentally
 * overwritten by an updated version of the provided code.</li>
 * 
 * <li>You should at least <b>create the following classes</b> in the package
 * <code>asteroids.model</code>:
 * <ul>
 * <li>a class <code>Ship</code> for representing a space ship</li>
 * <li>a class <code>World</code> for representing a world</li>
 * <li>a class <code>Bullet</code> for representing a bullet</li>
 * </ul>
 * You may, of course, add additional classes as you see fit.
 * 
 * <li>You should <b>change your <code>Facade</code></b> from part 1 to
 * implement this interface.</li>
 * 
 * <li>The <b>header of that Facade class</b> should look as follows:<br>
 * <code>class Facade implements asteroids.part2.facade.IFacade { ... }</code><br>
 * Consult the <a href=
 * "http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">
 * Java tutorial</a> for more information on interfaces, if necessary.</li>
 *
 * <li>Your <code>Facade</code> class should offer a <b>default constructor</b>.
 * </li>
 * 
 * <li><b>Each non-deprecated method</b> defined in this {@link IFacade}
 * interface and the interface from part 1 (
 * {@link asteroids.part1.facade.IFacade}) must be implemented by your Facade
 * class.
 * 
 * <li>The methods {@link #createShip()},
 * {@link #createShip(double, double, double, double, double, double)},
 * {@link #move(Ship, double)} and {@link #thrust(Ship, double)} from part 1 are
 * <b>deprecated</b> and no longer need to be implemented.</li>
 * 
 * <li>Methods in this interface are <b>only allowed to throw exceptions of type
 * <code>asteroids.util.ModelException</code></b> (this class is provided). No
 * other exception types are allowed. This exception can only be thrown if (1)
 * calling a method of your <code>Ship</code> class with the given parameters
 * would violate a precondition, or (2) if the method of your <code>Ship</code>
 * class throws an exception (if so, wrap the exception in a
 * <code>ModelException</code>).</li>
 * 
 * <li><b>ModelException should not be used anywhere outside of your Facade
 * implementation.</b></li>
 * 
 * <li>Your Facade implementation should <b>only contain trivial code</b> (for
 * example, calling a method, combining multiple return values into an array,
 * creating @Value instances, catching exceptions and wrapping it in a
 * ModelException). All non-trivial code should be placed in the other classes
 * that you create.</li>
 * 
 * <li>The rules described above and the documentation described below for each
 * method apply only to the class implementing IFacade. <b>Your other classes
 * should follow the rules described in the assignment.</b></li>
 * 
 * <li><b>Do not modify the signatures</b> of the methods defined in this
 * interface.</li>
 * 
 * </ul>
 *
 */
public interface IFacade extends asteroids.part1.facade.IFacade {

	/**************
	 * SHIP: Basic methods
	 *************/

	/**
	 * This method is deprecated; you should not implement nor use it.
	 * 
	 * @deprecated Since part 2 of the project. The method description (unit
	 *             circle) was inconsistent with the constraints described in
	 *             the assignment (minimum radius).
	 */
	@Override
	@Deprecated
	default Ship createShip() throws ModelException {
		throw new ModelException("unsupported operation");
	}

	/**
	 * This method is deprecated; you should not implement nor use it.
	 * 
	 * @deprecated Since part 2 of the project. This behavior is now triggered
	 *             through the
	 *             {@link IFacade#createShip(double, double, double, double, double, double, double)}
	 *             method (which includes the mass as an additional parameter).
	 */
	@Override
	@Deprecated
	default Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double angle)
			throws ModelException {
		return createShip(x, y, xVelocity, yVelocity, radius, angle, 1);
	}

	/**
	 * Create a new non-null ship with the given position, velocity, radius,
	 * direction and mass.
	 * 
	 * The thruster of the new ship is initially inactive. The ship is not
	 * located in a world.
	 */
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException;

	/**
	 * Terminate <code>ship</code>.
	 */
	public void terminateShip(Ship ship) throws ModelException;

	/**
	 * Check whether <code>ship</code> is terminated.
	 */
	public boolean isTerminatedShip(Ship ship) throws ModelException;

	/**
	 * Return the total mass of <code>ship</code> (i.e., including bullets
	 * loaded onto the ship).
	 */
	public double getShipMass(Ship ship) throws ModelException;

	/**
	 * Return the world of <code>ship</code>.
	 */
	public World getShipWorld(Ship ship) throws ModelException;

	/**
	 * Return whether <code>ship</code>'s thruster is active.
	 */
	public boolean isShipThrusterActive(Ship ship) throws ModelException;

	/**
	 * Enables or disables <code>ship</code>'s thruster depending on the value
	 * of the parameter <code>active</code>.
	 */
	public void setThrusterActive(Ship ship, boolean active) throws ModelException;

	/**
	 * Return the acceleration of <code>ship</code>.
	 */
	public double getShipAcceleration(Ship ship) throws ModelException;

	/**
	 * This method is deprecated; you should not implement nor use it.
	 * 
	 * 
	 * @deprecated Since part 2 of the project. This behavior is now triggered
	 *             through the {@link #evolve(World, double, CollisionListener)}
	 *             method.
	 */
	@Override
	@Deprecated
	default void move(Ship ship, double dt) throws ModelException {
		// do nothing; this behaviour is now triggered by the evolve method.
	}

	/**************
	 * BULLET: Basic methods
	 *************/

	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 */
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException;

	/**
	 * Terminate <code>bullet</code>.
	 */
	public void terminateBullet(Bullet bullet) throws ModelException;

	/**
	 * Check whether <code>bullet</code> is terminated.
	 */
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException;

	/**
	 * Return the position of <code>ship</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getBulletPosition(Bullet bullet) throws ModelException;

	/**
	 * Return the velocity of <code>ship</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getBulletVelocity(Bullet bullet) throws ModelException;

	/**
	 * Return the radius of <code>bullet</code>.
	 */
	public double getBulletRadius(Bullet bullet) throws ModelException;

	/**
	 * Return the mass of <code>bullet</code>.
	 */
	public double getBulletMass(Bullet bullet) throws ModelException;

	/**
	 * Return the world in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned in a world, or
	 * if it is positioned on a ship.
	 */
	public World getBulletWorld(Bullet bullet) throws ModelException;

	/**
	 * Return the ship in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	public Ship getBulletShip(Bullet bullet) throws ModelException;

	/**
	 * Return the ship that fired <code>bullet</code>.
	 */
	public Ship getBulletSource(Bullet bullet) throws ModelException;

	/**************
	 * WORLD: Basic methods
	 *************/

	/**
	 * Create a new world with the given <code>width</code> and
	 * <code>height</code>.
	 */
	public World createWorld(double width, double height) throws ModelException;

	/**
	 * Terminate <code>world</code>.
	 */
	public void terminateWorld(World world) throws ModelException;

	/**
	 * Check whether <code>world</code> is terminated.
	 */
	public boolean isTerminatedWorld(World world) throws ModelException;

	/**
	 * Return the size of <code>world</code> as an array containing the width,
	 * followed by the height.
	 */
	public double[] getWorldSize(World world) throws ModelException;

	/**
	 * Return all ships located within <code>world</code>.
	 */
	public Set<? extends Ship> getWorldShips(World world) throws ModelException;

	/**
	 * Return all bullets located in <code>world</code>.
	 */
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException;

	/**
	 * Add <code>ship</code> to <code>world</code>.
	 */
	public void addShipToWorld(World world, Ship ship) throws ModelException;

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeShipFromWorld(World world, Ship ship) throws ModelException;

	/**
	 * Add <code>bullet</code> to <code>world</code>.
	 */
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException;

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException;

	/**************
	 * SHIP: Methods related to loaded bullets
	 *************/

	/**
	 * Return the set of all bullets loaded on <code>ship</code>.
	 * 
	 * For students working alone, this method may return null.
	 */
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException;

	/**
	 * Return the number of bullets loaded on <code>ship</code>.
	 */
	public int getNbBulletsOnShip(Ship ship) throws ModelException;

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 */
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException;

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 * 
	 * For students working alone, this method must not do anything.
	 */
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException;

	/**
	 * Remove <code>ship</code> from <code>ship</code>.
	 */
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException;

	/**
	 * <code>ship</code> fires a bullet.
	 */
	public void fireBullet(Ship ship) throws ModelException;

	/******************
	 * COLLISIONS
	 **************/

	/**
	 * Return the shortest time in which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double getTimeCollisionBoundary(Object object) throws ModelException;

	/**
	 * Return the first position at which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double[] getPositionCollisionBoundary(Object object) throws ModelException;

	/**
	 * Return the shortest time in which the first entity will collide with the
	 * second entity.
	 */
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException;

	/**
	 * Return the first position at which the first entity will collide with the
	 * second entity.
	 */
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException;

	/**
	 * Return the time that must pass before a boundary collision or an entity
	 * collision will take place in the given world. Positive Infinity is
	 * returned if no collision will occur.
	 */
	public double getTimeNextCollision(World world) throws ModelException;

	/**
	 * Return the position of the first boundary collision or entity collision
	 * that will take place in the given world. Null is returned if no collision
	 * will occur.
	 */
	public double[] getPositionNextCollision(World world) throws ModelException;

	/**
	 * Advance <code>world</code> by <code>dt<code> seconds. 
	 * 
	 * To enable explosions within the UI, notify <code>collisionListener</code>
	 * whenever an entity collides with a boundary or another entity during this
	 * method. <code>collisionListener</code> may be null. If
	 * <code>collisionListener</code> is <code>null</code>, do not call its
	 * notify methods.
	 */
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException;

	/**
	 * Return the entity at the given <code>position</code> in the given
	 * <code>world</code>.
	 */
	public Object getEntityAt(World world, double x, double y) throws ModelException;

	/**
	 * Return a set of all the entities in the given world.
	 */
	public Set<? extends Object> getEntities(World world) throws ModelException;

	/**
	 * This method is deprecated; you should not implement nor use it.
	 * 
	 * @deprecated Since part 2 of the project. This behavior is now triggered
	 *             through the combination of
	 *             {@link IFacade#setThrusterActive(Ship, boolean)} and
	 *             {@link IFacade#evolve(World, double, CollisionListener)}
	 *             methods.
	 */
	@Override
	@Deprecated
	default void thrust(Ship ship, double amount) throws ModelException {
		throw new UnsupportedOperationException("thrust is deprecated");
	}

}
