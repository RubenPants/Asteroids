package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class FireAction extends ActionStatement {

	/// CONSTRUCTOR ///

	public FireAction(SourceLocation location) {
		setSourceLocation(location);
	}

	
	/// EXECUTION ///

	@Override
	public void execute(Program program) {
		getStatementShip().fireBullet();
		setStatementProgram(program);
	}

}
