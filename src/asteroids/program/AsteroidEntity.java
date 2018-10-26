package asteroids.program;

import java.util.List;

class AsteroidEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected AsteroidEntity() throws IllegalArgumentException {
		//
	}

	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getClosestEntity(getWorldEntity("Asteroid"));
	}

}
