package asteroids.program;

import java.util.List;

public abstract class BinaryExpression<E> extends MyExpression {

	/// CONSTRUCTOR ///

	protected BinaryExpression(E leftExpression, E rightExpression)
			throws IllegalArgumentException {
		setLeftOperand(leftExpression);
		setRightOperand(rightExpression);
	}


	/// BASIC PROPERTIES ///

	private E left_operand = null;
	private E right_operand = null;

	
	/// GETTERS ///
	
	@Override
	protected E getLeftOperand() {
		return left_operand;
	}

	protected Object getLeftOperandResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return ((MyExpression) getLeftOperand()).getExpressionResult(program, actualArgs, function);
	}

	@Override
	protected E getRightOperand() {
		return right_operand;
	}

	protected Object getRightOperandResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return ((MyExpression) getRightOperand()).getExpressionResult(program, actualArgs, function);
	}
	

	/// SETTERS ///

	protected void setLeftOperand(E expression) {
		left_operand = expression;
	}

	protected void setRightOperand(E expression) {
		right_operand = expression;
	}
	
	
	/// LOCAL CLASS ///
	
	class BinaryArithmeticExpression implements BinaryOperandSolver, ArithmeticExpression{
		
		public Object solveRightOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double rightParameter = parameterArray[1];

			if (rightParameter != null)
				return rightParameter;
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, (MyExpression) getRightOperand(), function))
					return (double) getRightOperandResult(program, actualArgs, function);
				else
					throw new IllegalArgumentException();
			}
		}

		public Object solveLeftOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double leftParameter = parameterArray[0];

			if (leftParameter != null)
				return leftParameter;
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, (MyExpression) getLeftOperand(), function))
					return (double) getLeftOperandResult(program, actualArgs, function);
				else
					throw new IllegalArgumentException();
			}
		}
	}
	
	
	/// LOCAL INTERFACE ///

	interface BinaryOperandSolver {
		Object solveLeftOperand(Program program, List<MyExpression> actualArgs, MyFunction function);

		Object solveRightOperand(Program program, List<MyExpression> actualArgs, MyFunction function);
	}
	
}
