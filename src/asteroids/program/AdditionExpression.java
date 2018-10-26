package asteroids.program;

import java.util.List;

class AdditionExpression extends BinaryExpression<ArithmeticExpression> implements ArithmeticExpression {
	
	/// CONSTRUCTOR ///

	protected AdditionExpression(ArithmeticExpression leftExpression, ArithmeticExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		BinaryArithmeticExpression solved = new BinaryArithmeticExpression();

		Double leftOperand = (double) solved.solveLeftOperand(program, actualArgs, function);
		Double rightOperand = (double) solved.solveRightOperand(program, actualArgs, function);

		return leftOperand + rightOperand;
	}
	
}
