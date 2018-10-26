package asteroids.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class FunctionExpression extends MyExpression implements ArithmeticExpression {

	/// CONSTRUCTOR ///

	protected FunctionExpression(String functionName, List<MyExpression> actualArgs) {
		setFunctionName(functionName);
		setArguments(actualArgs);
	}

	
	/// BASIC PROPERTIES ///
	
	private String functionName;
	private List<MyExpression> actualArgs;

	
	/// GETTERS ///

	private List<MyExpression> getActualArgs() {
		return actualArgs;
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		Map<String, Object> old_local_variables = getFunction().getFunctionLocalVariables();
		List<MyExpression> newActualArgs = updateArgs(program, actualArgs, function);

		getFunction().resetLocalVariables();
		Object result = evaluateFunctionBody(getFunction().getFunctionBody(), newActualArgs, getFunction());
		getFunction().setLocalVariables(old_local_variables);

		return result;
	}

	private MyFunction getFunction() {
		if (getExpressionProgram().getProgramFunctions().containsKey(getFunctionName()))
			return getExpressionProgram().getProgramFunctions().get(getFunctionName());

		throw new IllegalArgumentException();
	}

	private String getFunctionName() {
		return functionName;
	}

	
	/// SETTERS ///

	private void setArguments(List<MyExpression> actualArgs) {
		this.actualArgs = actualArgs;
	}

	private void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	/// CHECKERS ///
	
	private static boolean isValidFunctionBody(MyStatement body) {
		return (body.containsStatement("ReturnStatement")
				&& ((body instanceof ReturnStatement) || (body instanceof SequenceStatement)
						|| (body instanceof IfElseStatement) || (body instanceof WhileStatement)));
	}

	
	/// HELP FUNCTIONS ///

	private List<MyExpression> updateArgs(Program program, List<MyExpression> oldActualArgs, MyFunction function) {
		List<MyExpression> newActualArgs = new ArrayList<MyExpression>();

		for (MyExpression actualArg : getActualArgs())
			newActualArgs.add(actualArg);

		for (int i = 0; i < newActualArgs.size(); i++) {
			MyExpression actualArg = newActualArgs.get(i);

			if (!(actualArg instanceof DoubleLiteralExpression)) {
				Double value = (Double) actualArg.getExpressionResult(program, oldActualArgs, function);
				MyExpression newArg = new DoubleLiteralExpression(value);
				newActualArgs.set(i, newArg);
			}
		}
		
		return newActualArgs;
	}

	
	/// EVALUATION ///

	private Object evaluateFunctionBody(MyStatement body, List<MyExpression> actualArgs, MyFunction function)
			throws IllegalArgumentException {
		if (isValidFunctionBody(body))
			return body.evaluateInFunction(getExpressionProgram(), actualArgs, function);
		
		else
			throw new IllegalArgumentException("FunctionInvocationExpression --> Else statement in evaluateFunctionBody");
	}

}
