package asteroids.part3;

import asteroids.part3.facade.IFacade;
import asteroids.part3.internal.AsteroidsFrame3;

/**
 * Play in a field with multiple asteroids that must be destroyed.
 * 
 * @author Sieben Bocklandt and Ruben Broekx
 */
public class Part3 {
	public static void main(String[] args) {
		boolean tryFullscreen = true;
		boolean enableSound = true;
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
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
		AsteroidsFrame3.run(facade, tryFullscreen, enableSound);
	}
}
