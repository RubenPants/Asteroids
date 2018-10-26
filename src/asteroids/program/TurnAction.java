package asteroids.program;

import asteroids.part3.programs.SourceLocation;
import asteroids.program.ActionStatement;

class TurnAction extends ActionStatement {

	/// CONSTRUCTOR ///

	public TurnAction(MyExpression angle, SourceLocation location) {
		setAngle(angle);
		setSourceLocation(location);
	}

	
	/// BASIC PROPERTIES ///
	
	private MyExpression angle;

	
	/// GETTERS ///
	
	private MyExpression getAngle() {
		return angle;
	}

	
	/// SETTERS ///
	
	private void setAngle(MyExpression angle) {
		this.angle = angle;
	}

	
	/// EXECUTION ///
	
	public void execute(Program program) {
		try {
			getStatementShip().turn((double) getAngle().getExpressionResult(program));
		} catch (AssertionError error) {
			throw new IllegalArgumentException();
		}
	}

}
