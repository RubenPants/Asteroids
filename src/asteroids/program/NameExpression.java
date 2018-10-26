package asteroids.program;

public abstract class NameExpression extends MyExpression implements ArithmeticExpression{

	/// CONSTRUCTOR ///

	protected NameExpression(String name) {
		setName(name);
	}
	

	/// BASIC PROPERTIES ///
	
	private String name;

	
	/// GETTERS ///

	protected String getName() {
		return name;
	}

	
	/// SETTERS ///

	private void setName(String name) throws IllegalArgumentException {
		this.name = name;
	}

}
