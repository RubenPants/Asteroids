package asteroids.model;

import asteroids.part2.CollisionListener;

/**
 * A class that describes planetoids and their properties. A planetoid has a position and
 * a velocity, both are described in a Cartesian xy-field. It also has an
 * orientation and a radius which defines its circular shape. The mass, density and maximum total velocity are 
 * the planetoids last properties.
 * 
 * @invar 	The position is a valid position.
 * 		  | isValidPosition(getEntityPositionX(), getEntityPositionY())
 * @invar 	The velocity is a valid velocity.
 * 		  | isValidVelocity(getEntityVelocityX(), getEntityVelocityY())
 * @invar 	The orientation is a valid orientation.
 * 		  | isValidOrientation(getEntityOrientation())
 * @invar 	The radius is a valid radius.
 * 		  | isValidRadius(getEntityRadius())
 * @invar 	The mass is a valid mass.
 * 		  | isValidMass(getEntityMass())
 * @invar 	The density is a valid density.
 * 		  | isValidDensity(getEntityDensity())
 * @invar   The total traveled distance is valid.
 * 		  | isValidTotalTraveledDistance(getPlanetoidTotalTraveledDistance())
 * 
 * @authors Sieben Bocklandt and Ruben Broekx
 * 
 */
public class Planetoid extends MinorPlanet {
	
	/// CONSTRUCTOR ///
	
	/**
	 * Initializes a new planetoid with given parameters.
	 * 
	 * @note	This constructor will never be used in facade, but we made it to increase the adaptability.
	 * 			In the future, it would be possible that each planetoid has a different maximum velocity and density.
	 * 			In this case, this constructor would come in handy.
	 * 
	 * @param 	positionX
	 *          The horizontal position of the planetoid in kilometers.
	 * @param 	positionXY
	 *          The vertical position of the planetoid in kilometers.
	 * @param 	velocityX
	 *          The horizontal starting velocity of the planetoid in kilometers per second.
	 * @param 	velocityY
	 *          The vertical starting velocity of the planetoid in kilometers per second.
	 * @param 	radius
	 *          The radius that defines the circular shape of the planetoid in kilometers.
	 * @param 	orientation
	 *          The orientation of the planetoid in radians.
	 * @param 	mass
	 *          The mass of the planetoid in kilograms.
	 * @param 	maxVelocity
	 *          The maximum velocity of the planetoid in kilometers per second.
	 * @param 	density
	 *          The density of the planetoid in kilograms/km^3.     
	 * @param 	totalTraveledDistance
	 * 			The distance the planetoid has traveled in kilometers.    
	 * 
	 * @effect	This planetoid will be initialized as a new entity with a given position (x, y), velocity (velocityX, velocityY),
	 *			radius, density, mass and maximum velocity.
	 *		  | super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density)	 	
	 * @effect	The initialRadius of the planetoid will be set on the given radius.
	 * 		  | setPlanetoidInitialRadius(radius)
	 * @effect	The planetoids total traveled distance will be set on the given value.
	 *		  | setPlanetoidTotalTraveledDistance(totalTraveledDistance)
	 * @effect	The planetoids radius will be updated.
	 * 		  | updatePlanetoidRadius()
	 */
	public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density,double totalTraveledDistance) {
		super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
	
		setPlanetoidInitialRadius(radius);
		setPlanetoidTotalTraveledDistance(totalTraveledDistance);
		updatePlanetoidRadius();
	}
	
	/**
	 * Initializes a new planetoid with given parameters and the non-given parameters set to default.
	 * 
	 * @param 	positionX
	 *          The horizontal position of the planetoid in kilometers.
	 * @param 	positionY
	 *          The vertical position of the planetoid in kilometers.
	 * @param 	velocityX
	 *          The horizontal starting velocity of the planetoid in kilometers per second.
	 * @param 	velocityY
	 *          The vertical starting velocity of the planetoid in kilometers per second.
	 * @param 	radius
	 *          The radius that defines the circular shape of the planetoid in kilometers.
	 * @param 	totalTraveledDistance
	 *          The totalTraveledDistance of the planetoid in kilometers.
	 * 
	 * @effect 	The planetoid is initialized with the given values and the default orientation, mass, maximum 
	 *  		velocity and density.
	 */
	public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius,double totalTraveledDistance){
		this(positionX, positionY, velocityX, velocityY, radius, getDefaultOrientation(), getDefaultPlanetoidMass(),
				getDefaultMaxVelocity(), getDefaultPlanetoidDensity(),totalTraveledDistance);
	}
	
	/**
	 * Initializes a new planetoid with all it's parameters set to default.
	 * 
	 * @effect 	The planetoid is initialized with the default values.
	 *         	@see implementation
	 */
	public Planetoid(){
		this(getDefaultPositionX(),getDefaultPositionY(),getDefaultVelocityX(),getDefaultVelocityY(),getDefaultMinorPlanetRadius(),getDefaultPlanetoidTraveledDistance());
	}
	
	
	/// PROPERTIES ///

	protected double initial_radius;
	protected double totalTraveledDistance;
	
	
	/// DEFAULTS ///
	
	/**
	 * A method that returns the default planetoid density.
	 * 
	 * @return 	The density.
	 * 			@see implementation
	 */
	private static double getDefaultPlanetoidDensity(){
		return 0.917E12;
	}
	
	/**
	 * Return the default Planetoid mass.
	 * 
	 * @return 	The default mass.
	 * 			@see implementation
	 */
	private static double getDefaultPlanetoidMass() {
		return MassFormula(getDefaultMinorPlanetRadius(), getDefaultPlanetoidDensity());
	}
	
	/**
	 * Return the default traveled distance.
	 * 
	 * @return 	The default traveled distance.
	 * 			@see implementation
	 */
	private static double getDefaultPlanetoidTraveledDistance(){
		return 0;
	}
	
	
	/// GETTERS ///
	
	/**
	 * Return the initial radius of the planetoid
	 * 
	 * @return 	The initial radius.
	 * 			@see implementation
	 */
	protected final double getPlanetoidInitialRadius(){
		return initial_radius;
	}
	
	/**
	 * Return the total traveled distance of the planetoid.
	 * 
	 * @return 	The distance.
	 * 			@see implementation
	 */
	public double getPlanetoidTotalTraveledDistance(){
		return totalTraveledDistance;
	}
	
	
	/// SETTERS ///
	
	/**
	 * Set the planetoids density to a given density.
	 * 
	 * @param 	density
	 * 			The planetoids new density.
	 * 
	 * @post 	If the density is valid, the new density will be equal to the given density. Otherwise, it will be equal to the default planetoid density.
	 * 		 	@see implementation
	 */
	protected void setEntityDensity(double density){
		if (isValidDensity(density))
			this.density = density;
		else
			this.density = getDefaultPlanetoidDensity();
	}
	
	
	/**
	 * Set the planetoids initial radius to a given radius.
	 * 
	 * @param 	radius
	 * 			The planetoids new initial radius.
	 * 
	 * @post 	The new initial radiuswill be equal to the given radius.
	 * 		  | new.getPlanetoidInitialRadius() == radius.
	 */
	protected void setPlanetoidInitialRadius(double radius){
		initial_radius = radius;
	}
	
	/**
	 * Set the planetoids total traveled distance to a given value.
	 * 
	 * @param 	totalTraveledDistance
	 * 			The planetoids new totalTraveledDistance.
	 * 
	 * @post 	The new totalTraveledDistance will be equal to the given value.
	 * 		  | new.getPlanetoidTotalTraveledDistance() == totalTraveledDistance
	 * @throws  IllegalArgumentException if the given value isn't a valid one
	 * 			@see implementation 
	 */
	protected void setPlanetoidTotalTraveledDistance(double totalTraveledDistance){
		if (isValidTraveledDistance(totalTraveledDistance))
			this.totalTraveledDistance = totalTraveledDistance;
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Update the planetoids radius.
	 * 
	 * @effect 	The new radius is 10E-6 times the totalTraveledDistance subtracted from the initialRadius. 
	 * 			If this new radius is valid, it will be set and the mass will be updated.
	 * 			When it's not, the planetoid will be terminated.
	 * 			@see implementation
	 */
	private void updatePlanetoidRadius(){
		double init_radius = getPlanetoidInitialRadius();
		double totalTraveledDistance = getPlanetoidTotalTraveledDistance();
		double new_radius = init_radius - 0.000001*totalTraveledDistance;
		
		if (isValidRadius(new_radius)){
			setEntityRadius(new_radius);
			setEntityMass(MassFormula(new_radius, getEntityDensity()));
		}
		else
			Terminate();
	}
	
	
	/// CHECKERS ///
	
	/**
	 * Checks if a density is valid for this planetoid.
	 * 
	 * @param 	density
	 * 			The density that has to be checked.
	 * 
	 * @return 	The boolean that checks the density.
	 * 			@see implementation
	 */
	protected boolean isValidDensity(double density){
		return (density == getDefaultPlanetoidDensity());
	}

	/**
	 * Checks if a traveled distance is valid for this planetoid.
	 * 
	 * @param 	totalTraveledDistance
	 * 			The totalTraveledDistance that has to be checked.
	 * 
	 * @return 	The boolean that checks the totalTraveledDistance.
	 * 			@see implementation
	 */
	protected boolean isValidTraveledDistance(double totalTraveledDistance){
		return (totalTraveledDistance>=0 && totalTraveledDistance >= getDefaultPlanetoidTraveledDistance());
	}
	
	
	/// HELP FUNCTIONS ///

	/**
	 * Divides the planetoid in two children (asteroids) who will fly in a straight line away from eachother.
	 * 
	 * @effect 	Two children will be made via the asteroid constructor. They will have followong properties: 
	 * 			they have anti-supplementary orientations( one of the two is chosen random).
	 * 			the total velocity is 1.5 times the velocity of the planetoid.
	 * 			The radius is 0.5 times the radius of the planetoid.
	 * 			@see implementation
	 * @effect 	The first child will be moved for 0.00001 seconds after it's placed in the world, to avoid collision with the second child.
	 * 			@see implementation
	 * @effect 	If one of the children cannot be placed in the world, it will be terminated.
	 * 			@see implementation
	 */
	private void planetoidDivision() {
		// Calculating the child properties
		double total_child_velocity = 1.5 * getEuclidianDistance(getEntityVelocityX(), getDefaultVelocityY());
		double child_radius = getEntityRadius() / 2;
		double child1_orientation = Math.random() * Math.PI;
		double child2_orientation = (child1_orientation + Math.PI);

		double child1_velocityX = total_child_velocity * Math.cos(child1_orientation);
		double child1_velocityY = total_child_velocity * Math.sin(child1_orientation);
		double child2_velocityX = total_child_velocity * Math.cos(child2_orientation);
		double child2_velocityY = total_child_velocity * Math.sin(child2_orientation);

		double child1_positionX = getEntityPositionX() + child_radius * Math.cos(child1_orientation);
		double child1_positionY = getEntityPositionY() + child_radius * Math.sin(child1_orientation);
		double child2_positionX = getEntityPositionX() + child_radius * Math.cos(child2_orientation);
		double child2_positionY = getEntityPositionY() + child_radius * Math.sin(child2_orientation);

		// Making the children
		Asteroid child1 = new Asteroid(child1_positionX, child1_positionY, child1_velocityX, child1_velocityY, child_radius);
		Asteroid child2 = new Asteroid(child2_positionX, child2_positionY, child2_velocityX, child2_velocityY, child_radius);
		child1.setEntityOrientation(child1_orientation);
		child2.setEntityOrientation(child2_orientation);

		// Adding them to the world
		World world = getEntityWorld();
		getEntityWorld().removeEntityFromWorld(this);
		
		try {
			world.addEntityToWorld(child1);
			child1.move(0.00001);
		} catch (IllegalArgumentException illegalArgumentException) {
			child1.Terminate();
		}
		
		try {
			world.addEntityToWorld(child2);
		} catch (IllegalArgumentException illegalArgumentException) {
			child2.Terminate();
		}
	}


	/// MOVE ///
	
	/**
	 * Move the planetoid for the given time moveTime.
	 * 
	 * @param 	moveTime
	 * 			The time the planetoid has to move.
	 * 
	 * @post 	After moveTime, the planetoids position will be set on moveTime times its velocity.
	 * 			@see implementation
	 * 
	 * @effect  The planetoid will be shrunk for moveTime seconds.
	 * 		  | shrink(moveTme)
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the given time is negative.
	 * 		  | moveTime < 0
	 */
	protected void move(double moveTime) {
		if (moveTime < 0)
			throw new IllegalArgumentException();
		
		shrink(moveTime);
		
		double velocityX = getEntityVelocityX();
		double velocityY = getEntityVelocityY();

		final double collidingPositionX = getEntityPositionX() + velocityX * moveTime;
		final double collidingPositionY = getEntityPositionY() + velocityY * moveTime;
		
		setPositionWithoutChecking(collidingPositionX, collidingPositionY);
	}
	
	/**
	 * Shrink a planetoid for a given time.
	 * 
	 * @param 	time
	 * 			The time the planetoid has to shrink.
	 * 
	 * @effect	The traveled distance is incremented by the distance it will travel in time seconds. 
	 * 			@see implementation
	 * @effect  The radius will be updated
	 * 		  | updatePlanetoidRadius()
	 */
	private void shrink(double time){
		double x_distance = getEntityVelocityX()*time;
		double y_distance = getEntityVelocityY()*time;
		double total_traveled_distance = getPlanetoidTotalTraveledDistance() + getEuclidianDistance(x_distance,y_distance);
		
		setPlanetoidTotalTraveledDistance(total_traveled_distance);
		
		updatePlanetoidRadius();
	}

	
	/// COLLISIONS ///
	
	/**
	 * A method that resolves the collision between a ship and a minor planet.
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
	 * @effect 	When they collide, the ship is teleported to a random position in the world, 
	 * 			if this position is occupied, the ship will be terminated.
	 * 			@see implementation
	 */
	protected void entityAndShipCollide(Ship ship,double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener){
		World world = ship.getEntityWorld();

		double radius = ship.getEntityRadius();
		double random_x = radius+Math.random()*(world.getWorldWidth()-radius);
		double random_y = radius+Math.random()*(world.getWorldHeight()-radius);
		
		try {
			ship.setEntityPosition(random_x, random_y);
		} catch (IllegalArgumentException illegalArgumentException) {
			double collisionPositionX = collisionPosition[0];
			double collisionPositionY = collisionPosition[1];
		
			if (collisionListener != null)
				collisionListener.objectCollision(this, ship,collisionPositionX,collisionPositionY);
			ship.Terminate();
		}
	}
	
	
	/// TERMINATE ///

	/**
	 * Terminate the planetoid.
	 * 
	 * @post	The planetoids state will be set to Terminated. If the planetoid was in a world, it will 
	 * 			be removed from this world. 
	 * 			@see implementation
	 * 
	 * @effect 	If the planetoid was big enough, it will be divided in two asteroids.
	 * 		  | if (getEntityRadius >= 30)
	 * 		  |   planetoidDivision()
	 */
	public void Terminate() {
		if (isEntityFree())
			setEntityState(State.TERMINATED);

		else if (isEntityInWorld()) {
			if (getEntityRadius() >=30)
				planetoidDivision();
			else
				getEntityWorld().removeEntityFromWorld(this);
			
			setEntityState(State.TERMINATED);
		}
	}
	
}

