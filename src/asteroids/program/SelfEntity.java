package asteroids.program;

import java.util.List;

class SelfEntity extends EntityExpression {
	
	/// CONSTRUCTOR ///

	protected SelfEntity() throws IllegalArgumentException {
		//
	}
	
	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);
		
		return getExpressionShip();
	}

}
