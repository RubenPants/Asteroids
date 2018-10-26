package asteroids.program;

import java.util.List;

class DoubleLiteralExpression extends MyExpression implements ArithmeticExpression {

	/// CONSTRUCTOR ///

	protected DoubleLiteralExpression(double value) {
		setValue(value);
	}

	
	/// BASIC PROPERTIES ///
	
	private double value;
	
	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return getValue();
	}

	private double getValue() {
		return value;
	}
	

	/// SETTERS ///

	private void setValue(double value) throws IllegalArgumentException {
		this.value = value;
	}

}
