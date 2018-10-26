package asteroids.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import asteroids.part2.CollisionListener;
import asteroids.program.Program;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class that describes ships and their properties. A ship has a position and
 * a velocity, both are described in a Cartesian xy-field. It also has an
 * orientation and a radius which defines its circular shape. The thruster-activity 
 * and thruster-force define the ship's thruster. The mass, density and maximum total velocity are 
 * the ship's last properties.
 * 
 * @invar 	The position is a valid position.
 * 		  | isValidPosition(getEntityPositionX, getEntityPositionY)
 * @invar 	The velocity is a valid velocity.
 * 		  | isValidVelocity(getEntityVelocityX, getEntityVelocityY)
 * @invar 	The orientation is a valid orientation.
 * 		  | isValidOrientation(getEntityOrientation)
 * @invar 	The radius is a valid radius.
 * 		  | isValidRadius(getEntityRadius)
 * @invar 	The mass is a valid mass.
 * 		  | isValidMass(getEntityMass)
 * @invar 	The density is a valid density.
 * 		  | isValidDensity(getEntityDensity)
 * 
 * @authors Sieben Bocklandt and Ruben Broekx
 * 
 */
public class Ship extends Entity {

	/// CONSTRUCTORS ///

	/**
	 * Initializes a new ship with given parameters.
	 * 
	 * @note	This constructor will never be used in facade, but we made it to increase the adaptability.
	 * 			In the future, it would be possible that each ship has a different maximum velocity, density
	 * 			and thruster-force. In this case, this constructor would come in handy.
	 * 
	 * @param 	positionX
	 *          The horizontal position of the ship in kilometers.
	 * @param 	positionY
	 *          The vertical position of the ship in kilometers.
	 * @param 	velocityX
	 *          The horizontal starting velocity of the ship in kilometers per second.
	 * @param 	velocityY
	 *          The vertical starting velocity of the ship in kilometers per second.
	 * @param 	radius
	 *          The radius that defines the circular shape of the ship in kilometers.
	 * @param 	orientation
	 *          The orientation of the ship in radians.
	 * @param 	mass
	 *          The mass of the ship in kilograms.
	 * @param 	maxVelocity
	 *          The maximum velocity of the ship in kilometers per second.
	 * @param 	density
	 *          The density of the ship in kilograms/km^3.
	 * @param 	thrusterActivity
	 *          The boolean that is true when the thruster is active, false when it's off.
	 * @param 	thrusterforce
	 *          The force [Newton] of the thruster that a ship can, and shall deliver.
	 *          
	 * @effect	This ship will be initialized as a new entity with a given position (x, y), velocity (velocityX, velocityY),
	 *			radius, density, mass and maximum velocity.
	 *		  | super(x, y, velocityX, velocityY, radius, orientation, mass, maxVelocity, density)	 	
	 * @effect	The thruster-activity will be set on the given boolean value.
	 * 		  | setThrusterActive(thrusterActivity)
	 * @effect	The thruster-force of the ship will be set to the given thruster-force.
	 *		  | setShipThrusterForce(thrusterForce)
	 */
	public Ship(double positionX, double positionY, double velocityX, double velocityY, double radius, double orientation, double mass,
			double maxVelocity, double density, boolean thrusterActivity, double thrusterForce) {
		super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
		
		setThrusterActive(thrusterActivity);
		setShipThrusterForce(thrusterForce);
	}
	
	/**
	 * Initializes a new ship with given parameters and the non-given parameters set to default.
	 * 
	 * @param 	positionX
	 *          The horizontal position of the ship in kilometers.
	 * @param 	positionY
	 *          The vertical position of the ship in kilometers.
	 * @param 	velocityX
	 *          The horizontal starting velocity of the ship in kilometers per second.
	 * @param 	velocityY
	 *          The vertical starting velocity of the ship in kilometers per second.
	 * @param 	radius
	 *          The radius that defines the circular shape of the ship in kilometers.
	 * @param 	orientation
	 *          The orientation of the ship in radians.
	 * @param 	mass
	 *          The mass of the ship in kilograms.
	 * 
	 * @effect 	The ship is initialized with the given values and the default maximum 
	 *  		velocity, density, thruster-activity and thruster-force.
	 *         	@see implementation
	 */
	public Ship(double positionX, double positionY, double velocityX, double velocityY, double radius, double orientation,
			double mass) {
		this(positionX, positionY, velocityX, velocityY, radius, orientation, mass, Entity.getDefaultMaxVelocity(),
				getDefaultShipDensity(), getDefaultThrusterActivity(), getDefaultThrusterForce());
	}

	/**
	 * Initializes a new ship with all it's parameters set to default.
	 * 
	 * @effect 	The ship is initialized with the default values.
	 *         	@see implementation
	 */
	public Ship() {
		this(getDefaultPositionX(), getDefaultPositionY(), getDefaultVelocityX(), getDefaultVelocityY(),
				getDefaultShipRadius(), getDefaultOrientation(), getDefaultMass());
	}
	
	
	/// BASIC PROPERTIES ///
	
	private boolean thruster_activity;
	private double thruster_force;

	
	/// CONSTANTS ///
	
	/**
	 * The smallest radius a ship can have.
	 */
	private final static double LOWER_SHIP_RADIUS = 10;

	
	/// DEFAULTS ///

	/**
	 * Return the default mass of a ship.
	 * 
	 * @return 	The default mass. This mass is not constant for all ships but depends on the size, 
	 * 			and density of the given ship.
	 * 			@see implementation
	 */
	@Immutable
	private static double getDefaultMass() {
		return MassFormula(getDefaultShipRadius(), getDefaultShipDensity());
	}

	/**
	 * Return the default density of a ship.
	 * 
	 * @return 	The default density is equal to 1.42E12.
	 * 			@see implementation
	 */
	private static double getDefaultShipDensity() {
		return 1.42E12;
	}
	
	/**
	 * Return the default radius of a ship.
	 * 
	 * @return 	The default radius is equal to ten.
	 * 			@see implementation
	 */
	@Immutable
	private static double getDefaultShipRadius() {
		return 10;
	}

	/**
	 * Return the default thruster-activity of a ship.
	 * 
	 * @return 	The default thruster-activity which will set to false.
	 * 			@see implementation
	 */
	@Immutable
	private static boolean getDefaultThrusterActivity() {
		return false;
	}

	/**
	 * Return the default thruster-force of a ship.
	 * 
	 * @return 	The default thruster-force is equal to 1.1E18.
	 * 			@see implementation
	 */
	@Immutable
	private static double getDefaultThrusterForce() {
		return 1.5E20;
	}

	/**
	 * Return the initial velocity at which a ship fires a bullet.
	 * 
	 * @return 	The initialFiringVelocity is equal to 250.
	 * 			@see implementation
	 */
	@Immutable
	private double getInitialFiringVelocity() {
		return 250;
	}

	
	/// GETTERS ///

	/**
	 * Return the total mass of the ship. This is the sum of the mass of the
	 * ship and the total weight of its bullets.
	 * 
	 * @return 	the total weight.
	 * 			@see implementation
	 */
	@Basic
	public double getEntityMass() {
		double bullets_weight = ((Ship) this).getTotalBulletsWeight();
		return (mass + bullets_weight);
	}
	
	/**
	 * Return the minimum mass a ship can have.
	 * 
	 * @return 	The minimum mass which will be calculated with the radius and the density of the ship.
	 * 		    @see implementation
	 */
	private double getMinimumShipMass() {
		return MassFormula(getEntityRadius(), getEntityDensity());
	}

	/**
	 * Return the number of bullets loaded on the ship.
	 * 
	 * @return 	the total number of bullets.
	 * 			@see implementation
	 */
	public int getNbBulletsOnShip() {
		return getShipBullets().size();
	}
	
	/** 
	 * Return the acceleration of the ship computed with Newton's third law.
	 * 
	 * @return 	The acceleration which will be calculated with the thruster-force and the mass of the ship. Zero is returned when the thrusters aren't active.
	 * 			@see implementation
	 */
	public double getShipAcceleration() {
		if (isThrusterActive())
			return (getShipTrusterForce() / getEntityMass());
		else
			return 0;
	}

	/**
	 * Return a set containing all the bullets that are loaded on the ship.
	 * 
	 * @return 	The set of bullets.
	 * 			@see implementation
	 */
	public Set<Bullet> getShipBullets() {
		Set<Bullet> result = new HashSet<Bullet>();
		
		result.addAll(bullets.values());
		
		return result;
	}
	
	public Program getShipProgram(){
		return program;
	}

	/**
	 * Return the thruster-force of the ship.
	 * 
	 * @return 	The thruster-force.
	 * 			@see implementation
	 */
	@Basic
	private double getShipTrusterForce() {
		return thruster_force;
	}

	/**
	 * Return the total weight of the bullets loaded on the ship. This is the
	 * sum of the weights of all the bullets.
	 * 
	 * @return 	The total weight.
	 * 			@see implementation
	 */
	private double getTotalBulletsWeight() {
		return getShipBullets().stream().mapToDouble(x -> x.getEntityMass()).sum();
			
	}
	
	
	/// SETTERS ///

	/**
	 * Give the ship a new density.
	 * 
	 * @param 	density
	 *          The new density.
	 *          
	 * @post 	If the given density is a valid value, the ship's density will be equal to it.
	 * 			Otherwise will the density be set to the default ship density.
	 *          @see implementation
	 */
	protected void setEntityDensity(double density) {
		if (isValidDensity(density))
			this.density = density;

		else
			this.density = getDefaultShipDensity();
	}

	/**
	 * Give the ship a new mass.
	 * 
	 * @param 	mass
	 *        	The new mass.
	 *        
	 * @post 	If the given mass is a valid value, the ship's mass will be equal to it.
	 *       	Otherwise will the mass be set to the minimum value. 
	 *       	@see implementation
	 */
	protected void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		
		else
			this.mass = getMinimumShipMass();
	}

	/**
	 * Give the ship a new thrusterForce.
	 * 
	 * @param 	thrusterForce
	 *          The new thrusterForce.
	 *          
	 * @post 	If the given force is positive, the thruster-force of the ship
	 *       	will be equal to that given value.
	 * 			@see implementation
	 * @post 	If the given force is negative, the thruster-force of the ship 
	 * 			will be equal to the default value.
	 *        	@see implementation
	 */
	private void setShipThrusterForce(double thrusterForce) {
		if (thrusterForce < 0)
			thrusterForce = getDefaultThrusterForce();

		thruster_force = thrusterForce;
	}
	
	/**
	 * Give the ship a new thrusterActivity.
	 * 
	 * @param 	thrusterActivity
	 *          The new thrusterActivity.
	 *          
	 * @effect 	The thrusterActivity of the ship will be equal to the given boolean value.
	 * 			@see implementation
	 */
	public void setThrusterActive(boolean thrusterActivity) {
		if (thrusterActivity)
			thrustOn();
		else
			thrustOff();
	}

	
	/// CHECKERS ///

	/**
	 * Checks if a bullet may be loaded on the ship.
	 * 
	 * @param 	bullet
	 *          The bullet that has to be checked.
	 *          
	 * @return 	False if the bullet is already in a ship
	 * 		  | if (bullet.getBulletShip() != null)
	 * 		  |   result == false
	 * @return  False if the bullet isn't completely in the ship.
	 * 		  | if (!bulletFullyInShip(bullet))
	 *		  |   result == false
	 * @return  False if the bullet is null
	 * 		  | if(bullet == null)
	 * 		  |   result == false 
	 * @return  False if the bullet or the ship is terminated. 
	 * 		  | if (bullet.isEntityTerminated() || isEntityTerminated())
	 * 		  |   result == false
	 * @return  True in all other cases.
	 * 		  | else
	 * 		  |   result == true
	 */
	protected boolean canHaveAsBullet(Bullet bullet) {
		if(bullet == null)
			return false;
		
		if (bullet.getBulletShip() != null)
			return false;
			
		if (!isBulletFullyInShip(bullet))
			return false;

		if (bullet.isEntityTerminated() || isEntityTerminated())
			return false;

		return true;
	}
	
	/**
	 * Checks if a bullet is already loaded on the ship.
	 * 
	 * @param 	bullet
	 *          The bullet that has to be checked.
	 *          
	 * @return 	False if the bullet is already on the ship.
	 * 			@see implementation
	 */
	protected boolean hasAsBullet(Bullet bullet) {
		return bullets.containsKey(bullet.hashCode());
	}

	/**
	 * Checks if a bullet lies fully in the ship.
	 * 
	 * @param 	bullet
	 *          The bullet that has to be checked.
	 *          
	 * @return 	False if the sum of distance between the centers and the radius 
	 * 			of the bullet is greater than the radius of the ship.
	 *         	@see implementation
	 */
	private boolean isBulletFullyInShip(Bullet bullet) {
		double delta_x = Math.abs(bullet.getEntityPositionX() - getEntityPositionX());
		double delta_y = Math.abs(bullet.getEntityPositionY() - getEntityPositionY());
		double bullet_radius = bullet.getEntityRadius();
		double ship_radius = getEntityRadius();
		double distance_between = getEuclidianDistance(delta_x, delta_y);
		
		return ((distance_between + bullet_radius) < ship_radius);
	}
	
	/**
	 * Return the thrusterActivity of the ship.
	 * 
	 * @return 	The thrusterActivity.
	 * 			@see implementation
	 */
	@Basic
	public boolean isThrusterActive() {
		return thruster_activity;
	}

	/**
	 * Checks if the given density is valid.
	 * 
	 * @param 	density
	 *          The density that has to be checked.
	 *          
	 * @return 	True if the density is greater than the default density.
	 * 			@see implementation
	 */
	protected boolean isValidDensity(double density) {
		return (density >= getDefaultShipDensity());
	}

	/**
	 * Checks if the given mass is valid.
	 * 
	 * @param 	mass
	 *          The mass that has to be checked.
	 *          
	 * @return 	True if the mass is greater than the minimum mass and the mass is a number.
	 * 			@see implementation
	 */
	protected boolean isValidMass(double mass) {
		return ((mass != Double.NaN) && (mass >= getMinimumShipMass()));
	}

	/**
	 * Checks if the given radius is valid.
	 * 
	 * @param 	radius
	 *          The radius that has to be checked.
	 *          
	 * @return 	True if the radius is greater than the default lower ship radius.
	 *          @see implementation
	 */
	protected boolean isValidRadius(double radius) {
		return (radius >= LOWER_SHIP_RADIUS);
	}

	
	/// ADDERS ///

	/**
	 * Load multiple bullets on the ship.
	 * 
	 * @param 	bullets
	 *          The collection of bullets that have to be loaded.
	 *          
	 * @effect 	The bullets will be checked by a stream. If the ship can have all the bullets, they will be added one at a time.
	 * 			@see implementation
	 * @throws  IllegalArgumentExeption
	 * 			If one of the bullets is not valid.
	 * 			@see implementation
	 */
	public void addMultipleBulletsToShip(Collection<Bullet> bullets) throws IllegalArgumentException {
		if(bullets.stream().allMatch(bullet ->canHaveAsBullet(bullet)))
			bullets.forEach(bullet -> addOneBulletToShip(bullet));
		else
			throw new IllegalArgumentException();
	}

	public void addProgramToShip(Program program){
		this.program = program;
		program.setProgramShip(this);
	}

	/**
	 * Load one bullet on the ship.
	 * 
	 * @param 	bullet
	 *          The bullet that has to be loaded.
	 *          
	 * @post 	If the ship can have the bullet, the bullet will be loaded.
	 *        | new.hasAsBullet == true
	 *        
	 * @throws 	IllegalArgumentException
	 *          If the bullet cannot be loaded on the ship.
	 *        | !canHaveAsBulet(bullet)
	 */
	public void addOneBulletToShip(Bullet bullet) throws IllegalArgumentException {
		if (canHaveAsBullet(bullet)) {
			bullets.put(bullet.hashCode(), bullet);
			bullet.setBulletLoaded(this);
		} 
		else
			throw new IllegalArgumentException();
	}
	
	
	/// REMOVERS ///

	/**
	 * Remove a bullet from the ship.
	 * 
	 * @param 	bullet
	 *       	The bullet that has to be removed.
	 *       
	 * @post 	The bullet isn't loaded on the ship anymore.
	 *        | !new.hasAsBullet(bullet)
	 *        
	 * @throws 	IllegalArgumenException
	 *          If the bullet wasn't loaded on the ship.
	 *        | hasAsBullet(bullet)
	 */
	public void removeBulletFromShip(Bullet bullet) {
		if (!hasAsBullet(bullet))
			throw new IllegalArgumentException();

		else {
			bullets.remove(bullet.hashCode());
			bullet.setBulletNotLoaded(this);
		}
	}

	
	/// MOVE ///
	
	/**
	 * Let a ship move for a given time "moveTime".
	 * 
	 * @param 	moveTime
	 * 			The time for which the ship has to move.
	 * @post 	If the ship's thruster is active, its acceleration will be used to recalculate the velocity.
	 * 			If the thuster isn't active, the acceleration will be 0 and the velocity will not be changed
	 * 			@see implementation
	 * 
	 * @post 	After moveTime, the ship's position will be set on moveTime times its velocity. 
	 * 			@see implementation
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the given time is negative.
	 * 		  | moveTime < 0
	 */
	protected void move(double moveTime) {
		if (moveTime < 0)
			throw new IllegalArgumentException();

		double velocityX = getEntityVelocityX();
		double velocityY = getEntityVelocityY();

		final double collidingPositionX = getEntityPositionX() + velocityX * moveTime;
		final double collidingPositionY = getEntityPositionY() + velocityY * moveTime;

		final double acceleration = getShipAcceleration();
		final double orientation = getEntityOrientation();
			
		double newVelocityX = velocityX + acceleration * Math.cos(orientation) * moveTime;
		double newVelocityY = velocityY + acceleration * Math.sin(orientation) * moveTime;
			
		setEntityVelocity(newVelocityX, newVelocityY);
		
		setPositionWithoutChecking(collidingPositionX, collidingPositionY);
	}


	/// TURN ///
	
	/**
	 * Update the direction of the ship by adding an angle in radians to its
	 * current direction. The angle may be negative.
	 * 
	 * @param 	angle
	 *          The given angle which will determine the difference in orientation.
	 * 
	 * @pre 	The new orientation will be between 0 and 2*PI, 0 included.
	 *        | isVallidRadian(getEntityOrientation() + angle)
	 * 
	 * @effect 	The new orientation of the ship is equal to the sum of the
	 *         	orientation and the given angle.
	 *        | setEntityOrientation(getEntityOrientation() + angle)
	 */
	public void turn(double angle) {
		assert isValidOrientation(getEntityOrientation() + angle);
		
		setEntityOrientation(getEntityOrientation() + angle);
	}

	
	/// FIRE ///
	
	/**
	 * Fires a random bullet that lies in the ship.
	 *
	 * @post 	The ship does not contain the random bullet anymore.
	 *		  | new.hasAsBullet(bullet) == false
	 * @post 	The bullet's position will be equal to the position of the ship, mapped by the 
	 * 			orientation with a distance of one kilometer between the bullet and the ship.
	 *			@see implementation
	 *
	 * @effect 	The bullet is removed from the ship.
	 *		  | removeBulletFromShip
	 * @effect 	The orientation of the bullet will be set to the orientation of the ship.
	 *			@see implementation
	 * @effect 	The velocity of the bullet will be set to the initial firing velocity (mapped into the velocityX and velocityY).
	 *			@see implementation
	 * @effect 	There will be checked if the bullet can be fired, the possible collisions will be resolved.
	 *		  | possibleToFire(bullet,this,world, positionBulletX, positionBulletY, radiusBullet)
	 */
	public void fireBullet() {
		if (!bullets.isEmpty() && isEntityInWorld()) {
			
			Map.Entry<Integer, Bullet> entry = bullets.entrySet().iterator().next();
			Bullet bullet = entry.getValue();

			removeBulletFromShip(bullet);

			double positionShipX = getEntityPositionX();
			double positionShipY = getEntityPositionY();
			double orientation = getEntityOrientation();
			double radiusShip = getEntityRadius();
			double radiusBullet = bullet.getEntityRadius();
			double positionBulletX = positionShipX + Math.cos(orientation) * (radiusShip + radiusBullet + 1);
			double positionBulletY = positionShipY + Math.sin(orientation) * (radiusShip + radiusBullet + 1);

			bullet.setPositionWithoutChecking(positionBulletX, positionBulletY);
			bullet.setEntityOrientation(orientation);
			bullet.setEntityVelocity(getInitialFiringVelocity() * Math.cos(orientation),
					getInitialFiringVelocity() * Math.sin(orientation));
			
			World world = getEntityWorld();
			
			if (possibleToFire(bullet, world, positionBulletX, positionBulletY, radiusBullet))
				world.addEntityToWorld(bullet);
		}
	}

	/**
	 * Checks whether a bullet can be fired. If, when the bullet will be fired, there would be any collision, then 
	 * this collisions will be resolved.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	bullet
	 * 			The bullet that will be checked.
	 * @param 	world
	 * 			The world where the bullet is fired in.
	 * @param 	posBulletX
	 * 			The x-position of the bullet.
	 * @param 	posBulletY
	 * 			the y-position of the bullet.
	 * @param 	radiusBullet
	 * 			The radius of the bullet.
	 * 
	 * @return 	False if the bullet cannot be set in the given world.
	 * 			@see implementation
	 * @return 	False if the bullet overlaps with another entity.
	 * 			@see implementation
	 * 
	 * @effect 	The bullet will be terminated when it's not within the boundaries of the world.
	 * 		  | if (!bullet.entityLiesInBoundaries(world))
	 * 		  |   bullet.Terminate()
	 * @effect 	The bullet will be reloaded if it overlaps with a bullet with the same source ship.
	 * 			@see implementation
	 * @effect 	The bullet will be terminated when it overlaps with a bullet that doesn't have the same source ship.
	 * 			@see implementation
	 * @effect 	The bullet will be reloaded if it overlaps with its source ship.
	 * 			@see implementation
	 * @effect 	If the bullet is overlapping a ship which is not its source ship, both will be terminated.
	 * 			@see implementation
	 */
	private  boolean possibleToFire(Bullet bullet, World world, double posBulletX, double posBulletY,
			double radiusBullet) {
		boolean Boolean = true;

		// Check if the new-created bullet is in the world
		if (!bullet.entityLiesInBoundaries(world)) {
			bullet.Terminate();
			Boolean = false;
		}

		if (Boolean == true) {
			for (Entity entityInWorld : world.getWorldEntities()) {
				// Two entities are overlapping when the distance between the centers is bigger than the sum of 
				//  the radii of the two.
				if (bullet.overlap(entityInWorld) && Boolean == true) {
					Boolean = false;

					// If entityInWorld is a bullet:
					if (entityInWorld instanceof Bullet) {
						// If the bullet overlaps with a bullet from its parent-ship, the newest bullet will not be fired.
						if (this.equals(((Bullet) entityInWorld).getBulletSource())) {
							bullet.setPositionWithoutChecking(getEntityPositionX(), getEntityPositionY());
							addOneBulletToShip(bullet);
						}

						// If the bullet overlaps with a bullet which does not belong to its parent-ship, the two will be terminated.
						else {
							bullet.Terminate();
							((Bullet) entityInWorld).Terminate();
						}
					}

					// If entityInWorld is a ship:
					else if (entityInWorld instanceof Ship) {
						// If the bullet overlaps with its parent-ship, the bullet will be reloaded.
						if (this.equals(entityInWorld)) {
							bullet.setPositionWithoutChecking(getEntityPositionX(), getEntityPositionY());
							addOneBulletToShip(bullet);
						}

						// If the bullet overlaps with a different ship, the two will be terminated.
						else {
							bullet.Terminate();
							((Ship) entityInWorld).Terminate();
						}
					}
				}
			}
		}
		return Boolean;
	}

	
	/// THRUSTER ///

	/**
	 * Set the ship's thruster on inactive.
	 * 
	 * @post 	The ship's thruster is inactive.
	 * 		  | new.isThrusterActive == False
	 */
	private void thrustOff() {
		thruster_activity = false;
	}
	
	/**
	 * Set the ship's thruster on active.
	 * 
	 * @post 	The ship's thruster is active.
	 * 		  | new.isThrusterActive == True
	 */
	private void thrustOn() {
		thruster_activity= true;
	}
	

	/// COLLISIONS ///

	/**
	 * A method that resolves the collision between two ships.
	 * 
	 * @param 	ship
	 * 			The ship that will collide with the entity where the method is invoked on.
	 * @param 	collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param 	defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param 	collisionListener
	 * 			A variable used to visualize the explosions.
	 * 
	 * @effect 	Because the two entities are both ships, doubleShipOrMinorPlanetCollide will be used. 
	 * 			@see implementation
	 */
	@Override
	protected void entityAndShipCollide(Ship ship, double[] collisionPosition, double defaultEvolvingTime,CollisionListener collisionListener) {
		this.doubleShipOrMinorPlanetCollide(ship, defaultEvolvingTime);		
	}
	

	/// TERMINATION ///
	
	/**
	 * Terminate the ship.
	 * 
	 * @post	The ship's state will be set to Terminated. If the ship was in a world, it will 
	 * 			be removed from this world. If it had bullets, these bullets will be terminated too.
	 * 			@see implementation
	 */
	public void Terminate() {
		if (isEntityFree())
			setEntityState(State.TERMINATED);
		
		else if (isEntityInWorld()) {
			getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);
		}

		for (Bullet bullet : getShipBullets()) {
			removeBulletFromShip(bullet);
			bullet.Terminate(); 
		}
	}

	
	/// RUN PROGRAM ///
	
	public List<Object> executeProgram(double dt){
		return getShipProgram().execute(dt);
	}
	
	/// RELATIONS WITH OTHER CLASSES ///

	/**
	 * The map bullets is a map with as key the hash-code representing the bullet, and as value the bullet itself.
	 */
	private final Map<Integer, Bullet> bullets = new HashMap<Integer, Bullet>();

	private  Program program = null;

}

