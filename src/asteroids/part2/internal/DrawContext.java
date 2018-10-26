package asteroids.part2.internal;

import java.awt.Graphics2D;

import asteroids.util.ModelException;

public interface DrawContext<F> {
	
	public Graphics2D getGraphics();
	
	public F getFacade();

	public double worldToScreenX(double x);

	public double worldToScreenY(double y);

	public void handleError(ModelException e);
}
