package asteroids.program;

import java.util.List;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public abstract class MyStatement {

	/// GETTERS ///

	protected Program getStatementProgram() {
		return this.program;
	}

	protected Ship getStatementShip() {
		return this.getStatementProgram().getProgramShip();
	}

	
	/// SETTERS ///

	protected void setStatementProgram(Program program) {
		this.program = program;
	}
	
	
	/// CHECKERS ///

	protected boolean containsStatement(String name){
		return getClass().getSimpleName().equals(name);
	}

	
	/// EVALUATION ///

	protected abstract void evaluate(Program program, List<MyExpression> actualArgs);

	protected void evaluate(Program program) {
		evaluate(program, null);
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		throw new IllegalArgumentException();
	}

	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs,
			SourceLocation location) {
		//
	}

	
	/// RELATIONS WITH OTHER CLASSES ///

	private Program program = null;

}
