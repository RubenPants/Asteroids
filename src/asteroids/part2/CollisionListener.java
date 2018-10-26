package asteroids.part2;

/**
 * The listener interface for receiving collision events.
 */
public interface CollisionListener {
  /**
   * Invoked when <code>entity</code> is about to collide with a boundary.
   */
  public void boundaryCollision(Object entity, double x, double y);
  
  /**
   * Invoked when <code>entity1</code> is about to collide with <code>object2</code>.
   */
  public void objectCollision(Object entity1, Object entity2, double x, double y);
}
