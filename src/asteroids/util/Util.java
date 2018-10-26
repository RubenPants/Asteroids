package asteroids.util;

public class Util {
  public static final double EPSILON = 0.0001;

  public static boolean fuzzyEquals(double x, double y) {
    if (Double.isNaN(x) || Double.isNaN(y))
      return false;
    return Math.abs(x - y) <= EPSILON || Double.valueOf(x).equals(Double.valueOf(y));
  }

  public static boolean fuzzyLessThanOrEqualTo(double x, double y) {
    if (fuzzyEquals(x, y)) {
      return true;
    } else {
      return Double.compare(x, y) < 0;
    }
  }
  
  public static double absoluteError(double expected, double actual) {
    return Math.abs(expected - actual);
  }

  public static double relativeError(double expected, double actual) {
    return absoluteError(expected, actual) / Math.abs(expected);
  }
  
  public static boolean arrayFuzzyEquals(double[] expected, double[] actual) {
	  if (expected == null) {
		  return actual == null;
	  } else if (expected.length != actual.length) {
		  return false;
	  }
	  for (int i = 0; i < expected.length; i++) {
		  if (!fuzzyEquals(expected[i], actual[i])) {
			  return false;
		  }
	  }
	  return true;
  }
}
