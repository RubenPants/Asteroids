package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A value class that describes position.
 * 
 * @authors Sieben Bocklandt and Ruben Broekx
 * 
 */
@Value
public class Position {
	
	/// CONSTRUCTOR ///
	
	/**
	 * Initializes a position.
	 * 
	 * @param 	positionX
	 * 			The x-component.
	 * @param 	positionY
	 * 			The y-component.
	 * 
	 * @effect 	The x and y component will be set on the given values.
	 * 			@see implementation
	 */
	private Position(double positionX, double positionY) {
		setPositionX(positionX);
		setPositionY(positionY);
	}
	
	
	/// BASIC PROPERTIES ///
	
	private double positionX;
	private double positionY;
	
	
	/// DEFAULTS ///
	
	/**
	 * Initializes a position with default values 0.
	 * 
	 * @effect 	Uses the normal constructor.
	 * 			@see implementation
	 */
	protected Position() {
		this(0, 0);
	}
	
	
	/// GETTERS ///
	
	/**
	 * Returns the position as an array.
	 * 
	 * @return 	The array.
	 * 		  | result == {positionX, positionY}
	 */
	protected double[] getPositionArray() {
		double[] result = { positionX, positionY };
		return result;
	}
	
	/**
	 * Return the x-component of the position.
	 * 
	 * @return 	The x-component.
	 * 			@see implementation
	 */
	protected double getPositionX() {
		return positionX;
	}
	
	/**
	 * Return the y-component of the position.
	 * 
	 * @return 	The y-component.
	 * 			@see implementation
	 */
	protected double getPositionY() {
		return positionY;
	}
	
	
	/// SETTERS ///
	
	/**
	 * Set the x-component of the position.
	 * 
	 * @param 	newPositionX
	 * 			The new value.
	 * 
	 * @post 	The new x-component will be equal to newPositionX.
	 * 		  | new.getPositionyX == newPositionX
	 */
	protected void setPositionX(double newPositionX) {
		positionX = newPositionX;
	}
	
	/**
	 * Set the y-component of the position.
	 * 
	 * @param 	newPositionY
	 * 			The new value.
	 * 
	 * @post 	The new y-component will be equal to newPositionY.
	 * 		  | new.getPositionY == newPositionY
	 */
	protected void setPositionY(double newPositionY) {
		positionY = newPositionY;
	}
}

