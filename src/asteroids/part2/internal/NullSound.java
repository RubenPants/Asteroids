package asteroids.part2.internal;

/**
 * Sound manager that ignores all requests.
 */
public class NullSound implements Sound {

  @Override
  public void play(String name) {
  }

  @Override
  public void stop(String name) { 
  }

  @Override
  public void loop(String name) {
  }

  @Override
  public void start() {
  }
}
