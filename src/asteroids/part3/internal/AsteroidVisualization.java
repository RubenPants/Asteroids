package asteroids.part3.internal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import asteroids.model.Asteroid;
import asteroids.model.World;
import asteroids.part2.internal.DrawContext;
import asteroids.part2.internal.Visualization;
import asteroids.part3.facade.IFacade;
import asteroids.util.ModelException;

public class AsteroidVisualization extends Visualization<IFacade, Asteroid> {

	public AsteroidVisualization(Asteroid asteroid, Image image) {
		super(Color.WHITE, asteroid, image);
	}

	@Override
	public void draw(DrawContext<IFacade> ctx) {
		IFacade facade = ctx.getFacade();

		Graphics2D g2d = ctx.getGraphics();

		try {
			World world = facade.getAsteroidWorld(getObject());
			if (world != null) {
				double radius = facade.getAsteroidRadius(getObject());
				double[] xy = facade.getAsteroidPosition(getObject());
				xy[1] = facade.getWorldSize(world)[1] - xy[1];
				double x = xy[0], y = xy[1];
				if (getImage() == null) {
					g2d.setColor(getColor());
					g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
				} else {
					AffineTransform T = AffineTransform.getScaleInstance(2 * radius / getImage().getWidth(null),
							2 * radius / getImage().getHeight(null));
					T.preConcatenate(AffineTransform.getTranslateInstance(x - radius, y - radius));
					g2d.drawImage(this.getImage(), T, null);
				}
			}
		} catch (ModelException e) {
			ctx.handleError(e);
		}
	}
}
