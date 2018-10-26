package asteroids.program;

import java.util.List;

class BreakStatement extends MyStatement {

	/// CONSTRUCTOR ///

	protected BreakStatement() {
		//
	}	
	

	/// EVALUATION ///

	protected void evaluate(Program program, List<MyExpression> actualArgs) {
		throw new IllegalAccessError();
	}

	protected Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		throw new IllegalAccessError();
	}

}
