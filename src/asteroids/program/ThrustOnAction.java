package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class ThrustOnAction extends ActionStatement {

	/// CONSTRUCTOR ///
	
	public ThrustOnAction(SourceLocation location) {
		setSourceLocation(location);
	}

	
	/// EXECUTION ///
	
	@Override
	public void execute(Program program) {
		getStatementShip().setThrusterActive(true);
	}

}
