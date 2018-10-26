package asteroids.program;

import java.util.List;

class NullEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected NullEntity() throws IllegalArgumentException {
		//
	}

	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return null;
	}

}
