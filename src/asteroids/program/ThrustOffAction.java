package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class ThrustOffAction extends ActionStatement {

	/// CONSTRUUCTOR ///
	
	public ThrustOffAction(SourceLocation location) {
		setSourceLocation(location);
	}
	
	
	/// EXECUTION ///

	@Override
	public void execute(Program program) {
		getStatementShip().setThrusterActive(false);
	}

}
