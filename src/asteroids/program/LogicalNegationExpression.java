package asteroids.program;

import java.util.List;

class LogicalNegationExpression extends UnaryExpression<BooleanExpression> implements BooleanExpression {

	/// CONSTRUCTOR ///

	protected LogicalNegationExpression(BooleanExpression operand) {
		super(operand);
	}
	

	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function)
			throws IllegalArgumentException {
		setExpressionProgram(program);

		return !(Boolean) getOperandResult(program, actualArgs, function);
	}

}
