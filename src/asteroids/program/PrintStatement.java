package asteroids.program;

import java.util.List;

class PrintStatement extends MyStatement {

	/// CONSTRUCTOR ///

	protected PrintStatement(MyExpression expression) {
		setExpression(expression);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression expression;


	/// SETTERS ///

	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}

	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);

		if (expression instanceof ParameterExpression)
			throw new IllegalArgumentException();

		Object result = expression.getExpressionResult(program, actualArgs);

		getStatementProgram().addPrintOut(result);

		System.out.println(result);
	}

}
