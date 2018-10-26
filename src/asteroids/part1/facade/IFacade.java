package asteroids.part1.facade;

import asteroids.model.Ship;
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
 * </ul>
 * You may, of course, add additional classes as you see fit.
 * 
 * <li>You should <b>create a class <code>Facade</code></b> that implements this
 * interface. This class should be placed <b>in the package
 * <code>asteroids.facade</code></b>.</li>
 * 
 * <li>The <b>header of that Facade class</b> should look as follows:<br>
 * <code>class Facade implements IFacade { ... }</code><br>
 * Consult the <a href=
 * "http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">
 * Java tutorial</a> for more information on interfaces, if necessary.</li>
 *
 * <li>Your <code>Facade</code> class should offer a <b>default constructor</b>.
 * </li>
 * 
 * <li><b>Each method</b> defined in the interface <code>IFacade</code> must be
 * implemented by the class <code>Facade</code>. For example, the implementation
 * of {@link #getShipRadius(Ship) } should call one or more methods on the given
 * ship to retrieve its current radius.</li>
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
 * method apply only to the class implementing IFacade. <b>Your Ship class
 * should follow the rules described in the assignment.</b></li>
 * 
 * <li><b>Do not modify the signatures</b> of the methods defined in this
 * interface.</li>
 * 
 * </ul>
 *
 */
public interface IFacade {

	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on <code>(0, 0)</code> facing right. Its
	 * speed is zero.
	 */
	public Ship createShip() throws ModelException;

	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation (in radians).
	 */
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException;

	/**
	 * Return the position of <code>ship</code> as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 */
	public double[] getShipPosition(Ship ship) throws ModelException;

	/**
	 * Return the velocity of <code>ship</code> as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 */
	public double[] getShipVelocity(Ship ship) throws ModelException;

	/**
	 * Return the radius of <code>ship</code>.
	 */
	public double getShipRadius(Ship ship) throws ModelException;

	/**
	 * Return the orientation of <code>ship</code> (in radians).
	 */
	public double getShipOrientation(Ship ship) throws ModelException;

	/**
	 * Update <code>ship</code>'s position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 */
	public void move(Ship ship, double dt) throws ModelException;

	/**
	 * Update <code>ship</code>'s velocity based on its current velocity, its
	 * direction and the given <code>amount</code>.
	 */
	public void thrust(Ship ship, double amount) throws ModelException;

	/**
	 * Update the direction of <code>ship</code> by adding <code>angle</code>
	 * (in radians) to its current direction. <code>angle</code> may be
	 * negative.
	 */
	public void turn(Ship ship, double angle) throws ModelException;

	/**
	 * Return the distance between <code>ship1</code> and <code>ship2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 */
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException;

	/**
	 * Check whether <code>ship1</code> and <code>ship2</code> overlap. A ship
	 * always overlaps with itself.
	 */
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException;

	/**
	 * Return the number of seconds until the first collision between
	 * <code>ship1</code> and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 */
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException;

	/**
	 * Return the first position where <code>ship1</code> and <code>ship2</code>
	 * collide, or <code>null</code> if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 */
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException;
}
