package asteroids.program;

import asteroids.model.Entity;

abstract class OnEntityExpression extends MyExpression implements ArithmeticExpression {

	/// CONSTRUCTOR ///

	protected OnEntityExpression(MyExpression operand) throws IllegalArgumentException {
		setOperand(operand);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression operand;
	

	/// GETTERS ///

	protected MyExpression getOperand() {
		return operand;
	}

	protected Entity getOperandResult(Program program) {
		return (Entity) getOperand().getExpressionResult(program);
	}
	
	
	/// SETTERS ///

	private void setOperand(MyExpression operand) {
		if (canHaveAsOnEntityExpressionOperand(operand))
			this.operand = operand;

		else
			throw new IllegalArgumentException();
	}

	
	/// CHECKERS ///
	
	private boolean canHaveAsOnEntityExpressionOperand(MyExpression expression) {
		return ((expression instanceof EntityExpression || expression instanceof VariableExpression)
				&& !(expression instanceof NullEntity));
	}

}
