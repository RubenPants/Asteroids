package asteroids.program;

import java.util.List;

class PlanetoidEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected PlanetoidEntity() throws IllegalArgumentException {
		//
	}

	
	/// GETTERS ///
	
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getClosestEntity(getWorldEntity("Planetoid"));
	}

}
