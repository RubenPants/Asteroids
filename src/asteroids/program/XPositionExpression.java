package asteroids.program;

import java.util.List;

class XPositionExpression extends OnEntityExpression {
	
	/// CONSTRUCTOR ///
	
	protected XPositionExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	
	/// GETTERS ///
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getOperandResult(program).getEntityPositionX();
	}

}
