package asteroids.program;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Ship;

class ShipEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected ShipEntity() throws IllegalArgumentException {
		//
	}

	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		Set<? extends Entity> ships = getWorldEntity("Ship");

		// for this method, the ship the program runs on can't be used.
		ships.remove(getExpressionShip());

		return (Ship) getClosestEntity(ships);
	}
	
}
