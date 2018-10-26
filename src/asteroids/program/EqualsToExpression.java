package asteroids.program;

import java.util.List;

class EqualsToExpression extends BinaryExpression<MyExpression> implements BooleanExpression  {

	/// CONSTRUCTOR ///

	protected EqualsToExpression(MyExpression leftExpression, MyExpression rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	
	/// GETTERS ///
	
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);
		
		//ANONYMOUS CLASS
		BinaryOperandSolver solver = new BinaryOperandSolver() {
			
			public Object solveLeftOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
				if (getLeftOperand() instanceof ParameterExpression)
					return (Double) getArgumentExpression(getLeftOperand(), actualArgs);
				else
					return getLeftOperandResult(program, actualArgs, function);
			}

			public Object solveRightOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
				if (getRightOperand() instanceof ParameterExpression)
					return (Double) getArgumentExpression(getRightOperand(), actualArgs);
				else
					return getRightOperandResult(program, actualArgs, function);
			}
			
		};

		Object leftOperand = solver.solveLeftOperand(program, actualArgs, function);
		Object rightOperand = solver.solveRightOperand(program, actualArgs, function);

		return leftOperand.equals(rightOperand);
	}

	
	/// CHECKERS ///

	protected boolean canHaveAsNbOperands(double number) {
		return number == 2;
	}

	
	/// HELP FUNCTIONS ///

	protected void assignExpressionToParameter(List<MyExpression> actualArgs) {
		if (getLeftOperand() instanceof ParameterExpression)
			setLeftOperand(actualArgs.get(((ParameterExpression) getLeftOperand()).getParameterNumber() - 1));

		if (getRightOperand() instanceof ParameterExpression)
			setRightOperand(actualArgs.get(((ParameterExpression) getRightOperand()).getParameterNumber() - 1));
	}

	
	/// PROPERTIES ///

	protected MyExpression left_operand = null;
	protected MyExpression right_operand = null;

}
