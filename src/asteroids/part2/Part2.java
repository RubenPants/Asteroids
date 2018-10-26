package asteroids.part2;

import asteroids.part2.facade.IFacade;
import asteroids.part2.internal.AsteroidsFrame2;


/**
 * Play against (simple) bot-ships. Bullets will be of random size.
 * 
 * @author Sieben Bocklandt and Ruben Broekx
 */
public class Part2 {
	public static void main(String[] args) {
		boolean tryFullscreen = false;
		boolean enableSound = false;
		for (String arg : args) {
			if (arg.equals("-fullscreen")) {
				tryFullscreen = true;
			} else if (arg.equals("-nosound")) {
				enableSound = false;
			} else {
				System.out.println("unknown option: " + arg);
				return;
			}
		}

		IFacade facade = new asteroids.facade.Facade();
		AsteroidsFrame2.run(facade, tryFullscreen, enableSound);
	}
}
