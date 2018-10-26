package asteroids.part2.internal;

import java.awt.Color;

public class CompositeVisualization<F, T> extends Visualization<F, T> {

	private final Visualization<F, T>[] vis;

	@SafeVarargs
	public CompositeVisualization(T object, Visualization<F, T>... vis) {
		super(null, object, null);
		this.vis = vis;
	}

	@Override
	public void draw(DrawContext<F> context) {
		for (Visualization<F, T> v : vis) {
			v.draw(context);
		}
	}
	
	@Override
	public Color getColor() {
		for (Visualization<F, T> v : vis) {
			if (v.getColor() != null) {
				return v.getColor();
			}
		}
		return super.getColor();
	}

}
