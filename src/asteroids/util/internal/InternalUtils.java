package asteroids.util.internal;

public class InternalUtils {

	/**
	 * Convert the given delta value (in radians) to a value that, when added to
	 * the given base angle, yields a result in the interval [0, 2*PI).
	 * 
	 * @param baseAngle
	 * @param delta
	 * @return
	 */
	public static double toProperAngleDelta(double baseAngle, double delta) {
		while (baseAngle + delta >= 2 * Math.PI) {
			delta -= 2 * Math.PI;
		}
		while (baseAngle + delta < 0) {
			delta += 2 * Math.PI;
		}
		assert 0 <= baseAngle+delta && baseAngle+delta < 2*Math.PI;
		return delta;
	}
}
