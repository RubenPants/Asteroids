package asteroids.part1.internal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

import static java.lang.Math.*;

@SuppressWarnings("serial")
public class AsteroidsView extends JPanel {

	private Set<Ship> ships;
	private IFacade facade;
	private Ship selected;
	private Iterator<Ship> iterator;
	private boolean thrust = false;
	private boolean showCollisions = false;

	public AsteroidsView(Set<Ship> ships, IFacade facade) {
		super(true);
		this.ships = ships;
		this.iterator = ships.iterator();
		this.selected = iterator.next();
		this.facade = facade;
		this.setBackground(Color.BLACK);
	}

	public void selectNext() {
		if (!iterator.hasNext()) {
			iterator = ships.iterator();
		}
		selected = iterator.next();
	}

	public Ship getSelected() {
		return selected;
	}

	public void setThrust(boolean thrust) {
		this.thrust = thrust;
	}

	public void setShowCollisions(boolean show) {
		this.showCollisions = show;
	}

	public boolean getShowCollisions() {
		return showCollisions;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Ship ship : ships) {
			drawShip(ship, g2d);
		}
		if (showCollisions) {
			drawCollision(g2d);
		}
	}

	private void drawCollision(Graphics2D g2d) {

		double min_dt = Double.POSITIVE_INFINITY;
		Ship min_ship = null;
		// find next collision, if any
		for (Ship ship : ships) {
			try {
				if (!facade.overlap(selected, ship)) {
					double dt = facade.getTimeToCollision(selected, ship);
					if (dt < min_dt) {
						min_dt = dt;
						min_ship = ship;
					}
				}
			} catch (ModelException e) {
				e.printStackTrace();
			}
		}
		try {
			Ship first = min_ship;
			if (first != null && !facade.overlap(selected, first)) {
				// draw circles
				double dt = min_dt; // facade.getTimeToCollision(selected,
									// first);
				double[] xy = facade.getShipPosition(selected);
				double x = xy[0], y = xy[1];
				double[] vxy = facade.getShipVelocity(selected);
				double vx = vxy[0], vy = vxy[1];

				int x1_Collision = (int) (x + dt * vx);
				int y1_Collision = getHeight() - (int) (y + dt * vy);
				int radius1 = (int) facade.getShipRadius(selected);
				float[] dashPattern = { 10, 5 };
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
				g2d.drawOval(x1_Collision - radius1, y1_Collision - radius1, 2 * radius1, 2 * radius1);
				g2d.drawLine((int) x, getHeight() - (int) y, x1_Collision, y1_Collision);

				double[] first_xy = facade.getShipPosition(first);
				double first_x = first_xy[0], first_y = first_xy[1];
				double[] first_vxy = facade.getShipVelocity(first);
				double first_vx = first_vxy[0], first_vy = first_vxy[1];

				int x2_Collision = (int) (first_x + dt * first_vx);
				int y2_Collision = getHeight() - (int) (first_y + dt * first_vy);

				int radius2 = (int) facade.getShipRadius(first);
				g2d.drawOval(x2_Collision - radius2, y2_Collision - radius2, 2 * radius2, 2 * radius2);
				g2d.drawLine((int) first_x, getHeight() - (int) first_y, x2_Collision, y2_Collision);
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}
		// draw cross
		for (Ship ship : ships) {
			try {
				if (!facade.overlap(selected, ship)) {
					double[] colPos = facade.getCollisionPosition(selected, ship);
					if (colPos != null) {
						int x = (int) colPos[0];
						int y = getHeight() - (int) colPos[1];
						g2d.setColor(Color.WHITE);
						g2d.drawLine(x - 5, y, x + 5, y);
						g2d.drawLine(x, y - 5, x, y + 5);
					}
				}
			} catch (ModelException e) {
				e.printStackTrace();
			}
		}
	}

	protected void drawShip(Ship ship, Graphics2D g2d) {
		try {
			double radius = facade.getShipRadius(ship);
			double angle = -facade.getShipOrientation(ship);
			double[] xy = facade.getShipPosition(ship);
			double x = xy[0], y = getHeight() - xy[1];

			if (ship == selected) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.WHITE);
			}
			g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
			g2d.drawLine((int) x, (int) y, (int) (x + Math.cos(angle) * radius), (int) (y + sin(angle) * radius));
			if (thrust && ship == selected) {
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
			e.printStackTrace();
		}
	}
}
