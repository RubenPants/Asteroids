package asteroids.model;

import asteroids.part2.CollisionListener;

/**
 * A class that describes asteroids and their properties. An asteroid has a position and
 * a velocity, both are described in a Cartesian xy-field. It also has an
 * orientation and a radius which defines its circular shape. The mass, density and maximum total velocity are 
 * the asteroids last properties.
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
public class Asteroid extends MinorPlanet {
	
	/// CONSTRUCTOR ///
	
	/**
	 * Initializes a new asteroid with given parameters.
	 * 
	 * @note	This constructor will never be used in facade, but we made it to increase the adaptability.
	 * 			In the future, it would be possible that each planetoid has a different maximum velocity and density.
	 * 			In this case, this constructor would come in handy.
	 * 
	 * @param 	positionX
	 *          The horizontal position of the asteroid in kilometers.
	 * @param 	positionY
	 *          The vertical position of the asteroid in kilometers.
	 * @param 	velocityX
	 *          The horizontal starting velocity of the asteroid in kilometers per second.
	 * @param 	velocityY
	 *          The vertical starting velocity of the asteroid in kilometers per second.
	 * @param 	radius
	 *          The radius that defines the circular shape of the asteroid in kilometers.
	 * @param 	orientation
	 *          The orientation of the asteroid in radians.
	 * @param 	mass
	 *          The mass of the asteroid in kilograms.
	 * @param 	maxVelocity
	 *          The maximum velocity of the asteroid in kilometers per second.
	 * @param 	density
	 *          The density of the asteroid in kilograms/km^3.     
	 *          
	 * @effect	This asteroid will be initialized as a new entity with a given position (x, y), velocity (velocityX, velocityY),
	 *			radius, density, mass and maximum velocity.
	 *		  | super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density)	 	
	 */
	public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density) {
		super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
	}
	
	/**
	 * Initializes a new asteroid with given parameters and the non-given parameters set to default.
	 * 
	 * @param 	positionX
	 *          The horizontal position of the asteroid in kilometers.
	 * @param 	positionY
	 *          The vertical position of the asteroid in kilometers.
	 * @param 	velocityX
	 *          The horizontal starting velocity of the asteroid in kilometers per second.
	 * @param 	velocityY
	 *          The vertical starting velocity of the asteroid in kilometers per second.
	 * @param 	radius
	 *          The radius that defines the circular shape of the asteroid in kilometers.
	 * 
	 * @effect 	The asteroid is initialized with the given values and the default orientation, mass, maximum 
	 *  		velocity and density.   
	 */
	public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius){
		this(positionX, positionY, velocityX, velocityY, radius, getDefaultOrientation(), getDefaultAsteroidMass(),
				getDefaultMaxVelocity(), getDefaultAsteroidDensity());
	}
	
	/**
	 * Initializes a new asteroid with all it's parameters set to default.
	 * 
	 * @effect 	The asteroid is initialized with the default values.
	 *         	@see implementation
	 */
	public Asteroid(){
		this(getDefaultPositionX(),getDefaultPositionY(),getDefaultVelocityX(),getDefaultVelocityY(),getDefaultMinorPlanetRadius());
	}
	
	
	/// DEFAULTS ///
	
	/**
	 * A method that returns the default asteroid density.
	 * 
	 * @return 	The density.
	 * 			@see implementation
	 */
	private static double getDefaultAsteroidDensity(){
		return 2.65E12;
	}
	
	/**
	 * Return the default Asteroid mass.
	 * 
	 * @return 	The default mass.
	 * 			@see implementation
	 */
	private static double getDefaultAsteroidMass() {
		return MassFormula(getDefaultMinorPlanetRadius(), getDefaultAsteroidDensity());
	}
	
	
	/// SETTERS ///
	
	/**
	 * Set the asteroids density to a given density.
	 * 
	 * @param 	density
	 * 			The asteroids new density.
	 * 
	 * @post 	If the density is valid, the new density will be equal to the given density. Otherwise, it will be equal to the default asteroid density.
	 * 		 	@see implementation
	 */
	protected void setEntityDensity(double density){
		if (isValidDensity(density))
			this.density = density;
		else
			this.density = getDefaultAsteroidDensity();
	}


	
	/// CHECKERS ///
	
	/**
	 * Checks if a density is valid for this asteroid.
	 * 
	 * @param 	density
	 * 			The density that has to be checked.
	 * 
	 * @return 	The boolean that checks the density.
	 * 			@see implementation
	 */
	protected boolean isValidDensity(double density){
		return (density == getDefaultAsteroidDensity());
	}
	
	
	/// COLLISIONS ///
	
	/**
	 * A method that resolves the collision between a ship and an asteroid.
	 * 
	 * @param 	ship
	 * 			The ship that will collide with the entity where the method is invoked on.
	 * @param 	collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param 	defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param 	collisionListener
	 * 			A variable used to visualize the explosions.
	 */
	protected void entityAndShipCollide(Ship ship,double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener){
		double collisionPositionX = collisionPosition[0];
		double collisionPositionY = collisionPosition[1];
		
		if (collisionListener != null)
			collisionListener.objectCollision(this, ship,collisionPositionX,collisionPositionY);
		
		ship.Terminate();
	}
	

	/// TERMINATE ///
	
	/**
	 * Terminate the asteroid.
	 * 
	 * @post	The asteroids state will be set to Terminated. If the asteroid was in a world, it will 
	 * 			be removed from this world. 
	 * 			@see implementation
	 */
	public void Terminate() {
		if (isEntityFree())
			setEntityState(State.TERMINATED);
		
		else if (isEntityInWorld()) {
			getEntityWorld().removeEntityFromWorld(this);
			setEntityState(State.TERMINATED);
		}
	}
	
}
