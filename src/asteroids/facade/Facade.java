package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.Planetoid;
import asteroids.model.Asteroid;


import asteroids.model.World;
import asteroids.part2.CollisionListener;

import asteroids.part3.programs.IProgramFactory;
import asteroids.program.Program;
import asteroids.program.ProgramFactory;
import asteroids.util.ModelException;



/**
 * A class that can be used to connect the IFacade class with the class Ship.
 * 
 * @version 18th of May
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public class Facade implements asteroids.part3.facade.IFacade  {
		
	/// SHIP ///
	
	/**
	 * Returns a Ship with default values.
	 * 
	 * @post 	The ship has a circular shape with radius 1. 
	 * 		  | ship.getShipRadius() == 1;
	 * @post 	The position of the ship will be {0,0}
	 * 		  | ship.getShipPosition() = {0,0};
	 * @post 	The ship's orientation will be equal to 0, which means it's facing right.
	 *  	  | ship.getShipOrientation() = 0;
	 * @post 	The ship's velocity will be {0,0}.
	 * 		  | ship.getShipVelocity() = {0,0};
	 * @post    The ship's maximum velocity will be equal to the speed of light.
	 * 		  | ship.getShipMaximumVelocity == SPEED_OF_LIGHT
	 * 
	 * @return  A ship with default values will be returned.
	 * 		  | result = Ship()
	 * 
	 * @throws  Modelexception
	 * 			If the default radius or the default position is not correct.
	 * 		  | (getDefaultRadius <LOWER_RADIUS || !isValidArray(getDefaultPosition))
	 */
	@Override
	public Ship createShip() throws ModelException {
		try {
			Ship ship = new Ship();
			return ship;
		} catch (IllegalArgumentException | AssertionError Error) {
			throw new ModelException("these are no valid default arguments #1");
		}
	}
	
	/**
	 * Returns a ship with given parameters. 
	 * 
	 * @return	A new ship with given properties and a default maximum velocity will be returned.
	 * 		  | result = Ship(x, y, xVelocity, yVelocity, radius, orientation)
	 */
	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation,
			double mass) throws ModelException {
		try {
			Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, orientation, mass);
			return ship;
		} catch (IllegalArgumentException | AssertionError Error) {
			throw new ModelException("these are no valid arguments #2");
		}
	}

	/**
	 * 	Returns the position of the ship as an array of length 2, with the x-coordinate at index 0 and the
	 *  y-coordinate at index 1.
	 *  
	 * @param 	ship
	 *  		The ship whose location is asked .
	 *  
	 * @return	The ship's position.
	 *  	  | result =ship.getShipPosition()
	 * 
	 * @throws ModelException 
	 */
	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		try {
			return ship.getEntityPosition();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #3");
		}
	}
	
	/**
	 * Return the velocity of ship as an array of length 2, with the velocity along the X-axis at index 0 
	 * and the velocity along the Y-axis at index 1.
	 * 
	 * @param 	ship
	 * 			The ship whose velocity is asked.
	 * 
	 * @return  The ship's velocity.
	 *  	  | result =ship.getShipVelocity()
	 * 
	 * @throws ModelException 
	 */
	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		try {
			return ship.getEntityVelocity();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #4");
		}
	}

	/**
	 * Return the radius of the ship.
	 * 
	 * @param 	ship
	 * 			The ship whose radius is asked.
	 * 
	 * @return  The ship's radius.
	 *  	  | result =ship.getShipRadius()
	 */
	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		try {
			return ship.getEntityRadius();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #5");
		}
	}
	
	/**
	 * Return the orientation of the ship in radians.
	 * 
	 * @param 	ship
	 * 			The ship whose orientation is asked.
	 * 
	 * @return  The ship's orientation.
	 *  	  | result =ship.getShipOrientation()
	 */
	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		try {
			return ship.getEntityOrientation();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #6");
		}
	}

	/**
	 * Update the direction of the ship by adding an angle in radians to its current direction.
	 * 
	 * @effect  The ship will turn with the given angle.
	 * 		  | ship.turn(angle)
	 */
	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		try {
			ship.turn(angle);
		} catch (IllegalArgumentException | AssertionError Error) {
			throw new ModelException("these are not valid arguments #7");
		}
	}
	
	/**
	 * Calculate the distance between two ships.
	 * 
	 * @return	The distance between these ships.
	 * 		  | result = ship1.getDistanceBetween(ship2)
	 * 
	 * @throws ModelException 
	 */
	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #8");
		}
	}	

	/**
	 * Returns a boolean saying if the two ships are overlapping.
	 * 
	 * @return	Return True if the two ships overlap. 
	 * 		  | result = ship1.overlap(ship2)
	 * 
	 * @throws ModelException 
	 */
	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #9");
		}
	}	

	/**
	 * Calculates the number of seconds until, if ever, the first collision between two ships will take place.
	 * 
	 * @return	The seconds until collision or null if they never collide.
	 *		  | result= ship1.getTimeToCollision(ship2)
	 */
	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are no valid arguments #10");
		}
	}

	/**
	 * Calculates the position, if there is one, of the collision between two ships.
	 * 
	 * @return 	The position where the ships will collide. If they don't, positive infinity is returned.
	 * 		  | result = ship1.getCollisionPosition
	 */
	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are no valid arguments #11");
		}
	}

	/**
	 * Terminate a given ship.
	 * @see implementation
	 */
	@Override
	public void terminateShip(Ship ship) throws ModelException {
		try {
			ship.Terminate();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #12");
		}
	}

	/**
	 * Check if a given ship is terminated.
	 * @see implementation
	 */
	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		try {
			return ship.isEntityTerminated();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #13");
		}
	}

	/**
	 * Get the mass of a given ship.
	 * @see implementation
	 */
	@Override
	public double getShipMass(Ship ship) throws ModelException {
		try {
			return ship.getEntityMass();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #14");
		}
	}

	/**
	 * Return the world to which the ship belongs to.
	 * @see implementation
	 */
	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		try {
			return ship.getEntityWorld();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #15");
		}
	}

	/**
	 * Check if the thruster of the ship is active.
	 * @see implementation
	 */
	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		try {
			return ship.isThrusterActive();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #16");
		}
	}

	/**
	 * Turn the thruster of a given ship on if true, turn it off if false.
	 * @see implementation
	 */
	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		try {
			ship.setThrusterActive(active);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #17");
		}
	}

	/**
	 * Return a given ship its acceleration.
	 * @see implementation
	 */
	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		try {
			return ship.getShipAcceleration();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #18");
		}
	}

	
	/// BULLET ///
	
	/**
	 * Create a bullet with given values.
	 * @see implementation
	 */
	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			Bullet bullet = new Bullet(x, y, xVelocity, yVelocity, radius);
			return bullet;
		} catch (IllegalArgumentException| AssertionError Error) {
			throw new ModelException("this are not valid arguments #19");
		}
	}

	/**
	 * Terminate a given bullet.
	 * @see implementation
	 */
	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		try {
			bullet.Terminate();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #20");
		}
	}

	/**
	 * Check if a given bullet is terminated.
	 * @see implementation
	 */
	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		try {
			return bullet.isEntityTerminated();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #21");
		}
	}

	/**
	 * Return the position of a given bullet.
	 * @see implementation
	 */
	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		try {
			return bullet.getEntityPosition();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #22");
		}
	}

	/**
	 * Return the velocity of a given bullet.
	 * @see implementation
	 */
	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		try {
			return bullet.getEntityVelocity();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #23");
		}
	}

	/**
	 * Return the radius of a given bullet.
	 * @see implementation
	 */
	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		try {
			return bullet.getEntityRadius();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #24");
		}
	}

	/**
	 * Return the mass of a given bullet.
	 * @see implementation
	 */
	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		try {
			return bullet.getEntityMass();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #25");
		}
	}

	/**
	 * Return the world to which the bullet belongs to.
	 * @see implementation
	 */
	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		try {
			return bullet.getEntityWorld();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #26");
		}
	}

	/**
	 * Return the ship to which the bullet belongs to when the bullet is in a ship.
	 * @see implementation
	 */
	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		try {
			return bullet.getBulletShip();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #27");
		}
	}

	/**
	 * Return the ship to which the bullet belongs to when the bullet is in the world.
	 * @see implementation
	 */
	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		try {
			return bullet.getBulletSource();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #28");
		}
	}
	

	/// WORLD ///

	/**
	 * Create a rectangular world with a given width and a given height.
	 * @see implementation
	 */
	@Override
	public World createWorld(double width, double height) throws ModelException {
		try {
			World world = new World(width, height);
			return world;
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #29");
		}
	}

	/**
	 * Terminate a given world.
	 * @see implementation
	 */
	@Override
	public void terminateWorld(World world) throws ModelException {
		try {
			world.Terminate();
		} catch (IllegalStateException illegalStateException) {
			throw new ModelException("this is not a valid argument #30");
		}
	}

	/**
	 * Check if a given world is terminated.
	 * @see implementation
	 */
	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		try {
			return world.isWorldTerminated();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #31");
		}
	}	

	/**
	 * Get the size (width and height) of a given world.
	 * @see implementation
	 */
	@Override
	public double[] getWorldSize(World world) throws ModelException {
		try {
			return world.getWorldSize();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #32");
		}
	}	

	/**
	 * Return a set of all the ships in a given world.
	 * @see implementation
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		try {
			return (Set<? extends Ship>) world.getWorldSpecificEntities("Ship");
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #33");
		}
	}	

	/**
	 * Return a set of all the bullets in a given ship.
	 * @see implementation
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		try {
			return (Set<? extends Bullet>) world.getWorldSpecificEntities("Bullet");
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #34");
		}
	}	

	/**
	 * Add a given ship to a given world.
	 * @see implementation
	 */
	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addEntityToWorld(ship);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #35");
		}
	}

	/**
	 * Remove a given ship from a given world.
	 * @see implementation
	 */
	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {
			world.removeEntityFromWorld(ship);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #35");
		}
	}

	/**
	 * Add a given bullet to a given world.
	 * @see implementation
	 */
	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.addEntityToWorld(bullet);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #36");
		}
	}

	/**
	 * Remove a given bullet from a given world.
	 * @see implementation
	 */
	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.removeEntityFromWorld(bullet);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #37");
		}
	}

	
	///BULLETS AND SHIPS///
	
	/**
	 * Return the set of bullets that belongs to a given ship.
	 * @see implementation
	 */
	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		try {
			return ship.getShipBullets();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #38");
		}
	}
	

	/**
	 * Return the amount of bullets that are on a given ship.
	 * @see implementation
	 */
	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		try {
			return ship.getNbBulletsOnShip();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #39");
		}
	}

	/**
	 * Load a given bullet onto a given ship.
	 * @see implementation
	 */
	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.addOneBulletToShip(bullet);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #40");
		}
	}

	/**
	 * Load a collection of bullets onto a given ship.
	 * @see implementation
	 */
	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.addMultipleBulletsToShip(bullets);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #41");
		}
	}

	/**
	 * Remove a given bullet from a given ship.
	 * @see implementation
	 */
	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBulletFromShip(bullet);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #42");
		}
	}

	/**
	 * Fire a bullet from the given ship, if possible.
	 * @see implementation
	 */
	@Override
	public void fireBullet(Ship ship) throws ModelException {
		try {
			ship.fireBullet();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #43");
		}
	}

	
	///COLLISIONS///

	/**
	 * Return the time until a given entity will collide with the boundary of the world it's in.
	 * 	Positive infinity will be returned if the collision won't take place.
	 * @see implementation
	 */
	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity) object).getTimeCollisionBoundary();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #44");
		}
	}

	/**
	 * Return the position where the entity would collide with the boundary of the world it's in.
	 *  [infinity, infinity] would be returned if no collision takes place.
	 * @see implementation
	 */
	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity) object).getPositionCollisionBoundary();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #45");
		}
	}

	/**
	 * Return the time until two given entities will collide with each other.
	 * 	Positive infinity will be returned if the collision won't take place.
	 * @see implementation
	 */
	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity) entity1).getTimeToCollision((Entity) entity2);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #46");
		}
	}

	/**
	 * Return the position where two given entities would collide.
	 *  [infinity, infinity] would be returned if no collision takes place.
	 * @see implementation
	 */
	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity) entity1).getCollisionPosition(((Entity) entity2));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #47");
		}
	}

	/**
	 * Return the time till the first collision in a world will take place.
	 *  Positive infinity would be returned if no collision takes place.
	 * @see implementation
	 */
	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		try {
			return world.getTimeNextCollision();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("this is not a valid argument #48");
		}
	}

	/**
	 * Return the position where the first collision in a world will take place.
	 *  [infinity, infinity] would be returned if no collision takes place.
	 * @see implementation
	 */
	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		try {
			return world.getPositionNextCollision();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #49");
		}
	}

	
	/// EVOLVE AND ENTITIES ///
	
	/**
	 * Evolve the world over dt time.
	 * @see implementation
	 */
	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt, collisionListener);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #50");
		}
	}

	/**
	 * Return the entity at a given position, if there is an entity at this given position.
	 * @see implementation
	 */
	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		try {
			return world.getEntityAt(x, y);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #51");
		}
	}

	/**
	 * Return all the entities that are in a given world.
	 * @see implementation
	 */
	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		try {
			return world.getWorldEntities();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #52");
		}
	}
	/**
	 * Return the number of students working in this team
	 * @see implementation
	 */
	@Override
	public int getNbStudentsInTeam() {
			return 2;
			
	}
	
	/**
	 * Return a set of all the asteroids in a world
	 * @see implementation
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		try {
			return (Set<? extends Asteroid>) world.getWorldSpecificEntities("Asteroid");
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #53");
		}
	}

	/**
	 * Add a given asteroid to a given world.
	 * @see implementation
	 */
	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.addEntityToWorld(asteroid);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #54");
		}
	}
	
	/**
	 * Remove a given asteroid from a given world.
	 * @see implementation
	 */
	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.removeEntityFromWorld(asteroid);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #55");
		}
	}
	
	/**
	 * Return a set of all the planetoids in a given world.
	 * @see implementation
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		try {
			return (Set<? extends Planetoid>) world.getWorldSpecificEntities("Planetoid");
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #56");
		}
	}
	/**
	 * Add a given planetoid to a given world.
	 * @see implementation
	 */
	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.addEntityToWorld(planetoid);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #57");
		}
	}
		
	/**
	 * Remove a given planetoid from a given world.
	 * @see implementation
	 */
	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.removeEntityFromWorld(planetoid);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #58");
		}
	}

	/**
	 * Create an asteroid with given values.
	 * @see implementation
	 */
	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			Asteroid asteroid= new Asteroid(x, y, xVelocity, yVelocity, radius);
			return asteroid;
		} catch (IllegalArgumentException | AssertionError Error) {
			throw new ModelException("these are not valid arguments #59");
		}
	}
	/**
	 *Terminate a given asteroid.
	 * @see implementation
	 */
	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		try {
			asteroid.Terminate();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #60");
		}
	}
	/**
	 * Checks whether an asteroid is terminated.
	 * @see implementation
	 */
	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.isEntityTerminated();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #61");
		}
	}
	/**
	 *Returns the position array of the given asteroid.
	 * @see implementation
	 */
	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getEntityPosition();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #62");
		}
	}
	/**
	 *Returns the velocity array of the given asteroid.
	 * @see implementation
	 */
	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getEntityVelocity();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #63");
		}
	}
	/**
	 *Returns the radius of the given asteroid.
	 * @see implementation
	 */
	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getEntityRadius();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #64");
		}
	}
	/**
	 *Returns the mass of the given asteroid.
	 * @see implementation
	 */
	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getEntityMass();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #65");
		}
	}
	/**
	 *Returns the world the given asteroid belongs to.
	 * @see implementation
	 */
	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getEntityWorld();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #66");
		}
	}
	/**
	 * Create a planetoid with given values.
	 * @see implementation
	 */
	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		try {
			Planetoid planetoid = new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
			return planetoid;
		} catch (IllegalArgumentException | AssertionError Error) {
			throw new ModelException("these are not valid arguments #67");
		}
	}
	/**
	 * Terminate a given planetoid.
	 * @see implementation
	 */
	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		try {
			planetoid.Terminate();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #68");
		}
	}
		
	/**
	 * Checks whether a given planetoid is terminated.
	 * @see implementation
	 */
	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.isEntityTerminated();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #69");
		}
	}

	/**
	 *Returns the position array of the given planetoid.
	 * @see implementation
	 */
	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getEntityPosition();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #70");
		}
	}
	/**
	 *Returns the velocity array of the given planetoid.
	 * @see implementation
	 */
	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getEntityVelocity();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #71");
		}
	}
	/**
	 *Returns the radius of the given planetoid.
	 * @see implementation
	 */
	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getEntityRadius();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #72");
		}
	}
	/**
	 *Returns the mass of the given planetoid.
	 * @see implementation
	 */
	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getEntityMass();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #73");
		}
	}
	/**
	 *Returns the total traveled distance of the given planetoid.
	 * @see implementation
	 */
	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getPlanetoidTotalTraveledDistance();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #74");
		}
	}
	/**
	 * Returns the world the given planetoid belongs to.
	 *
	 * @return 	The world to which the given planetoid belongs to
	 * 			@see implementation
	 */
	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getEntityWorld();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #75");
		}
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		try {
			return ship.getShipProgram();
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #76");
		}
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		try {
			ship.addProgramToShip(program);		
		} catch (IllegalArgumentException | NullPointerException error) {
			throw new ModelException("these are not valid arguments #77");
		}
	}


	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		try {
			return ship.executeProgram(dt);	
		} catch (IllegalArgumentException | RuntimeErrorException | IllegalAccessError error) {
			throw new ModelException("these are not valid arguments #78");
		}
	}

	//
	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		try {
			return new ProgramFactory();
			} catch (IllegalArgumentException illegalArgumentException) {
			throw new ModelException("these are not valid arguments #79");
		}
	}
}
