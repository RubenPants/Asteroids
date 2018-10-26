package asteroids.program;

import java.util.List;

class YVelocityExpression extends OnEntityExpression {
	
	/// CONSTRUCTOR ///
	
	protected YVelocityExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getOperandResult(program).getEntityVelocityY();
	}

}
