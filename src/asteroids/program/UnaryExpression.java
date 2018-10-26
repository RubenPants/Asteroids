package asteroids.program;

import java.util.List;

public abstract class UnaryExpression<E> extends MyExpression {

	/// CONSTRUCTOR ///

	protected UnaryExpression(E operand) {
		setOperand(operand);
	}

	
	/// BASIC PROPERTIES ///

	private E operand;

	
	/// GETTERS ///

	@Override
	protected E getOperand() {
		return operand;
	}

	protected Object getOperandResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		return ((MyExpression) getOperand()).getExpressionResult(program, actualArgs, function);
	}

	
	/// SETTERS ///

	protected void setOperand(E operand) {
		this.operand = operand;
	}
	
	
	/// LOCAL CLASS ///
	
	class UnaryArithmeticExpression implements UnaryOperandSolver, ArithmeticExpression {

		public Object solveOperand(Program program, List<MyExpression> actualArgs, MyFunction function) {
			Double[] parameterArray = getExpressionParameter(actualArgs, function);
			Double parameter = parameterArray[0];

			if (parameter != null)
				return parameter;
			
			else {
				if (canHaveAsArithmeticOperand(program, actualArgs, (MyExpression) getOperand(), function))
					return (double) getOperandResult(program, actualArgs, function);
				
				else
					throw new IllegalArgumentException();
			}
		}
	}
	
	
	/// LOCAL INTERFACE ///
	
	interface UnaryOperandSolver{
		public Object solveOperand(Program program, List<MyExpression> actualArgs, MyFunction function);
	}

}
