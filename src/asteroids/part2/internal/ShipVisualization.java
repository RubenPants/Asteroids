package asteroids.part2.internal;

import static java.lang.Math.sin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import asteroids.model.Ship;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class ShipVisualization<F extends IFacade> extends Visualization<F, Ship> {

	public ShipVisualization(Color color, Ship ship, Image image) {
		super(color, ship, image);
	}

	@Override
	public void draw(DrawContext<F> ctx) {
		try {
			Graphics2D g2d = ctx.getGraphics();
			F facade = ctx.getFacade();
			double radius = facade.getShipRadius(getObject());
			double angle = -facade.getShipOrientation(getObject());
			double x = ctx.worldToScreenX(facade.getShipPosition(getObject())[0]);
			double y = ctx.worldToScreenY(facade.getShipPosition(getObject())[1]);
			g2d.setColor(getColor());
			if (getImage() == null) {
				g2d.drawOval((int) Math.round(x - radius), (int) Math.round(y - radius),
						(int) Math.round(2 * radius), (int) Math.round(2 * radius));
			} else {
				AffineTransform T = AffineTransform.getTranslateInstance(radius, radius);
				T.rotate(angle);
				T.translate(-radius, -radius);
				T.preConcatenate(AffineTransform.getTranslateInstance(x - radius, y - radius));
				g2d.drawImage(getImage(), T, null);
			}
			g2d.setStroke(new BasicStroke(2));
			g2d.drawLine((int) Math.round(x), (int) Math.round(y), (int) Math.round(x + Math.cos(angle) * radius),
					(int) Math.round(y + sin(angle) * radius));
			if (facade.isShipThrusterActive(getObject())) {
				Path2D.Double flame = new Path2D.Double();
				double flameAngle = Math.PI / 12;
				flame.moveTo(radius * Math.cos(Math.PI - flameAngle), radius * Math.sin(Math.PI - flameAngle));
				flame.lineTo(-radius - radius / 3, 0);
				flame.lineTo(radius * Math.cos(Math.PI + flameAngle), radius * Math.sin(Math.PI + flameAngle));
				flame.transform(AffineTransform.getRotateInstance(angle));
				flame.transform(AffineTransform.getTranslateInstance(x, y));
				g2d.setColor(Color.orange);
				g2d.draw(flame);
			}
		} catch (ModelException e) {
			ctx.handleError(e);
		}
	}
}
