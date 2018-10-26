package asteroids.program;

import java.awt.geom.IllegalPathStateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class Program {

	/// CONSTRUCTOR ///

	protected Program(List<MyFunction> functions, MyStatement main) {
		setFunctions(functions);
		setMain(main);
		main.setStatementProgram(this);
	}

	
	/// BASIC PROPERTIES ///

	private MyStatement main;
	private double time_left = 0;
	private boolean first_time = true;
	private SourceLocation location;
	private boolean mayExecute = false;
	
	
	/// GETTERS ///

	private MyStatement getMain() {
		return main;
	}

	protected boolean getMayExecute() {
		return mayExecute;
	}

	private List<Object> getPrintOuts() {
		return print_outs;
	}

	protected Map<String, MyFunction> getProgramFunctions() {
		return functions;
	}

	protected Ship getProgramShip() {
		return ship;
	}

	protected Map<String, Object> getProgramVariables() {
		return variables;
	}

	protected SourceLocation getSourceLocation() {
		return this.location;
	}

	protected double getTimeLeft() {
		return time_left;
	}
	
	
	/// SETTERS ///

	private void setFunctions(List<MyFunction> functions) {
		for (MyFunction function : functions) {
			this.functions.put(function.getFunctionName(), function);
			function.setFunctionProgram(this);
		}
	}

	private void setMain(MyStatement main) {
		this.main = main;
	}

	protected void setMayExecute() {
		mayExecute = true;
	}

	protected void setMayNotExecute() {
		mayExecute = false;
	}

	public void setProgramShip(Ship ship) {
		this.ship = ship;
	}

	protected void setSourceLocation(SourceLocation location) {
		this.location = location;
	}


	/// ADDERS ///

	protected void addPrintOut(Object object) {
		print_outs.add(object);
	}

	protected void addTime(double dt) {
		time_left += dt;
	}

	protected void addVariable(String string, Object object) {
		variables.put(string, object);
	}

	
	/// EXECUTION ///
	
	public List<Object> execute(double dt) {
		addTime(dt);

		if (first_time) {
			first_time = false;

			try {
				getMain().evaluate(this);
				
				return getPrintOuts();
			} catch (IllegalPathStateException illegalPathStateException) {
				return null;
			}
		} else {
			try {
				getMain().skipEvaluationUntilLocation(this, null, getSourceLocation());
				
				return getPrintOuts();
			} catch (IllegalPathStateException e) {
				return null;
			}
		}
	}

	
	/// RELATIONS WITH OTHER CLASSES ///

	private Ship ship;
	private Map<String, MyFunction> functions = new HashMap<String, MyFunction>();
	private Map<String, Object> variables = new HashMap<String, Object>();
	private List<Object> print_outs = new ArrayList<>();

}
