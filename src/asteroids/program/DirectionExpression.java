package asteroids.program;

import java.util.List;

class DirectionExpression extends MyExpression {

	/// CONSTRUCTOR ///

	protected DirectionExpression() {
		//
	}

	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getExpressionShip().getEntityOrientation();
	}

}
