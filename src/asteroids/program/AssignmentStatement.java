package asteroids.program;

import java.util.List;

class AssignmentStatement extends MyStatement {
	
	/// CONSTRUCTOR ///

	protected AssignmentStatement(String variableName, MyExpression expression) {
		setVariableName(variableName);
		setExpression(expression);
	}

	
	/// BASIC PROPERTIES ///

	private MyExpression expression;
	private String variableName;
	

	/// GETTERS ///
	
	private MyExpression getExpression() {
		return expression;
	}

	private String getVariableName() {
		return variableName;
	}

	
	/// SETTERS ///

	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}
	
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	
	/// CHECKERS ///
	
	private boolean isValidVariable(String variableName, MyExpression expression, List<MyExpression> actualArgs) {
		return ((!getStatementProgram().getProgramFunctions().containsKey(variableName))
				&& (expression.getExpressionResult(getStatementProgram(), actualArgs) instanceof Double));
	}
	
	
	/// HELP FUNCTIONS ///

	protected void assignLocalVariable(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);
		
		function.addLocalVariable(getVariableName(), getExpression().getExpressionResult(program, actualArgs, function));
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);

		if (isValidVariable(getVariableName(), getExpression(), actualArgs))
			getStatementProgram().addVariable(getVariableName(), getExpression().getExpressionResult(program, actualArgs));
		
		else
			throw new IllegalArgumentException();
	}

}
