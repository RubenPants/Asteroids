package asteroids.part3.facade;

import java.util.List;
import java.util.Set;

import asteroids.model.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.program.Program;
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
 * <li>a class <code>Asteroid</code> for representing an asteroid</li>
 * <li>a class <code>Planetoid</code> for representing a planetoid</li>
 * <li>a class <code>Program</code> for representing an executable program</li>
 * </ul>
 * You may, of course, add additional classes as you see fit.
 * 
 * <li>You should <b>change your <code>Facade</code></b> from part 2 to
 * implement this interface.</li>
 * 
 * <li>The <b>header of that Facade class</b> should look as follows:<br>
 * <code>class Facade implements asteroids.part3.facade.IFacade { ... }</code><br>
 * Consult the <a href=
 * "http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">
 * Java tutorial</a> for more information on interfaces, if necessary.</li>
 *
 * <li>Your <code>Facade</code> class should offer a <b>default constructor</b>.
 * </li>
 * 
 * <li><b>Each non-deprecated method</b> defined in this {@link IFacade}
 * interface and the interfaces from part 1 (
 * {@link asteroids.part1.facade.IFacade}) and part 2
 * ({@link asteroids.part2.facade.IFacade}) must be implemented by your Facade
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
 * calling the method with the given parameters would violate a precondition, or
 * (2) if the method causes an exception to be thrown in your code (if so, wrap
 * the exception in a <code>ModelException</code>).</li>
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
public interface IFacade extends asteroids.part2.facade.IFacade {

	/***********************
	 * ADMINISTRATIVE STUFF
	 ***********************/

	/**
	 * Return the number of students in your team (used to adapt the tests for
	 * single-student groups).
	 * 
	 * @return 1 or 2
	 */
	public int getNbStudentsInTeam();

	/**************
	 * WORLD: Asteroids and planetoids
	 *************/

	/**
	 * Return all asteroids located in <code>world</code>.
	 */
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException;

	/**
	 * Add <code>asteroid</code> to <code>world</code>.
	 */
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException;

	/**
	 * Remove <code>asteroid</code> from <code>world</code>.
	 */
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException;

	/**
	 * Return all planetoids located in <code>world</code>.
	 */
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException;

	/**
	 * Add <code>planetoid</code> to <code>world</code>.
	 */
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException;

	/**
	 * Remove <code>planetoid</code> from <code>world</code>.
	 */
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException;

	/**************
	 * ASTEROID: Basic methods
	 *************/

	/**
	 * Create a new non-null asteroid with the given position, velocity and
	 * radius.
	 * 
	 * The asteroid is not located in a world.
	 */
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException;

	/**
	 * Terminate <code>asteroid</code>.
	 */
	public void terminateAsteroid(Asteroid asteroid) throws ModelException;

	/**
	 * Check whether <code>asteroid</code> is terminated.
	 */
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException;

	/**
	 * Return the position of <code>asteroid</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException;

	/**
	 * Return the velocity of <code>asteroid</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException;

	/**
	 * Return the radius of <code>asteroid</code>.
	 */
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException;

	/**
	 * Return the mass of <code>asteroid</code>.
	 */
	public double getAsteroidMass(Asteroid asteroid) throws ModelException;

	/**
	 * Return the world in which <code>asteroid</code> is positioned.
	 */
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException;

	/**************
	 * PLANETOID: Basic methods
	 *************/

	/**
	 * Create a new non-null planetoid with the given position, velocity,
	 * radius, and total traveled distance.
	 * 
	 * The planetoid is not located in a world.
	 */
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException;

	/**
	 * Terminate <code>planetoid</code>.
	 */
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException;

	/**
	 * Check whether <code>planetoid</code> is terminated.
	 */
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException;

	/**
	 * Return the position of <code>planetoid</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException;

	/**
	 * Return the velocity of <code>planetoid</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException;

	/**
	 * Return the radius of <code>planetoid</code>.
	 */
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException;

	/**
	 * Return the mass of <code>planetoid</code>.
	 */
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException;

	/**
	 * Return the total traveled distance of <code>planetoid</code>.
	 */
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException;

	/**
	 * Return the world in which <code>planetoid</code> is positioned.
	 */
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException;

	/**********
	 * PROGRAMS
	 **********/

	/**
	 * Return the program loaded on the given ship.
	 */
	public Program getShipProgram(Ship ship) throws ModelException;

	/**
	 * Load the given program on the given ship.
	 */
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException;

	/**
	 * Execute the program loaded on the given ship during the given period of
	 * time. The ship is positioned in some world. Returns null if the program
	 * is not completely executed. Otherwise, returns the objects that have been
	 * printed.
	 * 
	 * This method is only used in the tests. The GUI never calls this method,
	 * only the
	 * {@link #evolve(World, double, asteroids.part2.CollisionListener)} method.
	 */
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException;

	/**
	 * Creates a new program factory.
	 */
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException;
}
