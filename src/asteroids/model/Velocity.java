package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A value class that describes velocity.
 * 
 * @authors Sieben Bocklandt and Ruben Broekx
 *
 */
@Value
public class Velocity {
	
	/// CONSTRUCTOR ///
	
	/**
	 * Initializes a velocity.
	 * 
	 * @param 	velocityX
	 * 			The x-component.
	 * @param 	velocityY
	 * 			The y-component.
	 * 
	 * @effect 	The x and y component will be set on the given values.
	 * 			@see implementation
	 */
	private Velocity(double velocityX, double velocityY) {
		setVelocityX(velocityX);
		setVelocityY(velocityY);
	}
	
	
	/// BASIC PROPERTIES ///
	
	private double velocityX;
	private double velocityY;
	
	
	/// DEFAULTS ///
	
	/**
	 * Initializes a velocity with default values 0.
	 * 
	 * @effect 	Uses the normal constructor.
	 * 			@see implementation
	 */
	protected Velocity() {
		this(0, 0);
	}
	
	
	/// GETTERS /// 

	/**
	 * Returns the velocity as an array.
	 * 
	 * @return 	The array.
	 * 		  | result == {velocityX, velocityY}
	 */
	protected double[] getVelocityArray() {
		double[] result = { velocityX, velocityY };
		return result;
	}
	
	/**
	 * Return the x-component of the velocity.
	 * 
	 * @return 	The x-component.
	 * 			@see implementation
	 */
	protected double getVelocityX() {
		return velocityX;
	}
	
	/**
	 * Return the y-component of the velocity.
	 * 
	 * @return 	The y-component.
	 * 			@see implementation
	 */
	protected double getVelocityY() {
		return velocityY;
	}
	
	
	/// SETTERS /// 
	
	/**
	 * Set the x-component of the velocity.
	 * 
	 * @param 	newVelocityX
	 * 			The new value.
	 * 
	 * @post 	The new x-component will be equal to newVelocityX.
	 * 		  | new.getVelocityX == newvelocityX
	 */
	protected void setVelocityX(double newVelocityX) {
		velocityX = newVelocityX;
	}
	
	/**
	 * Set the y-component of the velocity.
	 * 
	 * @param 	newVelocityY
	 * 			The new value.
	 * 
	 * @post 	The new y-component will be equal to newVelocityY.
	 * 		  | new.getVelocityY == newVelocityY
	 */
	protected void setVelocityY(double newVelocityY) {
		velocityY = newVelocityY;
	}
}

