package asteroids.model;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.*;
 
/**
 * A class that describes and modifies all the entities. An entity can be a ship, bullet or MinorPlanet.
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
public abstract class Entity {

	/// CONSTRUCTOR /// 
	
	/**
	 * Initialize an entity with the given parameters. 
	 * 
	 * @param 	positionX
	 * 			The x-value of the entity's position.
	 * @param 	positionY
	 * 			The y-value of the entity's position.
	 * @param 	velocityX
	 * 			The x-value of the entity's velocity.
	 * @param 	velocityY
	 * 			The y-value of the entity's velocity.
	 * @param 	radius
	 * 			The radius of the entity.
	 * @param 	orientation
	 * 			The orientation of the entity.
	 * @param 	mass
	 * 			The mass of the entity.
	 * @param 	maxvelocity
	 * 			The maximum total velocity of the entity.
	 * @param 	density
	 * 			The density of the entity.
	 * 
	 * @effect 	The properties will be set on their given values.
	 * 			@see implementation 			
	 **/
	protected Entity(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density) {
		setEntityRadius(radius);
		setEntityOrientation(orientation);
		setEntityMaxVelocity(maxVelocity);
		setEntityPosition(positionX, positionY);
		setEntityVelocity(velocityX, velocityY);
		setEntityDensity(density);
		setEntityMass(mass);
	}

	
	/// BASIC PROPERTIES ///

	protected double density;
	protected double mass;
	protected double max_velocity;
	protected double orientation;
	protected Position position = new Position();
	protected double radius;
	protected Velocity velocity = new Velocity();

	
	/// CONSTANTS ///
	
	/**
	 * A constant that is used to "correct" the errors that occur when using double values.
	 */
	protected final static double BETA = 1.01;

	/**
	 * A constant that is used to "correct" the errors that occur when using double values.
	 */
	protected final static double GAMMA = 0.01;
	
	/**
	 * A constant that is used to "correct" the errors that occur when using double values.
	 */
	protected final static double OMEGA = 0.99;
	
	/**
	 * The speed of the light which can and may never be exceeded. The speed of light will act as a maximum limit for the 
	 * velocity when the entity is not limited by an upper bound in its total velocity.
	 */
	protected final static double SPEED_OF_LIGHT = 300000;
	
	
	/// DEFAULTS ///

	/**
	 * Returns the default maximum velocity.
	 * 
	 * @return 	The default maximum velocity.
	 * 			@see implementation
	 */
	protected static double getDefaultMaxVelocity() {
		return SPEED_OF_LIGHT;
	}

	/**
	 * Return the default orientation.
	 * 
	 * @return 	The default orientation.
	 * 			@see implementation
	 */
	@Immutable
	protected static double getDefaultOrientation() {
		return 0;
	}

	/**
	 * Return the default x-position.
	 * 
	 * @return 	The default x-position.
	 * 			@see implementation
	 */
	@Immutable
	protected static double getDefaultPositionX() {
		return 0;
	}

	/**
	 * Return the default y-position.
	 * 
	 * @return 	The default y-position.
	 * 			@see implementation
	 */
	@Immutable
	protected static double getDefaultPositionY() {
		return 0;
	}

	/**
	 * Return the default x-velocity.
	 * 
	 * @return 	The default x-velocity.
	 * 			@see implementation
	 */
	@Immutable
	protected static double getDefaultVelocityX() {
		return 0;
	}

	/**
	 * Return the default y-velocity.
	 * 
	 * @return 	The default y-velocity.
	 * 			@see implementation
	 */
	@Immutable
	protected static double getDefaultVelocityY() {
		return 0;
	}

	
	/// GETTERS ///
	
	/**
	 * Calculate the position, if there is one, of the collision between two entities.
	 * 
	 * @param  	otherEntity
	 *         	The other entity.
	 *            
	 * @return 	Null if the time until the collision is positive infinity.
	 * 		  | if getTimeToCollision(entity) == Double.POSITIVE_INFINITY
	 * 		  |   result == null
	 * @return	The position of collision, which  is calculated by moving the ships
	 *         	at their respective velocities for the time until collision. 
	 *         	delta_x is the difference of the x-coordinates of the two ships when they
	 *         	are on their collision positions. delta_y is the difference of the
	 *         	y-coordinates at the same moment. 
	 *         	omega is the angle between the delta_y (vertical) and the connection
	 *         	between the two centers.
	 *			The actual position_colliside will be calculated using omega, the radius and the center
	 *			of the entity the method is invoked on (this) at the moment it will collide.
	 *        	@see implementation
	 */
	public double[] getCollisionPosition(Entity entity) {
		double position1X = getEntityPositionX();
		double position1Y = getEntityPositionY();
		double position2X = entity.getEntityPositionX();
		double position2Y = entity.getEntityPositionY();
		
		double velocity1X = getEntityVelocityX();
		double velocity1Y = getEntityVelocityY();
		double velocity2X = entity.getEntityVelocityX();
		double velocity2Y = entity.getEntityVelocityY();
		
		double radius1 = getEntityRadius();

		double time_till_overlapping = getTimeToCollision(entity);

		if (time_till_overlapping == Double.POSITIVE_INFINITY)
			return null;

		else {
			double collidingPosition1X = position1X + velocity1X * time_till_overlapping;
			double collidingPosition1Y = position1Y + velocity1Y * time_till_overlapping;
			double collidingPosition2X = position2X + velocity2X * time_till_overlapping;
			double collidingPosition2Y = position2Y + velocity2Y * time_till_overlapping;
			
			double delta_x = (collidingPosition2X - collidingPosition1X);
			double delta_y = (collidingPosition2Y - collidingPosition1Y);

			double omega;

			if (delta_x > 0)
				omega = Math.atan(delta_y / delta_x);
			
			else if (delta_x == 0 && delta_y > 0) 
				omega = Math.PI / 2;
			
			else if (delta_x == 0 && delta_y < 0) 
				omega = 3 * Math.PI / 2;
			
			else
				omega = Math.atan(delta_y / delta_x) + Math.PI;

			double[] position_collide = { collidingPosition1X + radius1 * Math.cos(omega),
					collidingPosition1Y + radius1 * Math.sin(omega) };
			
			return position_collide;
		}
	}

	/**
	 * Calculate the distance between two entities.
	 * 
	 * @param	otherEntity
	 *			The other entity.
	 *            
	 * @return	If this (the entity the method is invoked on) and otherEntity are the
	 *			same entity, the distance between equals zero. 
	 *		  | if (this.equals(entity))
	 *		  | result == 0
	 * @return	The distance between the two entities if they're not the same. This
	 *			is calculated by subtracting the sum of the radii of the entities
	 *			from the distance between the centers.  
	 *			@see implementation
	 */
	public double getDistanceBetween(Entity entity) {
		if (this.equals(entity))
			return 0;

		final double position1X = getEntityPositionX();
		final double position1Y = getEntityPositionY();
		final double position2X = entity.getEntityPositionX();
		final double position2Y = entity.getEntityPositionY();
		
		final double radius1 = getEntityRadius();
		final double radius2 = entity.getEntityRadius();
		final double total_radius = radius1 + radius2;
		
		final double delta_x = Math.abs(position1X - position2X);
		final double delta_y = Math.abs(position1Y - position2Y);
		final double distance_centers = getEuclidianDistance(delta_x, delta_y);
		final double distance = distance_centers - total_radius;

		return distance;
	}

	/**
	 * Returns the entity's density.
	 * 
	 * @return 	The density.
	 * 			@see implementation
	 */
	protected double getEntityDensity() {
		return density;
	}

	/**
	 * Returns the mass of the entity.
	 * 
	 * @return 	the mass.
	 * 			@see implementation
	 */
	public double getEntityMass() {
		return mass;
	}

	/**
	 * Returns the entity's maximum total velocity.
	 * 
	 * @return 	The maximum velocity.
	 * 			@see implementation
	 */
	protected double getEntityMaxVelocity() {
		return max_velocity;
	}

	/**
	 * Returns the entity's orientation.
	 * 
	 * @return 	The orientation.
	 * 			@see implementation
	 */
	public double getEntityOrientation() {
		return orientation;
	}
	
	/**
	 * Returns the entity's position.
	 * 
	 * @return 	The position of the entity.
	 * 			@see implementation
	 */
	public double[] getEntityPosition() {
		return position.getPositionArray();
	}

	/**
	 * Returns the entity's x-position value.
	 * 
	 * @return 	The x-position of the position.
	 * 			@see implementation
	 */
	public double getEntityPositionX() {
		return position.getPositionX();
	}

	/**
	 * Returns the entity's y-position value.
	 * 
	 * @return 	The y-position of the position.
	 * 			@see implementation
	 */
	public double getEntityPositionY() {
		return position.getPositionY();
	}

	/**
	 * Returns the entity's radius.
	 * 
	 * @return 	The radius.
	 * 			@see implementation
	 */
	public double getEntityRadius() {
		return radius;
	}

	/**
	 * Returns the entity's velocity.
	 * 
	 * @return 	The velocity of the entity.
	 * 			@see implementation
	 */
	public double[] getEntityVelocity() {
		return velocity.getVelocityArray();
	}

	/**
	 * Returns the entity's x-velocity value.
	 * 
	 * @return 	The x-value of the velocity.
	 * 			@see implementation
	 */
	public double getEntityVelocityX() {
		return velocity.getVelocityX();
	}

	/**
	 * Returns the entity's y-velocity value.
	 * 
	 * @return 	The y-value of the velocity.
	 * 			@see implementation
	 */
	public double getEntityVelocityY() {
		return velocity.getVelocityY();
	}
	
	/**
	 * returns the world where the entity's in.
	 * 
	 * @return 	The world.
	 * 			@see implementation
	 */
	public World getEntityWorld() {
		return world;
	}
	
	/**
	 * Returns the hypotenuse of the triangle.
	 * 
	 * @param 	a
	 *          The horizontal/vertical side of the triangle.
	 * @param 	b
	 *          The vertical/horizontal side of the triangle.
	 * 
	 * @return	The Euclidian distance: the square root of the sum of a squared and b squared. 
	 *			@see implementation
	 */
	protected static double getEuclidianDistance(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	/**
	 * Returns the position where the entity will collide with the boundary.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @return	Null if the time till collision equals POSITIVE_INFINITY.
	 * 		  | if getTimeCollisionBoundary() == Double.POSITIVE_INFINITY
	 * 		  |   result == null
	 * @return	The collision position of the given entity when it's colliding with a boundary of the world.
	 * 			This would be after the given entity moved over a time-laps of getTimeCollisionBoundary().
	 *		  | move(getTimeCollisionBoundary)
	 * 		  | result[0] == (getEntityPosition + radius) || 
	 * 		  |   result[1] == (getEntityPosition + radius) || 
	 * 		  |   result[0] == (getEntityPosition - radius) || 
	 * 		  |   result[1] == (getEntityPosition - radius)  
	 * 
	 * @throws	IllegalAccesError, it should be impossible, once in the else-statement, to not-touch with one of the
	 * 			boundaries of the world. When this is the case, the IlligalAccesError will be thrown.
	 * 			@see implementation
	 */
	public double[] getPositionCollisionBoundary() {
		double time = getTimeCollisionBoundary();
		double collidingPositionX = 0;
		double collidingPositionY = 0;

		if (time == Double.POSITIVE_INFINITY)
			return null;
		
		else {
			double positionX = getEntityPositionX();
			double positionY = getEntityPositionY();
		
			double velocityX = getEntityVelocityX();
			double velocityY = getEntityVelocityY();
			
			double width = getEntityWorld().getWorldWidth();
			double height = getEntityWorld().getWorldHeight();
			
			double radius = getEntityRadius();
			
			boolean collision_happened = false;
			collidingPositionX = Math.abs(positionX + time * velocityX);
			collidingPositionY = Math.abs(positionY + time * velocityY);

			// Right boundary
			if ((collidingPositionX + OMEGA * radius) <= width && width <= (collidingPositionX + BETA * radius)){
				collidingPositionX += radius;
				collision_happened = true;
			}
			// Left boundary
			if ((collidingPositionX - BETA * radius) <= 0 && 0 <= (collidingPositionX - OMEGA * radius)){
				collidingPositionX -= radius;
				collision_happened = true;
			}
			// Upper boundary
			if ((collidingPositionY + OMEGA * radius) <= height && height <= (collidingPositionY + BETA * radius)){
				collidingPositionY += radius;
				collision_happened = true;
			}
			// Lower boundary
			if ((collidingPositionY - BETA * radius) <= 0 && 0 <= (collidingPositionY - OMEGA * radius)){
				collidingPositionY -= radius;
				collision_happened = true;
			}
			
			// It should not be possible to get to this state, but if an entity does we throw an IllegalAccessError.
			else if (!collision_happened) {
				throw new IllegalAccessError();
			}
		}
		
		double[] new_position = { collidingPositionX, collidingPositionY };
		return new_position;
	}

	/**
	 * returns the state the entity is in.
	 * 
	 * @return 	The state.
	 * 			@see implementation
	 */
	private State getState() {
		return state;
	}

	/**
	 * Get the minimum time until the entity collides with a boundary.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @return 	Positive infinity if the entity has no world or a proper state. 
	 * 		  | if (!isEntityInWorld() && hasEntityProperState())
			  | result == Double.POSITIVE_INFINITY
	 * @return 	The time till collision with one of the boundaries of the world.
	 * 			If the entity has no velocity, the entity will not collide with a boundary. In this case 
	 * 			POSITIVE_INFINITY will be returned.
	 *			@see implementation
	 */
	public double getTimeCollisionBoundary() {
		if (! isEntityInWorld() && hasEntityProperState())
			return Double.POSITIVE_INFINITY;
		
		else {
			double positionX = getEntityPositionX();
			double positionY = getEntityPositionY();
		
			double velocityX = getEntityVelocityX();
			double velocityY = getEntityVelocityY();
			
			double width = getEntityWorld().getWorldWidth();
			double height = getEntityWorld().getWorldHeight();
			
			double radius = getEntityRadius();

			double distanceTillRightBoundary = (width - (positionX + radius));
			double distanceTillLeftBoundary = (positionX - radius);
			double distanceTillUpperBoundary = (height - (positionY + radius));
			double distanceTillLowerBoundary = (positionY - radius);

			double timeCollisionHorizontal = Double.POSITIVE_INFINITY;
			double timeCollisionVertical = Double.POSITIVE_INFINITY;
			
			// Calculate the time, if so, till the collision with each boundary.
			if (velocityX > 0)
				timeCollisionHorizontal = Math.abs(distanceTillRightBoundary / velocityX);
			
			else if (velocityX < 0)
				timeCollisionHorizontal = Math.abs(distanceTillLeftBoundary / velocityX);
			
			if (velocityY > 0)
				timeCollisionVertical = Math.abs(distanceTillUpperBoundary / velocityY);
			
			else if (velocityY < 0)
				timeCollisionVertical = Math.abs(distanceTillLowerBoundary / velocityY);

			// Return the time until the entity will collide with the first boundary.
			// If the entity has no velocity, POSITIVE_INFINITY will be returned.
			return Math.min(timeCollisionHorizontal, timeCollisionVertical);
		}
	}

	/**
	 * Calculate the time until, if ever, the first collision between two entities will take place.
	 * 
	 * @param	otherEntity
	 *			The other entity.
	 * 
	 * @post	The amount of seconds until the collision will take place is calculated. 
	 * 			This means that if the two entities travel this amount of time at their
	 * 			respective velocity, the distance between them will equal 0 (they collide). 
	 *		  | move(getTimeToCollision(Entity otherEntity)) 
	 *		  | otherEntity.move(getTimeToCollision(Entity otherEntity))
	 *		  | this.getDistanceBetween(otherEntity) == 0
	 * 
	 * @return	If the collision won't take place, Double.POSITIVE_INFINITY will be
	 *			returned. If the collision happens, the time until it happens is returned.
	 *         
	 * @throws	IllegalArgumentException if the two entities overlap.
	 *		  | (this.overlap(otherEntity))
	 */
	public double getTimeToCollision(Entity entity) {
		double position1X = getEntityPositionX();
		double position1Y = getEntityPositionY();
		double position2X = entity.getEntityPositionX();
		double position2Y = entity.getEntityPositionY();

		double velocity1X = getEntityVelocityX();
		double velocity1Y = getEntityVelocityY();
		double velocity2X = entity.getEntityVelocityX();
		double velocity2Y = entity.getEntityVelocityY();

		double radius1 = getEntityRadius();
		double radius2 = entity.getEntityRadius();
		double total_radius = (radius1 + radius2);

		double delta_rX = position2X - position1X;
		double delta_rY = position2Y - position1Y;

		double delta_vX = velocity2X - velocity1X;
		double delta_vY = velocity2Y - velocity1Y;

		double delta_r_r = Math.pow(delta_rX, 2) + Math.pow(delta_rY, 2);
		double delta_v_v = Math.pow(delta_vX, 2) + Math.pow(delta_vY, 2);
		double delta_v_r = (delta_rX * delta_vX + delta_rY * delta_vY);

		double d = Math.pow(delta_v_r, 2) - delta_v_v * (delta_r_r - Math.pow(total_radius, 2));

		if (this.overlap(entity))
			throw new IllegalArgumentException();
		
		if ((!isEntityInWorld() && hasEntityProperState())
				|| (!entity.isEntityInWorld() && entity.hasEntityProperState()))
			return Double.POSITIVE_INFINITY;

		else if (delta_v_r >= 0)
			return Double.POSITIVE_INFINITY;

		else if (d <= 0)
			return Double.POSITIVE_INFINITY;

		else
			return Math.abs((delta_v_r + Math.sqrt(d)) / delta_v_v);
	}
	
	
	/// SETTERS ///

	/**
	 * Set the density of the entity.
	 * 
	 * @param 	density
	 * 			The entity's new density.
	 * 
	 * @post 	If the density is valid, the new density will be equal to the given density. Otherwise, it will be equal to the default entity density.
	 * 		 	@see implementation 
	 */
	protected abstract void setEntityDensity(double density);

	/**
	 * set the entity's state to NO_WORLD.
	 * 
	 * @pre 	The entity is not terminated.
	 * 			@see implmentation
	 * 
	 * @effect 	The entity's world will be set on null and the state to NO_WORLD.
	 * 			@see implementation
	 */
	protected void setEntityFree() {
		assert (!isEntityTerminated());
		setEntityState(State.NO_WORLD);
		setEntityWorld(null);
	}

	/**
	 * Set the entity's state to IN_WORLD.
	 * 
	 * @param 	world
	 * 			The world the entity will be set in.
	 * 
	 * @pre 	The entity is not terminated.
	 * 			@see implementation
	 * 
	 * @effect 	The state changes to IN_WORLD and its world will be set to the given world.
	 * 			@see implementation
	 */
	protected void setEntityInWorld(World world) {
		assert (!isEntityTerminated());
		setEntityState(State.IN_WORLD);
		setEntityWorld(world);
	}

	/**
	 * Set the mass of the entity.
	 * 
	 * @param 	mass
	 * 			The entity's new mass.
	 * 
	 * @post 	The new mass will be equal to the given mass. If the mass wasn't valid, it will be set to a default mass .
	 * 			@see implementation
	 */
	protected abstract void setEntityMass(double mass);

	/**
	 * Set the maximum total velocity.
	 * 
	 * @param 	newMaxVelocity
	 * 			The new maximum velocity.
	 * 
	 * @post 	The maximum velocity will be equal to the given velocity, when it's not valid, 
	 * 			it will be set to the speed of the light.
	 * 			@see implementation
	 */
	protected void setEntityMaxVelocity(double newMaxVelocity) {
		if ((0 < newMaxVelocity) && (newMaxVelocity < SPEED_OF_LIGHT))
			max_velocity = newMaxVelocity;

		else
			max_velocity = SPEED_OF_LIGHT;
	}
	
	/**
	 * Gives the entity a new orientation.
	 * 
	 * @param 	orientation
	 *          The new orientation in radians.
	 * 
	 * @pre 	The given orientation is a valid orientation for the entity. 
	 * 			@see implementation
	 * 
	 * @post 	The new orientation will be equal to the given orientation.
	 *        | new.getEntityOrientation() == orientation
	 */
	protected void setEntityOrientation(double orientation) {
		assert isValidOrientation(orientation);

		this.orientation = orientation;
	}
	
	/**
	 * Set the position of an entity.
	 * 
	 * @param 	positionX
	 * 			The new x-position.
	 * @param 	positionY
	 * 			The new y-position.
	 * 
	 * @post	The position will be set on the new position.
	 * 		  | new.getEntityPositionX() == positionX
	 * 		  | new.getEntityPositionY() == positionY
	 * 
	 * @throws 	IllegalArgumentException
	 * 			if the position is not valid.
	 * 			@see implementation
	 */
	protected void setEntityPosition(double positionX, double positionY) throws IllegalArgumentException {
		if (!isValidPosition(positionX, positionY))
			throw new IllegalArgumentException();

		position.setPositionX(positionX);
		position.setPositionY(positionY);
	}

	/**
	 * Set the radius of an entity.
	 * 
	 * @param 	radius
	 * 			The new radius.
	 * 
	 * @post	The new radius will be equal to the given radius.
	 * 		  | new.getEntityRadius() == radius
	 * 
	 * @throws 	IllegalArgumentException
	 * 			if the radius is not valid.
	 * 			@see implementation
	 */
	protected void setEntityRadius(double radius) throws IllegalArgumentException {
		if (isValidRadius(radius))
			this.radius = radius;
		
		else
			throw new IllegalArgumentException();
	}

	/**
	 * set the entity's state.
	 * 
	 * @param 	state
	 * 			The new entity's state.
	 * 
	 * @post 	The new state will be equal to the given state.
	 * 		  | new.getState() == state
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the given state is null.
	 * 			@see implementation
	 */
	protected void setEntityState(State state) throws IllegalArgumentException {
		if (state == null)
			throw new IllegalArgumentException();
		
		else
			this.state = state;
	}

	/**
	 * Set the velocity of the entity on the given velocity.
	 * 
	 * @param 	velocityX
	 * 			The new x-value of the velocity.
	 * @param 	velocityY
	 * 			the new y-value of the velocity.
	 * 
	 * @post 	The new velocity will be equal to the given velocity
	 * 		  | new.getEntityVelocityX() == velocityX
	 * 		  | new.getEntityVelocityY() == velocityY
	 * @post 	If one or both of the given parameters is not a number, the respective parameter is set to zero.
	 * 		  | if (!isValidVelocity(velocityX, velocityY))
	 * 		  |   new.getEntityVelocityX() == 0 ||
	 * 		  |   new.getEntityVelocityY() == 0
	 * @post 	If the total velocity is greater than the maximum total velocity. The maximum velocity will
	 * 			be mapped with the orientation.
	 * 			@see implementation.
	 */
	protected void setEntityVelocity(double velocityX, double velocityY) {
		if (!isValidVelocity(velocityX, velocityY)) {
			if (Double.isNaN(velocityX))
				velocityX = 0;
			if (Double.isNaN(velocityY))
				velocityY = 0;

			if (getEuclidianDistance(velocityX, velocityY) > getEntityMaxVelocity()) {
				double orientation = getEntityOrientation();

				velocityX = Math.cos(orientation) * getEntityMaxVelocity();
				velocityY = Math.sin(orientation) * getEntityMaxVelocity();
			}
		}

		velocity.setVelocityX(velocityX);
		velocity.setVelocityY(velocityY);
	}

	/**
	 * Set a world to the entity.
	 * 
	 * @param 	world
	 * 			The new world.
	 * 
	 * @post 	The new world will be equal to the given world.
	 * 		  | new.getEntityWorld() == world
	 */
	protected void setEntityWorld(World world) {
		this.world = world;
	}

	/**
	 * Set the position without checking if the given position is a valid position.
	 * 
	 * @param 	x
	 * 			The new x-position.
	 * @param 	y
	 * 			The new y-position.
	 * 
	 * @post 	The new position will be equal to the given values.
	 * 		  | new.getEntityPosition == {x, y}
	 */
	protected void setPositionWithoutChecking(double positionX, double positionY) {
		position.setPositionX(positionX);
		position.setPositionY(positionY);
	}


	/// CHECKERS ///
	
	/**
	 * Checks if the entity can have this world as its world.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	world
	 * 			The world that has to be checked.
	 * 
	 * @return 	False if the entity is a ship and it does not fit in the world.
	 *		  | if (!entityFitsInWorld(world))
	 *		  |   result == false
	 * @return 	False if the entity already has a world.
	 * 		  | if (getEntityWorld() != null)
	 *		  |   result == false
	 * @return 	False if the entity is a bullet and it doesn't fit in the world or it's loaded on a ship.
	 *		  | if (this instanceof Bullet && (((Bullet) this).getBulletShip() != null))
	 *		  |   result == false
	 * @return 	False if the entity or the world is terminated.
	 *		  | if (isEntityTerminated() || world.isWorldTerminated())
	 *		  |   result == false
	 * @return  True in all other cases.
	 * 		  | else
	 * 		  |   result == true
	 */
	protected boolean canHaveAsWorld(World world){
		if (world == null)
			return false;
		
		else if (!entityFitsInWorld(world))
			return false;
		
		else if (getEntityWorld() != null)
			return false;

		// If the bullet belongs to a ship (which means the bullet is in a ship, and not in the world) false will be returned.
		else if (this instanceof Bullet && (((Bullet) this).getBulletShip() != null))
			return false;
		
		// An entity who is in the terminated state, cannot be in a world and a terminated world cannot have any entities. .
		else if (isEntityTerminated() || world.isWorldTerminated())
			return false;
		
		else
			return true;	
	}

	/**
	 * Checks if an entity has a proper state.
	 * 
	 * @return 	The boolean that checks whether the entity's state is IN_WORLD, NO_WORLD or Terminated.
	 * 			@see implementation
	 */
	protected boolean hasEntityProperState() {
		return (isEntityInWorld() ^ isEntityFree() ^ isEntityTerminated());
	}

	/**
	 * Checks if an entity has the NO_WORLD state.
	 * 
	 * @return 	The boolean that checks if the entity has the NO_WORLD state.
	 * 			@see implementation
	 */
	protected boolean isEntityFree() {
		return (getState() == State.NO_WORLD);
	}

	/**
	 * Checks if an entity has the IN_WORLD state.
	 * 
	 * @return 	The boolean that checks if the entity has the IN_WORLD state.
	 * 			@see implementation
	 */
	protected boolean isEntityInWorld() {
		return (getState() == State.IN_WORLD);
	}

	/**
	 * Checks if an entity has the Terminated state.
	 * 
	 * @return 	The boolean that checks if the entity has the Terminated state.
	 * 			@see implementation
	 */
	public boolean isEntityTerminated() {
		return (getState() == State.TERMINATED);
	}

	/**
	 * Checks if a given density is valid.
	 * 
	 * @param 	density
	 * 			The density that has to be checked.
	 * 
	 * @return 	The boolean that checks whether the given density is valid or not.
	 * 			@see implementation 
	 */
	protected abstract boolean isValidDensity(double density);

	/**
	 * Checks if a given mass is valid.
	 * 
	 * @param 	mass
	 * 			The mass that has to be checked.
	 * 
	 * @return 	The boolean that checks of the mass is valid.
	 * 			@see implementation
	 */
	protected abstract boolean isValidMass(double mass);

	/**
	 * Checks if a given orientation is valid.
	 * 
	 * @param 	orientation
	 * 			The orientation that has to be checked.
	 * 
	 * @return	the boolean that checks if an orientation is valid.
	 * 			@see implementation
	 */
	protected boolean isValidOrientation(double orientation) {
		return ((0 <= orientation) && (orientation < 2 * Math.PI));
	}

	/**
	 * Checks whether a position is valid or not.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	positionX
	 * 			The x-value of the position that has to be checked.
	 * @param 	positionY
	 * 			the y-value of the position that has to be checked.
	 * 
	 * @return  false if the x- or y-value of the position is not a number.
	 * 			@see implementation
	 * @return 	true if the ship belongs to a world,the boolean is still true and the entity fits in the world.
	 * 			@see implementation
	 */
	private boolean isValidPosition(double positionX, double positionY) {
		boolean Boolean = true;
		
		// Check if the given positions have a valid number.
		if ((Double.isNaN(positionX)) || (Double.isNaN(positionY)))
			Boolean = false;
		
		// Check if the entity with the new position would not overlap another entity, or the boundaries of the world.
		// To check this, we have to set the new positions to the entity and check if the entity would be overlapping. 
		// At the end of this statement, we have to make sure that position of the entity is back to it's old position, 
		// because an isValid function shouldn't change the values of its properties.
		if ((getEntityWorld() != null) && Boolean == true) {
			double oldPositionX = getEntityPositionX();
			double oldPositionY = getEntityPositionY();
			
			setPositionWithoutChecking(positionX, positionY);

			Boolean = entityFitsInWorld(getEntityWorld());

			setPositionWithoutChecking(oldPositionX, oldPositionY);
		}
		return Boolean;
	}

	/**
	 * Checks if a given radius is valid.
	 * 
	 * @param 	radius
	 * 			The radius that has to be checked.
	 * 
	 * @return 	The boolean that checks if the radius is valid.
	 * 			@see implementation 
	 */
	protected abstract boolean isValidRadius(double radius);

	/**
	 * Checks if a velocity is valid.
	 * 
	 * @param 	velocityX
	 * 			The x-value of the velocity that has to be checked.
	 * @param 	velocityY
	 * 			The y-value of the velocity that has to be checked.
	 * 
	 * @return 	False if one of the two values is not a number or if the total velocity
	 * 			(calculated with the formula of the Euclidian distance) exceeds the maximum velocity.
	 * 			@see implementation
	 */
	private boolean isValidVelocity(double velocityX, double velocityY) {
		if ((Double.isNaN(velocityX)) || (Double.isNaN(velocityY)))
			return false;

		if (getEuclidianDistance(velocityX, velocityY) > getEntityMaxVelocity())
			return false;

		return true;
	}

	
	/// HELP FUNCTIONS ///
	
	/**
	 * Checks if a collision with a boundary is with a horizontal boundary.
	 * 
	 * @param 	entity
	 * 			The entity that collides.
	 * @param 	collisionArray
	 * 			The collision position.
	 * 
	 * @return 	True if the y-value of the collision position is approximately equal to 0 (the lower-boundary) or equal to the
	 * 			height of the world (the upper-boundary).
	 * 			@see implementation
	 */
	protected static boolean collideHorizontalBoundary(Entity entity, double[] collisionArray) {
		boolean crossesLowerBoundary = (collisionArray[1] < GAMMA * entity.getEntityRadius());
		boolean crossesUpperBoundary = (collisionArray[1] > (entity.getEntityWorld().getWorldHeight() - GAMMA * entity.getEntityRadius()));
		
		return (crossesLowerBoundary || crossesUpperBoundary);
	}
	
	/**
	 * Checks if a collision with a boundary is with a vertical boundary.
	 * 
	 * @param 	entity
	 * 			The entity that collides.
	 * @param 	collisionArray
	 * 			The collision position.
	 * 
	 * @return 	True if the x-value of the collision position is approximately equal to 0 (the left-boundary) or equal to the
	 * 			height of the world (the right-boundary).
	 * 			@see implementation
	 */
	protected static boolean collideVerticalBoundary(Entity entity, double[] collisionArray) {
		boolean crossesLeftBoundary = (collisionArray[0] < GAMMA * entity.getEntityRadius());
		boolean crossesRightBoundary = (collisionArray[0] > (entity.getEntityWorld().getWorldWidth() - GAMMA * entity.getEntityRadius()));
		
		return (crossesLeftBoundary || crossesRightBoundary);
	}
	
	/**
	 * A function that resolves the collision between two ships or two minor planets.
	 * 
	 * @param 	entity 
	 * 			The entity that will collide with the entity where the method is invoked on.
	 * 
	 * @post 	The velocities of the entities will be changed, the calculation is in the implementation.
	 * 			@see implementation
	 * @post 	When the collision is resolved, the position_list will be updated.
	 * 			@see implementation
	 */
	protected void doubleShipOrMinorPlanetCollide(Entity entity,double defaultEvolvingTime){
		final double position_1X = getEntityPositionX();
		final double position_1Y = getEntityPositionY();
		final double position_2X = entity.getEntityPositionX();
		final double position_2Y = entity.getEntityPositionY();
		
		final double velocity_1X = getEntityVelocityX();
		final double velocity_1Y = getEntityVelocityY();
		final double velocity_2X = entity.getEntityVelocityX();
		final double velocity_2Y = entity.getEntityVelocityY();
		
		final double radius_1 = getEntityRadius();
		final double radius_2 = entity.getEntityRadius();
		final double total_radius = (radius_1 + radius_2);
		
		final double mass_1 = getEntityMass();
		final double mass_2 = entity.getEntityMass();

		final double delta_x = position_2X - position_1X;
		final double delta_y = position_2Y - position_1Y;
		
		final double delta_rX = position_2X - position_1X;
		final double delta_rY = position_2Y - position_1Y;
		
		final double delta_vX = velocity_2X - velocity_1X;
		final double delta_vY = velocity_2Y - velocity_1Y;
		
		final double delta_v_r = (delta_rX * delta_vX + delta_rY * delta_vY);

		double BigJ = (2 * mass_1 * mass_2 * delta_v_r) / (total_radius * (mass_1 + mass_2));
		double Jx = (BigJ * delta_x) / total_radius;
		double Jy = (BigJ * delta_y) / total_radius;

		setEntityVelocity(velocity_1X + Jx / mass_1, velocity_1Y + Jy / mass_1);
		entity.setEntityVelocity(velocity_2X - Jx / mass_2, velocity_2Y - Jy / mass_2);
		
		World world = getEntityWorld();
		world.updatePositionListAfterCollision(this,entity, defaultEvolvingTime);
	}
	
	/**
	 * Checks if an entity could fit in a given world.
	 * 
	 * @param 	world
	 * 			The world where the entity will be checked in.
	 * 
	 * @return 	A boolean that checks if the entity can be in the world.
	 * 			@see implementation			
	 */
	protected boolean entityFitsInWorld(World world) {
		if (!entityLiesInBoundaries(world) || entityOverlappingInWorld(world) != null)
			return false;
		
		return true;
	}

	/**
	 * Checks whether an entity lies in the boundaries of the world.
	 * 
	 * @param 	world
	 * 			The world where the entity will be checked in.
	 * 
	 * @return 	The boolean that checks if a ship lies in the boundaries of the world.
	 * 			@see implementation
	 */
	protected boolean entityLiesInBoundaries(World world) {
		double radius = getEntityRadius();
		double upper_bound = (world.getWorldHeight() - OMEGA * radius);
		double lower_bound = OMEGA * radius;
		double right_bound = (world.getWorldWidth() - OMEGA * radius);
		double left_bound = OMEGA * radius;

		double positionX = getEntityPositionX();
		double positionY = getEntityPositionY();

		return ((positionX > left_bound) && (right_bound > positionX) && (positionY > lower_bound)
				&& (upper_bound > positionY));
	}

	/**
	 * Checks whether an entity is overlapping with an entity in the given world.
	 * 
	 * @param 	world
	 * 			The world where the entity will be checked in.
	 * 
	 * @return 	The boolean that checks if the entity is overlapping with something in the world.
	 * 			@see implementation
	 */
	protected Entity entityOverlappingInWorld(World world) {
		for (Entity entity : world.getWorldEntities())
			if (this.overlap( entity) && !this.equals(entity))
				return entity;
		
		return null;
	}
	
	/**
	 * Return the mass of an entity computed by the mass-formula.
	 * 
	 * @return 	The mass of a bullet computed by the bullet mass formula.
	 * 			@see implementation
	 */
	protected static double MassFormula(double radius, double density) {
		return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3) * density;
	}

	
	/// MOVE ///
	
	/**
	 * Moves the entity over the given time "moveTime".
	 * 
	 * @param 	moveTime
	 * 			The time the entity has to move.
	 * 
	 * @effect 	The entity will be moved.
	 * 			@see implementation in the abstract methods
	 */
	protected void move(double moveTime) {
		if (moveTime < 0)
			throw new IllegalArgumentException();
		
		double velocityX = getEntityVelocityX();
		double velocityY = getEntityVelocityY();

		final double collidingPositionX = getEntityPositionX() + velocityX * moveTime;
		final double collidingPositionY = getEntityPositionY() + velocityY * moveTime;
		
		setPositionWithoutChecking(collidingPositionX, collidingPositionY);
	}
	

	/// OVERLAP ///

	/**
	 * Returns a boolean saying if the two entities are overlapping.
	 *
	 * @param	otherEntity
	 * 			The other entity.
	 * @return	True if the entity where this method is invoked on and entity are the same.
	 * 		  | if(this.equals(entity))
	 * 		  |   result == true
	 * @return	True if the distance between the two entities is negative.
	 *		  | result == (this.getDistanceBetween(otherEntity) < 0)
	 */
	public boolean overlap(Entity entity) {
		if (this.equals(entity))
			return true;

		double distance = this.getDistanceBetween(entity);

		return (distance < 0);
	}

	
	/// TERMINATION AND STATES ///

	/**
	 * The state of the entity is initiated as NO_WORLD.
	 */
	private State state = State.NO_WORLD;

	/**
	 * The three states an entity can be in: 
	 * 	 NO_WORLD: the entity has no world.
	 *   IN_WORLD: the entity is in a world.
	 *   TERMINATED: the entity is terminated, so it doesn't exist anymore.
	 */
	protected static enum State {
		NO_WORLD, IN_WORLD, TERMINATED;
	}
	
	/**
	 * Terminate the entity.
	 * 
	 * @effect 	The entity will be terminated, the state will be set on TERMINATED.
	 *			@see implementation 
	 */
	public abstract void Terminate();

	
	///COLLISIONS///
	
	/**
	 * A method that resolves the collision between an entity and a boundary.
	 * 
	 * @param 	collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param 	defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param 	collisionListener
	 * 			A variable used to visualize the explosions.
	 * 
	 * @post 	The collision will be resolved by checking if the entity collides with a horizontal or vertical boundary and inverting the respective velocity.
	 * 		  | if collideHorizontalBoundary(this,collisionPosition)
	 * 		  |   new.getEntityVelocityY == -getEntityVelocityY
	 * 		  | else
	 * 		  |   new.getEntityVelocityX == -getEntityVelocityX
	 * 
	 * @effect 	After the change of velocity, the entity_positionlist in world will be updated.
	 * 		  | getEntityWorld().updatePositionListAfterCollision(this,defaultEvolvingTime)
	 */
	protected void entityAndBoundaryCollide(double[] collisionPosition, double defaultEvolvingTime,CollisionListener collisionListener) {
		double VelocityX = getEntityVelocityX();
		double VelocityY = getEntityVelocityY();
		
		if (collideHorizontalBoundary(this, collisionPosition))
			VelocityY = -VelocityY;
		
		if (collideVerticalBoundary(this, collisionPosition))
			VelocityX = -VelocityX;
		
		setEntityVelocity(VelocityX, VelocityY);
		World world = getEntityWorld();
		world.updatePositionListAfterCollision(this, defaultEvolvingTime);
	}
	
	/**
	 * A method that resolves the collision between an entity and a bullet.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	bullet
	 * 			The bullet that will collide with the entity where the method is invoked on.
	 * @param 	collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param 	defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param 	collisionListener
	 * 			A variable used to visualize the explosions.
	 * 
	 * @effect 	if the bullet collides with the ship that has fired it, it will be loaded on this ship.
	 *		  | if (bullet.getBulletSource() = this)
	 *		  |   (new)bullet.getBulletSource() = this.
	 * @effect 	In all other cases, the bullet and the entity will be terminated.
	 * 		  | else
	 * 		  |   Terminate()
	 * 		  |   bullet.Terminate()
	 */
	protected void entityAndBulletCollide(Bullet bullet, double[] collisionPosition, CollisionListener collisionListener){
		//the bullet hits it's source ship -> it's reloaded.
		if (bullet.getBulletSource() == this) {
			World world = getEntityWorld();
			
			double position1X = getEntityPositionX();
			double position1Y = getEntityPositionY();
			
			bullet.setPositionWithoutChecking(position1X, position1Y);
			world.removeEntityFromWorld(bullet);
			((Ship)this).addOneBulletToShip(bullet);
		}
		
		//The bullet and the entity are terminated
		else{
			double collisionPositionX = collisionPosition[0];
			double collisionPositionY = collisionPosition[1];
			
			if (collisionListener != null)
				collisionListener.objectCollision(this, bullet,collisionPositionX,collisionPositionY);
			
			Terminate();
			bullet.Terminate();
		}
	}
	
	/**
	 * A method that resolves the collision between an entity and a ship.
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
	 * @post 	The collision will be resolved
	 * 			@see implementation of the abstract methods
	 */
	protected abstract void entityAndShipCollide(Ship ship,double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener);
	
	/**
	 * A method that resolves collisions between an entity and the world or two entities.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @param 	entity
	 * 			The entity that will collide with the entity where the method is invoked on.
	 * @param 	collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param 	defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param 	collisionListener
	 * 			A variable used to visualize the explosions.
	 * 
	 * @effect 	If the given entity is null, the entity where the method is invoked on will collide with a boundary.
	 * 			@see implementation
	 * @effect 	If the given entity is a ship, the entity where the method is invoked on will collide with this ship.
	 * 			@see implementation
	 * @effect 	If the given entity is a minor planet, the entity where the method is invoked on will collide with this minor planet.
	 * 			@see implementation
	 * @effect 	If the given entity is a bullet, the entity where the method is invoked on will collide with this bullet.
	 * 			@see implementation
	 */
	protected void letCollisionHappen(Entity entity,double[] collisionPosition,double defaultEvolvingTime, CollisionListener collisionListener){
		// entity will be null if there's only one collisionEntity in World. This means there's a boundary collision.
		if (entity == null)
			this.entityAndBoundaryCollide(collisionPosition,defaultEvolvingTime,collisionListener);
		
		// Collision with a ship
		else if (entity instanceof Ship)
			this.entityAndShipCollide((Ship)entity,collisionPosition,defaultEvolvingTime,collisionListener);
		
		// Collision with a minor planet
		else if (entity instanceof MinorPlanet)
			((MinorPlanet)entity).entityAndMinorPlanetCollide(this,collisionPosition,defaultEvolvingTime,collisionListener);
		
		// Collision with a bullet
		else if (entity instanceof Bullet) 
			this.entityAndBulletCollide((Bullet)entity,collisionPosition,collisionListener);					
	}
	
	
	/// RELATIONS WITH OTHER CLASSES ///
	
	/**
	 * The world where the entity is placed in. When initiated, the entity has no world.
	 */
	private World world = null;
	
}

