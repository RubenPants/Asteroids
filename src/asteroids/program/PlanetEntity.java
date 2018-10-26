package asteroids.program;

import java.util.List;

class PlanetEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected PlanetEntity() throws IllegalArgumentException {
		//
	}
	
	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getClosestEntity(getWorldEntity("MinorPlanet"));
	}
}
