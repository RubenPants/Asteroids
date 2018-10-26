package asteroids.part2.internal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Set;
import java.util.function.Supplier;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class CollisionVisualization<F extends IFacade> extends Visualization<F, Ship> {

	private Supplier<Boolean> showCollisions;

	public CollisionVisualization(Ship ship, Supplier<Boolean> showCollisions) {
		super(null, ship, null);
		this.showCollisions = showCollisions;
	}

	@Override
	public void draw(DrawContext<F> ctx) {
		if (!showCollisions.get()) {
			return;
		}
		Graphics2D g2d = ctx.getGraphics();

		try {
			F facade = ctx.getFacade();
			double min_dt = Double.POSITIVE_INFINITY;

			Ship selected = getObject();
			World world = facade.getShipWorld(selected);
			if (world == null) {
				return;
			}

			double dt = facade.getTimeCollisionBoundary(selected);
			if (dt < min_dt) {
				min_dt = dt;
			}

			Object min_ship = null;
			Set<? extends Object> ships = facade.getEntities(world);

			// find next collision, if any
			for (Object entity : ships) {
				if (entity == selected)
					continue;

				try {
					if (!(entity instanceof Ship) || !facade.overlap(selected, (Ship) entity)) {
						dt = facade.getTimeCollisionEntity(selected, entity);
						if (dt < min_dt) {
							min_dt = dt;
							min_ship = entity;
						}
					}
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
			try {
				// draw circles
				dt = min_dt;
				double[] xy = facade.getShipPosition(selected);
				double x = xy[0], y = xy[1];
				double[] vxy = facade.getShipVelocity(selected);
				double vx = vxy[0], vy = vxy[1];

				double x1_Collision = x + dt * vx;
				double y1_Collision = y + dt * vy;
				int radius1 = (int) facade.getShipRadius(selected);
				float[] dashPattern = { 10, 5 };
				g2d.setColor(Color.LIGHT_GRAY);
				Stroke oldStroke = g2d.getStroke();
				try {
					g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
					g2d.drawOval((int) Math.round(ctx.worldToScreenX(x1_Collision) - radius1),
							(int) Math.round(ctx.worldToScreenY(y1_Collision) - radius1), 2 * radius1, 2 * radius1);
					g2d.drawLine((int) Math.round(ctx.worldToScreenX(x)), (int) Math.round(ctx.worldToScreenY(y)),
							(int) Math.round(ctx.worldToScreenX(x1_Collision)),
							(int) Math.round(ctx.worldToScreenY(y1_Collision)));

					Object first = min_ship;
					if (first != null) {
						double[] first_xy = getPosition(facade, first);
						if (first_xy != null) {
							double first_x = first_xy[0], first_y = first_xy[1];
							double[] first_vxy = getVelocity(facade, first);
							double first_vx = first_vxy[0], first_vy = first_vxy[1];

							double x2_Collision = first_x + dt * first_vx;
							double y2_Collision = first_y + dt * first_vy;

							int radius2 = (int) getRadius(facade, first);
							g2d.drawOval((int) Math.round(ctx.worldToScreenX(x2_Collision) - radius2),
									(int) Math.round(ctx.worldToScreenY(y2_Collision) - radius2), 2 * radius2,
									2 * radius2);
							g2d.drawLine((int) Math.round(ctx.worldToScreenX(first_x)),
									(int) Math.round(ctx.worldToScreenY(first_y)),
									(int) Math.round(ctx.worldToScreenX(x2_Collision)),
									(int) Math.round(ctx.worldToScreenY(y2_Collision)));
						}
					}
				} finally {
					g2d.setStroke(oldStroke);
				}
			} catch (ModelException e) {
				e.printStackTrace();
			}
			// draw cross
			for (Object entity : ships) {
				try {
					if (!(entity instanceof Ship) || !(facade.overlap(selected, (Ship) entity))) {
						double[] colPos = facade.getPositionCollisionEntity(selected, entity);
						if (colPos != null) {
							int x = (int) Math.round(ctx.worldToScreenX(colPos[0]));
							int y = (int) Math.round(ctx.worldToScreenY(colPos[1]));
							g2d.setColor(Color.WHITE);
							g2d.drawLine(x - 5, y, x + 5, y);
							g2d.drawLine(x, y - 5, x, y + 5);
						}
					}
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}
	}

	protected double[] getPosition(F facade, Object object) throws ModelException {
		if (object instanceof Ship) {
			return facade.getShipPosition((Ship) object);
		} else if (object instanceof Bullet) {
			return facade.getBulletPosition((Bullet) object);
		}
		System.out.println("Unknown object type: " + object.getClass() + " ( in " + this.getClass() + ")");
		return null;
	}

	protected double[] getVelocity(F facade, Object object) throws ModelException {
		if (object instanceof Ship) {
			return facade.getShipVelocity((Ship) object);
		} else if (object instanceof Bullet) {
			return facade.getBulletVelocity((Bullet) object);
		}
		System.out.println("Unknown object type: " + object.getClass() + " ( in " + this.getClass() + ")");
		return null;
	}

	protected double getRadius(F facade, Object object) throws ModelException {
		if (object instanceof Ship) {
			return facade.getShipRadius((Ship) object);
		} else if (object instanceof Bullet) {
			return facade.getBulletRadius((Bullet) object);
		}
		System.out.println("Unknown object type: " + object.getClass() + " ( in " + this.getClass() + ")");
		return 0;
	}
}
