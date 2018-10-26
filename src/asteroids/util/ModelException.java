package asteroids.util;

@SuppressWarnings("serial")
/**
 * <code>Facade</code> is not allowed to throw exceptions except for <code>ModelException</code>.
 * 
 * Do not use ModelException outside of <code>Facade</code>.
 */
public class ModelException extends Exception {
  public ModelException(String message) {
    super(message);
  }

  public ModelException(Throwable nested) {
    super(nested);
  }
}
