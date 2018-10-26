package asteroids.program;

import java.util.List;

class VariableExpression extends NameExpression {

	/// CONSTRUCTOR ///

	protected VariableExpression(String variableName) {
		super(variableName);
	}

	
	/// GETTERS ///

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);
		Object result = null;

		if (function != null)
			result = function.getFunctionLocalVariables().get(getName());
		
		if (result == null) {
			result = getExpressionProgram().getProgramVariables().get(getName());
			
			if (result == null)
				throw new IllegalArgumentException();
		}
		
		return result;
	}

}
