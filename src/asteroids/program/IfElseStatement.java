package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

class IfElseStatement extends MyStatement {

	/// CONSTRUCTOR ///

	protected IfElseStatement(BooleanExpression condition, MyStatement ifBody, MyStatement elseBody) {
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	
	/// BASIC PROPERTIES ///

	private BooleanExpression condition;
	private MyStatement ifBody;
	private MyStatement elseBody;
	
	
	/// GETTERS ///

	private MyExpression getCondition() {
		return (MyExpression) condition;
	}
	
	private MyStatement getElseBody() {
		return elseBody;
	}
	
	private MyStatement getIfBody() {
		return ifBody;
	}
	
	
	/// SETTERS ///

	private void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}

	private void setElseBody(MyStatement elseBody) {
		this.elseBody = elseBody;
	}

	private void setIfBody(MyStatement ifBody) {
		this.ifBody = ifBody;
	}
	
	
	/// CHECKERS ///
	
	protected boolean containsStatement(String name){
		boolean contains = false;
		
		if (getIfBody().containsStatement(name))
			contains = true;
		
		else if (getElseBody() != null && getElseBody().containsStatement(name))
			contains = true;
	
		return contains;
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);

		if ((boolean) getCondition().getExpressionResult(program, actualArgs))
			getIfBody().evaluate(program, actualArgs);
		
		else if (getElseBody() != null)
			getElseBody().evaluate(program, actualArgs);
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);

		if ((boolean) getCondition().getExpressionResult(program, actualArgs, function)) {
			if (getIfBody() instanceof AssignmentStatement)
				((AssignmentStatement) getIfBody()).assignLocalVariable(getStatementProgram(), actualArgs, function);

			else
				return getIfBody().evaluateInFunction(getStatementProgram(), actualArgs, function);
		}

		else if (getElseBody() != null) {
			if (getElseBody() instanceof AssignmentStatement)
				((AssignmentStatement) getElseBody()).assignLocalVariable(getStatementProgram(), actualArgs, function);

			else
				return getElseBody().evaluateInFunction(getStatementProgram(), actualArgs, function);
		}

		return null;
	}

	@Override
	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs,
			SourceLocation location) {
		if ((boolean) getCondition().getExpressionResult(program, actualArgs))
			getIfBody().skipEvaluationUntilLocation(program, actualArgs, location);

		else if (getElseBody() != null)
			getElseBody().skipEvaluationUntilLocation(program, actualArgs, location);
	}

}
