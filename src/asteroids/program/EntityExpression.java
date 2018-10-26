package asteroids.program;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;

abstract class EntityExpression extends MyExpression {

	/// GETTERS ///
	
	protected Entity getClosestEntity(Set<? extends Entity> set){
		Ship ship = getExpressionShip();
		Entity result = null;
		
		if (!set.isEmpty()){
			// Makes a map with the distances as keys and the entities that have these distances in a list as values.
			Map<Double, List<Entity>> ordered_map = set.stream().collect(Collectors.groupingBy(e -> ship.getDistanceBetween(e)));
			
			// get the smallest distance.
			Double key = ordered_map.keySet().stream().reduce(Double::min).get();
			
			// get the first entity with this minimal distance.
			result = (ordered_map.get(key)).get(0);
		}
		return result;
	}
	
	private World getWorld() {
		return getExpressionShip().getEntityWorld();
	}
	
	protected Set<Entity> getWorldEntities() {
		return getWorld().getWorldEntities();
	}
	
	protected Set<? extends Entity> getWorldEntity(String entity) {
		return getWorld().getWorldSpecificEntities(entity);
	}
}
