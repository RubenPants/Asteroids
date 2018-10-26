package asteroids.program;

import java.util.List;

class ReturnStatement extends MyStatement {

	/// CONSTRUCTOR ///

	protected ReturnStatement(MyExpression expression) {
		setReturnExpression(expression);
	}

	
	/// BASIC PROPERTIES ///
	
	private MyExpression expression;
	
	
	/// GETTERS ///

	private MyExpression getExpression() {
		return this.expression;
	}

	
	/// SETTERS ///
	
	private void setReturnExpression(MyExpression expression) {
		this.expression = expression;
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		throw new IllegalArgumentException();
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		if (getExpression() instanceof VariableExpression)
			return ((VariableExpression) getExpression()).getExpressionResult(program, actualArgs, function);
		else
			return getExpression().getExpressionResult(program, actualArgs, function);
	}

}
