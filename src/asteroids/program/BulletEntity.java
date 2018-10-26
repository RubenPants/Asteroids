package asteroids.program;

import java.util.List;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;

class BulletEntity extends EntityExpression {

	/// CONSTRUCTOR ///

	protected BulletEntity() throws IllegalArgumentException {
		//
	}
	
	
	/// GETTERS ///

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		Set<? extends Entity> bullets = getWorldEntity("Bullet");

		bullets.removeIf(bullet -> !isFiredFromShip((Bullet) bullet));
		
		return bullets.stream().findAny().orElse(null);
	}

	
	/// CHECKERS ///

	private boolean isFiredFromShip(Bullet bullet) {
		return (getExpressionShip().equals(bullet.getBulletSource()));
	}

}
