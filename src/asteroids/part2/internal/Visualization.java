package asteroids.part2.internal;

import java.awt.Color;
import java.awt.Image;

public abstract class Visualization<F, T> implements Drawable<DrawContext<F>> {
	private final Color color;
	private final T object;
	private final Image image;

	public Visualization(Color color, T object, Image image) {
		if (object == null)
			throw new IllegalArgumentException("object null");
		this.color = color;
		this.object = object;
		this.image = image;
	}

	public Color getColor() {
		return color;
	}

	public T getObject() {
		return object;
	}

	public Image getImage() {
		return image;
	}
}
