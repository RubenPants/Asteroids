package asteroids.program;

import java.awt.geom.IllegalPathStateException;
import java.util.List;
import asteroids.part3.programs.SourceLocation;

abstract class ActionStatement extends MyStatement {

	/// BASIC PROPERTIES ///
	
	private double decrement_time = 0.2;
	private SourceLocation sourcelocation;
	
	
	/// GETTERS ///
	
	private double getDecrementTime(){
		return decrement_time;
	}
	
	private SourceLocation getSourceLocation(){
		return sourcelocation;
	}
	
	
	/// SETTERS ///
	
	protected void setSourceLocation(SourceLocation location){
		sourcelocation = location;
	}
	
	
	/// EVALUATION ///

	@Override
	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);

		if (getStatementProgram().getTimeLeft() >= getDecrementTime()) {
			getStatementProgram().setMayExecute();
			getStatementProgram().addTime(-getDecrementTime());
			execute(program);
		}

		else {
			getStatementProgram().setSourceLocation(getSourceLocation());
			getStatementProgram().setMayNotExecute();

			throw new IllegalPathStateException();
		}
	}

	@Override
	protected void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs,
			SourceLocation location) {
		if (getSourceLocation().equals(location)) {
			evaluate(program, actualArgs);
		}
	}
	
	
	/// EXECUTION ///
	
	protected abstract void execute(Program program);
	
}
