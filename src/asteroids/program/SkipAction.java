package asteroids.program;

import asteroids.part3.programs.SourceLocation;

class SkipAction extends ActionStatement {

	/// CONSTRUCTOR ///
	
	protected SkipAction(SourceLocation location) {
		setSourceLocation(location);
	}

	
	/// EXECUTION ///
	
	@Override
	protected void execute(Program program) {
		//
	}

}
