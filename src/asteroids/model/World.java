package asteroids.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import asteroids.part2.CollisionListener;

/**
 * A class that describes worlds. A world has a height and a width and can contain bullets and ships.
 * 
 * @invar 	The world's height will be valid.
 * 		  | isValidWidthOrHeight(height)
 * @invar 	The world's width will be valid.
 * 		  | isValidWidthOrHeight(width)
 * 
 * @authors Sieben Bocklandt and Ruben Broekx
 * 
 */
public class World {

	/// CONSTRUCTOR ///
	
	/**
	 * Initializes a new world with given width and height.
	 * 
	 * @param 	width
	 * 			The width of the world.
	 * @param 	height
	 * 			The height of the world.
	 * 
	 * @effect 	The width and height will be set.
	 * 			@see implementation
	 */
	public World(double width, double height) {
		setWorldSize(width,height);
	}

	/**
	 * Initializes a world with the default size.
	 * 
	 * @effect 	A new world with the maximum size is made.
	 * 			@see implementation
	 */
	public World() {
		this(UPPER_WORLD_BOUND_WIDTH, UPPER_WORLD_BOUND_HEIGHT);
	}

	
	/// BASIC PROPERTIES ///

	private double height;
	private double width;

	
	/// CONSTANTS ///
	
	/**
	 * A constant that is used to "correct" the errors that occur when using double values.
	 */
	private final static double GAMMA = 0.01;
	
	/**
	 * The maximum height a world can have.
	 */
	private final static double UPPER_WORLD_BOUND_HEIGHT = Double.MAX_VALUE;
	
	/**
	 * The maximum width a world can have.
	 */
	private final static double UPPER_WORLD_BOUND_WIDTH = Double.MAX_VALUE;

	
	/// GETTERS ///

	/**
	 * Returns Collision Entity 1.
	 * 
	 * @return 	The entity
	 * 			@see implementation
	 */
	private Entity getCollisionEntity1(){
		return collision_entity_1;
	}
	
	/**
	 * Returns Collision Entity 2.
	 * 
	 * @return 	The entity
	 * 			@see implementation
	 */
	private Entity getCollisionEntity2(){
		return collision_entity_2;
	}
	
	/**
	 * Return the entity positioned at the given position.
	 * 
	 * @param 	positionX
	 * 			The x-value of the position.
	 * @param 	positionY
	 * 			The y-value of the position.
	 * 
	 * @return 	Null if there is no entity at the given position.
	 * 			@see implementation
	 * @return 	The entity that is at the given position, if there is an entity at this position.
	 * 			@see implementation
	 */
	public Object getEntityAt(double positionX, double positionY) {
		String searchPosition = (positionX + "," + positionY);

		if (getWorldEntityPositions().containsKey(searchPosition))
			return getWorldEntityPositions().get(searchPosition);
		
		else
			return null;
	}
	
	/**
	 * Return the position where the next collision will take place.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @return 	The position of collision between two entities, this is the position if both collision_entity_1 and 
	 * 			collision_entity_2 are not null.
	 * 			@see implementation
	 * @return	The position of collision between an entity and a boundary of the world, this is the position 
	 * 			when collision_entity_1 is not null, but collision_entity_2 is. 
	 * 			@see implementation
	 * @return 	An array of two with both values set to POSITIVE_INFINITY, this is when there are no collisions, so 
	 * 			both collision_entity_1 and collision_entity_2 are null.
	 * 			@see implementation
	 */
	public double[] getPositionNextCollision() {
		// Two entities will collide.
		if (getCollisionEntity1() != null && getCollisionEntity2() != null)
			return getCollisionEntity1().getCollisionPosition(getCollisionEntity2());

		// An entity will collide with the boundary of the world.
		else if (getCollisionEntity1() != null && getCollisionEntity2() == null)
			return getCollisionEntity1().getPositionCollisionBoundary();

		// No collision will take place
		else {
			double infinity = Double.POSITIVE_INFINITY;
			double[] new_array = { infinity, infinity };
			return new_array;
		}
	}

	/** 
	 * Return the state of the world.
	 * 
	 * @return 	The state.
	 * 			@see implementation
	 */
	private State getState() {
		return state;
	}
	
	/**
	 * Return the time until the next collision.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 * 
	 * @effect  Collision entity1 and collision entity2 will be set on null before the collision-check.
	 * 		  | resetCollisionEntities()
	 * @effect 	If there is a collision between an entity and a boundary of the world, collision_entity_1  
	 * 			will be set on the entity that collides. collision_entity_2 will be null. Minimum collision time will
	 * 			be smaller than its previous value.
	 *		  | setCollisionEntity1(entity)
	 *		  | setCollisionEntity2(null)
	 *		  | new.minimumCollisionTime <= minimumCollisionTime.
	 * @effect 	If there is a collision, collision_entity_1 and collision_entity_2 will be set on the entities that 
	 * 			collide (entity1 and entity2). Minimum collision time will be smaller than its previous value.
	 *		  | setCollisionEntity1(entity1)
	 *		  | setCollisionEntity2(entity2)
	 *		  | new.minimumCollisionTime <= minimumCollisionTime.
	 * 
	 * @return 	The time till the first collision in a world will happen.
	 * 			@see implementation
	 */
	public double getTimeNextCollision() {
		double minimumCollisionTime = Double.POSITIVE_INFINITY;
		
		resetCollisionEntities();

		for (Entity entity_1 : getWorldEntities()) {
			double timeTillCollision = entity_1.getTimeCollisionBoundary();
			
			// Collision of the entity with the boundaries of the world.
			if (timeTillCollision < minimumCollisionTime) {
				minimumCollisionTime = timeTillCollision;
				setCollisionEntity1( entity_1);
				setCollisionEntity2(null);
			}

			// Collision of the entity with another entity in the world
			for (Entity entity_2 : getWorldEntities()) {
				if (entity_2.hashCode() > entity_1.hashCode()) {
					double delta_t = entity_1.getTimeToCollision( entity_2);
					if (delta_t < minimumCollisionTime) {
						minimumCollisionTime = delta_t;
						setCollisionEntity1(entity_1);
						setCollisionEntity2(entity_2);
					}
				}
			}
		}
		return minimumCollisionTime;
	}

	/**
	 * Return the set of all the entities in the world.
	 * 
	 * @return 	The set of entities in the world.
	 * 			@see implementation
	 */
	public Set<Entity> getWorldEntities() {
		Set<Entity> result = new HashSet<>();
		
		result.addAll(entities.values());
		
		return result;
	}
	
	/**
	 * Return the map with as keys the positions of all the entities in the world and as values the respective entities.
	 * 
	 * @return 	The map.
	 * 			@see implementation
	 */
	private Map<String,Entity> getWorldEntityPositions() {
		Map<String, Entity> result = new HashMap<String,Entity>();
		
		result.putAll(entity_positions);
		
		return result;
	}

	/**
	 * Return the height of the world.
	 * 
	 * @return 	The height of the world.
	 * 			@see implementation
	 */
	protected double getWorldHeight() {
		return getWorldSize()[1];
	}

	/**
	 * Return the size of the world.
	 * 
	 * @return 	The size of the world, expressed as an array.
	 * 			@see implementation
	 */
	public double[] getWorldSize() {
		double width = this.width;
		double height = this.height;
		double[] size_array = { width, height };

		return size_array;
	}

	/**
	 * Returns a set with only entities of a specific class that belong to the world.
	 * 
	 * @param 	string
	 * 			The string that defines the specific class like "Class".
	 * 
	 * @return 	The set of the entities that belong to the class in the string.
	 * 			@see implementation
	 * 
	 */
	public Set<? extends Entity> getWorldSpecificEntities(String string){
		Set<Entity> result = new HashSet<Entity>();
		for (Entity entity : getWorldEntities()){
			if (entity.getClass().getSimpleName().equals(string) || entity.getClass().getSuperclass().getSimpleName().equals(string))
				result.add(entity);}	
		return result;		
	}
	
	/**
	 * Return the width of the world.
	 * 
	 * @return 	The width of the world.
	 * 			@see implementation
	 */
	protected double getWorldWidth() {
		return getWorldSize()[0];
	}
	
	
	/// SETTERS ///
	
	/**
	 * Set the variable collision_entity_1 to a given entity.
	 * 
	 * @param 	entity
	 * 			The entity that will collide
	 * @post 	collision_entity_1 will be equal to the given entity
	 * 		  | new.getCollisionEntity1 == entity.
	 */
	private void setCollisionEntity1(Entity entity){
		collision_entity_1 = entity;
	}
	
	/**
	 * Set the variable collision_entity_2 to a given entity.
	 * 
	 * @param 	entity
	 * 			The entity that will collide
	 * @post 	collision_entity_2 will be equal to the given entity
	 * 		  | new.getCollisionEntity2 == entity.
	 */
	private void setCollisionEntity2(Entity entity){
		collision_entity_2 = entity;
	}

	/**
	 * Set the worlds height.
	 * 
	 * @param 	height
	 * 			The new height.
	 * 
	 * @post 	If the given height is negative, the new height is the absolute value of the given height.
	 * 		  | new.getWorldHeight() == Math.abs(height)
	 * @post 	If the given height is too big, the height of the world will be set to the upper bound.
	 * 		  | new.getWorldHeight() == UPPER_WORLD_BOUND_HEIGHT
	 * @post 	In all the other cases the given height will be a valid height and will be set as the 
	 * 			new height of the world.
	 * 		  | new.getWorldHeight() == height
	 */
	private void setWorldHeight(double height) {
		if (height < 0)
			height = Math.abs(height);

		if (!isValidWidthOrHeight(height))
			height = UPPER_WORLD_BOUND_HEIGHT;

		this.height = height;
	}

	/** 
	 * Set the world's size.
	 * 
	 * @param 	width
	 * 			The new width.
	 * @param 	height
	 * 			The new height.
	 * 
	 * @effect 	The width and height will be set on the new values.
	 * 			@see implementation
	 */
	private void setWorldSize(double width, double height) {
		setWorldWidth(width);
		setWorldHeight(height);
	}

	/**
	 * Set the state of the world to a given state.
	 * 
	 * @param 	state
	 * 			The new state.
	 * 
	 * @post 	The new state will be equal to the given state.
	 * 		  | new.getState() == state
	 * 
	 * @throws 	IllegalStateException()
	 * 			if the given state is null.
	 * 		  | state == null
	 */
	private void setWorldState(State state) {
		if (state == null)
			throw new IllegalStateException();
		
		else
			this.state = state;
	}
	
	/**
	 * Set the world's width.
	 * 
	 * @param 	width
	 * 			The new width.
	 * 
	 * @post 	If the given width is negative, the new width is the absolute value of the given width.
	 * 		  | new.getWorldWidth() == Math.abs(width)
	 * @post 	If the given width is too big, the width of the world will be set to the upper bound.
	 * 		  | new.getWorldWidth() == UPPER_WORLD_BOUND_WIDTH
	 * @post 	In all the other cases the given width will be a valid width and will be set as the 
	 * 			new width of the world.
	 * 		  | new.getWorldWidth() == width
	 */
	private void setWorldWidth(double width) {
		if (width < 0)
			width = Math.abs(width);

		if (!isValidWidthOrHeight(width))
			width = UPPER_WORLD_BOUND_WIDTH;

		this.width = width;
	}

	
	/// CHECKERS ///
	
	/**
	 * Checks if the world can have this entity.
	 *  
	 * @param 	entity
	 * 			The entity that has to be checked.
	 * 
	 * @return 	The boolean that checks if the entity can have this world as its world.
	 * 			@see implementation
	 */
	protected boolean canHaveAsEntity(Entity entity) {		
		return (entity != null && entity.canHaveAsWorld(this));
	}

	/**
	 * Checks whether an given entity belongs to the world.
	 * 
	 * @param 	entity
	 * 			The entity that has to be checked.
	 * 
	 * @return 	The boolean that checks if the world has the entity.
	 * 			@see implementation
	 */
	protected boolean hasAsEntity(Entity entity) {
		return getWorldEntities().contains(entity);
	}

	/**
	 * Checks if the world has a proper state (which is terminated or not terminated).
	 * 
	 * @return 	The boolean that checks the proper state.
	 * 			@see implementation
	 */
	protected boolean hasWorldProperState() {
		return (!isWorldTerminated()) ^ isWorldTerminated();
	}
	
	/**
	 * Checks if a width or height is valid.
	 * 
	 * @param 	length
	 * 			The width or height that has to be checked.
	 * 
	 * @return 	The boolean that checks the length.
	 * 			@see implementation
	 */
	private boolean isValidWidthOrHeight(double length){
		return (length > 0 && Double.isFinite(length)) ;
	}

	/**
	 * Checks whether the world is terminated or not.
	 * 
	 * @return 	The boolean that checks if the world is terminated.
	 * 			@see implementation
	 */
	public boolean isWorldTerminated() {
		return (getState() == State.TERMINATED);
	}

	
	/// ADDERS ///

	/**
	 * Add a given entity to the world.
	 * 
	 * @param 	entity
	 * 			The entity that has to be added to the world.
	 * 
	 * @post 	The entity will belong to the world.
	 * 		  | new.getWorldEntities().contains(entity)
	 * @post	The entity will be added with its position as key into the entity_positions map.
	 * 		  | new.getWorldEntityPositions().containsValue(entity)
	 * 
	 * @effect	The entity's world will be set on "this", which represents this world.
	 * 		  | entity.setEntityInWorld(this)
	 * 
	 * @throws 	IllegalArgumentException if the world cannot have this entity.
	 * 		  | (!canHaveAsEntity(entity))
	 *
	 */
	public void addEntityToWorld(Entity entity) throws IllegalArgumentException {
		if (canHaveAsEntity(entity)) {
			entity.setEntityInWorld(this);
			entities.put(entity.hashCode(),entity);
			entity_positions.put(arrayToString(entity.getEntityPosition()), entity);
		} 
		else
			throw new IllegalArgumentException();
	}

	
	/// REMOVERS ///
	
	/**
	 * Removes the given entity from the world.
	 * 
	 * @param 	entity
	 * 			The entity that has to be removed.
	 * 
	 * @post 	The entity will be removed together with its key from the entity_positions list.
	 * 		  | !new.getWorldEntityPositions().containsValue(entity)
	 * @post 	The entity will not belong to the world anymore.
	 * 		  | !new.getWorldEntities().contains(entity)
	 * 
	 * @effect 	The entity will be set on state NO_WORLD.
	 * 		  | entity.setEntityFree()
	 * 
	 * @throws 	IllegalArgumentException if the world doesn't have the entity.
	 * 			@see implementation
	 */
	public void removeEntityFromWorld(Entity entity) throws IllegalArgumentException  {
		
		if (!getWorldEntities().contains(entity))
			throw new IllegalArgumentException();
		
		else{
			entities.remove(entity.hashCode());
			entity_positions.remove(arrayToString(entity.getEntityPosition()));
			entity.setEntityFree();
		}
	}


	/// HELP FUNCTIONS ///
	
	/**
	 * Set an array [x,y] to a string "x, y".
	 * 
	 * @param	array
	 * 			The array that has to be formed into a string.
	 * 			
	 * @return	A position in form of a string.
	 * 			@see implementation
	 */
	private String arrayToString(double[] array) {
		return (array[0] + "," + array[1]);
	}
		

	/// EVOLVE ///
	
	/**
	 * Evolve the world by the given time "defaultEvolvingTime" and resolve collisions that will happen.
	 * 
	 * @note	The method will be provided with comments, to make it more easily to follow the flow of our thinking.
	 */
	public void evolve(double defaultEvolvingTime, CollisionListener collisionListener) {
		//A world cannot evolve if defaultEvolvingTime is not a finite number. 
		if (!Double.isFinite(defaultEvolvingTime))
			throw new IllegalArgumentException();
		
		// A world cannot evolve if there are no entities
		else if (!getWorldEntities().isEmpty()) {

			// Determine time till the first collision.
			double timeToCollision = getTimeNextCollision();

			double collisionPositionX = getPositionNextCollision()[0];
			double collisionPositionY = getPositionNextCollision()[1];

			double[] collisionArray = { collisionPositionX, collisionPositionY };

			// timeToCollision is smaller than the defaultEvolvingTime which means there will be a 
			// collision before the defaultEvolvingTime is over.
			if (timeToCollision <= defaultEvolvingTime) {

				// Clear the out-dated Map 'entity_positions'.
				entity_positions.clear();

				// Update the positions of the entities, along with the 'entity_positions'-Map.
				for (Entity entity : getWorldEntities()) {
					// Move the entity over the predetermined time 'timeToCollision'
					// The method 'move' will check if the given entity 'entity' is one of the 
					// entities who will collide, these entities are: 'entity_1' and 'entity_2' 
					// (entity_2 can be null when an entity, entity_1, collides with the world).
					entity.move(timeToCollision);

					// Update the Map 'entity_positions' for each entity with its new position.
					entity_positions.put(arrayToString(entity.getEntityPosition()), entity);
				}

				// Check and execute the type of collision.
				getCollisionEntity1().letCollisionHappen(getCollisionEntity2(),collisionArray, timeToCollision, collisionListener);				
				
				double remainingTime = defaultEvolvingTime - timeToCollision;

				if (remainingTime >= 0) {
					// Invoke the method 'evolve()' in a recursive way to see if there would be other 
					// collisions in the remaining time.
					evolve(remainingTime, collisionListener);
				}
				else
					throw new IllegalArgumentException();
			}

			// timeToCollision is bigger strict than the defaultEvolvingTime, which means no collision will 
			// take place when we evolve over the defaultEvolvingTime. We can safely evolve the whole world
			// (with all its entities) over defaultEvolvingTime.
			else {
				entity_positions.clear();
				for (Entity entity : getWorldEntities()){
					entity.move(defaultEvolvingTime);
					entity_positions.put(arrayToString(entity.getEntityPosition()),entity);
				}
			}
		}
	}
	
	
	/// COLLISION-FUNCTIONS ///
	
	/**
	 * A variable that contains an entity when there's a collision in the future.
	 */
	private Entity collision_entity_1 = null;
	
	/**
	 * A variable that contains an entity when there's a collision between two entity's in the future.
	 */
	private Entity collision_entity_2 = null;

	/**
	 * Sets both the collision entities on null.
	 * 
	 * @post	collision_entity_1 will be null
	 * 		  | new.getCollisionEntity1() ==null
	 * @post 	collision_entity_2 will be null
	 * 		  | new.getCollisionEntity2() ==null
	 */
	private void resetCollisionEntities(){
		collision_entity_1 = null;
		collision_entity_2 = null;
	}
	
	/**
	 * Updates the entity_positions map and moves an entity after colliding to avoid direct collisions.
	 * 
	 * @param 	entity
	 * 			An entity that collided. 				
	 * @param 	defaultEvolvingTime
	 * 			the time until the collision happened.
	 * 
	 * @effect 	The entity will be removed from the entity_positions map, moved for a small time (a fraction
	 * 			of the time until collision) and then reput in the map. The move is needed, because otherwise, 
	 *	 		the entity would keep touching the boundary which would invoke the same collision again the next 
	 *	 		time the method evolve() will be invoked.
	 * 			@see implementation
	 */
	protected void updatePositionListAfterCollision(Entity entity,double defaultEvolvingTime){
		entity_positions.remove(arrayToString(entity.getEntityPosition()));
		
		entity.move(Math.pow(GAMMA,6) * defaultEvolvingTime);
		
		entity_positions.put(arrayToString(entity.getEntityPosition()), entity);
	}
	
	/**
	 * Updates the entity_positions map and moves entities after colliding.
	 * 
	 * @param 	entity1
	 * 			An entity that collided.				
	 * @param 	entity2
	 * 			the other entity that collided.
	 * @param 	defaultEvolvingTime
	 * 			the time until the collision happened.
	 * 
	 * @effect 	updatePositionListAfterCollision() will be used onthe entities (or only entity1 when colliding with a boundary) .
	 * 			@see implementation
	 */
	protected void updatePositionListAfterCollision(Entity entity1, Entity entity2,double defaultEvolvingTime){
		updatePositionListAfterCollision(entity1,defaultEvolvingTime);
		
		if (entity2 != null){
			updatePositionListAfterCollision(entity2,defaultEvolvingTime);
		}
	}
				

	/// TERMINATION AND STATES ///

	/**
	 * The state of the world is initiated as NOT_TERMINATED.
	 */
	private State state = State.NOT_TERMINATED;

	/**
	 * The two states of a world:
	 *   NOT_TERMINATED: the world is not terminated.
	 *   TERMINATED: the world doesn't exist anymore, so it's terminated.
	 */
	private static enum State {
		NOT_TERMINATED, TERMINATED;
	}
	
	/**
	 * Terminate this world.
	 * 
	 * @effect  The world's state is set to Terminated and all the entities it contains are removed from the world.
	 * 			@see implementation
	 */
	public void Terminate() {
		if (!isWorldTerminated()) {
			setWorldState(State.TERMINATED);
			
			for (Entity entity: getWorldEntities())
				removeEntityFromWorld(entity);
		}
	}
	

	/// RELATIONS WITH OTHER CLASSES ///
	
	/**
	 * The map entities is a map with as key the hash-code representing the entity, and as value the entity itself.
	 * It contains all the entities that belong to the world.
	 */
	private final Map<Integer, Entity> entities = new HashMap<Integer, Entity>();
	
	/** 
	 * The map entity_positions is a map with as key the string "x,y" representing the position of the entity and as 
	 * value the entity itself.
	 */
	private final Map<String, Entity> entity_positions = new HashMap<String, Entity>();
}

