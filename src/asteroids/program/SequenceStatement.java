package asteroids.program;

import java.util.ArrayList;
import java.util.List;

import asteroids.part3.programs.SourceLocation;

class SequenceStatement extends MyStatement {

	/// CONSTRUCTOR ///

	protected SequenceStatement(List<MyStatement> statements) {
		setStatements(statements);
	}

	
	/// BASIC PROPERTIES ///
	
	private List<MyStatement> statements = new ArrayList<MyStatement>();

	
	/// GETTERS ///

	private List<MyStatement> getStatements() {
		return statements;
	}
	

	/// SETTERS ///

	private void setStatements(List<MyStatement> statements) {
		this.statements = statements;
	}

	
	/// CHECKERS ///
	
	protected boolean containsStatement(String name) {
		boolean contains = false;

		for (MyStatement statement : statements)
			if (statement.containsStatement(name))
				contains = true;

		return contains;
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		for (MyStatement statement : statements) {
			statement.evaluate(program, actualArgs);
		}
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);

		for (MyStatement statement : getStatements()) {
			if (statement instanceof AssignmentStatement)
				((AssignmentStatement) statement).assignLocalVariable(program, actualArgs, function);

			else if (statement instanceof WhileStatement)
				((WhileStatement) statement).evaluateWhileInFunction(program, actualArgs, function);

			else
				return statement.evaluateInFunction(program, actualArgs, function);
		}
		return null;
	}

	@Override
	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs,
			SourceLocation location) {
		for (MyStatement statement : statements) {
			if (program.getMayExecute())
				statement.evaluate(program, actualArgs);
			else
				statement.skipEvaluationUntilLocation(program, actualArgs, location);
		}
	}

}
