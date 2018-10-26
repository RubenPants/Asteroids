package asteroids.part2.internal;

import java.awt.Graphics2D;
import java.awt.Image;

import asteroids.util.internal.ResourceUtils;

public class Explosion implements Drawable<DrawContext<?>> {

	private static final double INITIAL_TIME_LEFT = 1.2;
	private double timeLeft = INITIAL_TIME_LEFT;
	private int x, y;

	private static final Image explosions = ResourceUtils.loadImage("asteroids/resources/explosions.png");

	public Explosion(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	public boolean evolve(double dt) {
		timeLeft -= dt;
		return timeLeft <= 0;
	}

	@Override
	public void draw(DrawContext<?> ctx) {
		Graphics2D g2d = ctx.getGraphics();
		int NB_FRAMES_HORIZONTAL = 5;
		int NB_FRAMES_VERTICAL = 5;
		int FRAME_WIDTH = 64;
		int FRAME_HEIGHT = 64;
		int imageNumber = (int) ((INITIAL_TIME_LEFT - timeLeft) * NB_FRAMES_HORIZONTAL * NB_FRAMES_VERTICAL
				/ INITIAL_TIME_LEFT);
		int row = imageNumber / NB_FRAMES_HORIZONTAL;
		int column = imageNumber % NB_FRAMES_VERTICAL;
		int sx = column * FRAME_WIDTH;
		int sy = row * FRAME_HEIGHT;
		g2d.drawImage(explosions, x - FRAME_WIDTH / 2, y - FRAME_WIDTH / 2, x + FRAME_WIDTH / 2, y + FRAME_WIDTH / 2,
				sx, sy, sx + FRAME_WIDTH, sy + FRAME_HEIGHT, null);
	}
}
