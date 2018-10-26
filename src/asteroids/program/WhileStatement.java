package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

class WhileStatement extends MyStatement {
	
	/// CONSTRUCTOR ///

	protected WhileStatement(BooleanExpression condition, MyStatement body) {
		setCondition(condition);
		setBody(body);
	}
	
	
	/// BASIC PROPERTIES ///

	private BooleanExpression condition;
	private MyStatement body;
	private boolean broken = false;
	
	
	/// GETTERS ///
	
	private MyStatement getBody() {
		return body;
	}
	
	private boolean getBrokenState() {
		return broken;
	}
	
	private MyExpression getCondition() {
		return (MyExpression) condition;
	}
	
	
	/// SETTERS ///

	private void setBody(MyStatement body) {
		this.body = body;
	}

	private void setBrokenTrue() {
		broken = true;
	}

	private void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}
	
	
	/// CHECKERS ///

	private boolean isNotBroken() {
		return !getBrokenState();
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);
		
		while ((boolean) getCondition().getExpressionResult(program, actualArgs) && isNotBroken()) {
			try {
				getBody().evaluate(program, actualArgs);
			} catch (IllegalAccessError error) {
				setBrokenTrue();
			}
		}
		
	}

	protected void evaluateWhileInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);

		while ((boolean) getCondition().getExpressionResult(program, actualArgs, function) && isNotBroken()) {
			if (getBody() instanceof AssignmentStatement)
				((AssignmentStatement) getBody()).assignLocalVariable(program, actualArgs, function);
			else {
				try {
					getBody().evaluateInFunction(program, actualArgs, function);
				} catch (IllegalAccessError error) {
					setBrokenTrue();
				}
			}
		}

	}

	@Override
	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs, SourceLocation location) {
		while ((boolean) getCondition().getExpressionResult(program, actualArgs) && isNotBroken()) {
			try {
				getBody().skipEvaluationUntilLocation(program, actualArgs, location);
			} catch (IllegalAccessError error) {
				setBrokenTrue();
			}
		}
	}

}
