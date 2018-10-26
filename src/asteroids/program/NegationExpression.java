package asteroids.program;

import java.util.List;

class NegationExpression extends UnaryExpression<ArithmeticExpression> implements ArithmeticExpression {

	/// CONSTRUCTOR ///

	protected NegationExpression(ArithmeticExpression operand) throws IllegalArgumentException {
		super(operand);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		UnaryArithmeticExpression expression = new UnaryArithmeticExpression();
		Double Operand = (double) expression.solveOperand(program, actualArgs, function);

		return -Operand;
	}

}
