package asteroids.program;

import java.util.HashMap;
import java.util.Map;

public class MyFunction {

	/// CONSTRUCTOR ///

	protected MyFunction(String functionName, MyStatement body) {
		setFunctionName(functionName);
		setBody(body);
	}

	
	/// GETTERS ///

	protected MyStatement getFunctionBody() {
		return body;
	}

	protected Map<String, Object> getFunctionLocalVariables() {
		return local_variables;
	}

	protected String getFunctionName() {
		return functionName;
	}

	protected Program getFunctionProgram() {
		return this.program;
	}
	
	
	/// SETTERS ///

	protected void setBody(MyStatement body) {
		this.body = body;
	}

	protected void setLocalVariables(Map<String, Object> local_variables) {
		this.local_variables = local_variables;
	}

	protected void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	protected void setFunctionProgram(Program program) {
		this.program = program;
	}	

	
	/// ADDERS ///

	protected void addLocalVariable(String localVariableName, Object localVariable) {
		local_variables.put(localVariableName, localVariable);
	}

	
	/// HELP FUNCTIONS ///
	
	protected void resetLocalVariables() {
		setLocalVariables(new HashMap<String, Object>());
	}
	

	/// RELATIONS WITH OTHER CLASSES ///
	
	private Program program;
	private String functionName;
	private MyStatement body;
	private Map<String, Object> local_variables = new HashMap<String, Object>();

}
