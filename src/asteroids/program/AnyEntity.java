package asteroids.program;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;

class AnyEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected AnyEntity() throws IllegalArgumentException {
		//
	}

	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		Set<Entity> entities = getWorldEntities();

		return entities.stream().findAny().orElse(null);
	}
}
