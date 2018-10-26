package asteroids.part2.internal;

import java.awt.Color;
import java.awt.Graphics2D;

import asteroids.model.Bullet;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class BulletVisualization<F extends IFacade> extends Visualization<F, Bullet> {

	public BulletVisualization(Color color, Bullet bullet) {
		super(color, bullet, null);
	}

	@Override
	public void draw(DrawContext<F> ctx) {
		Graphics2D g2d = ctx.getGraphics();
		try {
			F facade = ctx.getFacade();
			double radius = facade.getBulletRadius(getObject());
			double x = ctx.worldToScreenX(facade.getBulletPosition(getObject())[0]);
			double y = ctx.worldToScreenY(facade.getBulletPosition(getObject())[1]);
			g2d.setColor(getColor());
			g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
		} catch (ModelException e) {
			ctx.handleError(e);
		}
	}
}
