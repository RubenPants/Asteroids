package asteroids.part2.internal;

public interface Sound {
  public void play(String name);

  public void stop(String name);

  public void loop(String name);

  public void start();
}
