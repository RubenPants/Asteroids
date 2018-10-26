package asteroids.part2.internal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import asteroids.util.internal.ResourceUtils;

@SuppressWarnings("serial")
public class AsteroidsMenu2 extends JPanel implements KeyListener {

	private String[] menu_options;
	private int selectedIndex = 0;
	private final AsteroidsFrame2<?> game;
	private Image background;

	public AsteroidsMenu2(AsteroidsFrame2<?> game) {
		this.game = game;
		addKeyListener(this);
		setBackground(Color.BLACK);
		background = ResourceUtils.loadImage("asteroids/resources/menu-background.jpg");
		assert background != null : "No background found!";
		background = background.getScaledInstance(game.getWidth(), game.getHeight(), Image.SCALE_DEFAULT);
		this.menu_options = createOptions();
	}
	
	public AsteroidsFrame2<?> getGame() {
		return game;
	}

	protected String[] createOptions() {
		return new String[] { "Play", "Exit" };
	}

	@Override
	public boolean isFocusable() {
		return true;
	}

	private void drawCenteredString(Graphics2D g2d, String txt, int y) {
		int width = getWidth();
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(txt, g2d);
		g2d.drawString(txt, width / 2 - (int) bounds.getCenterX(), y);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(background, 0, 0, game.getWidth(), game.getHeight(), null);
		g2d.setFont(g2d.getFont().deriveFont(150f));
		int titleHeight = g2d.getFontMetrics().getHeight();
		g2d.setColor(Color.WHITE);
		int baseHeight = 250;
		drawCenteredString(g2d, "Asteroids", baseHeight);
		g2d.setFont(g2d.getFont().deriveFont(40f));
		int optionHeight = g2d.getFontMetrics().getHeight();
		for (int i = 0; i < menu_options.length; i++) {
			if (i == selectedIndex) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.WHITE);
			}
			drawCenteredString(g2d, menu_options[i], (int) (baseHeight + titleHeight + optionHeight * 1.5 * i));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(game.getWidth(), game.getHeight());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		case KeyEvent.VK_UP:
			selectedIndex = selectedIndex == 0 ? menu_options.length - 1 : selectedIndex - 1;
			repaint();
			game.getSound().play("blip");
			break;
		case KeyEvent.VK_DOWN:
			selectedIndex = (selectedIndex + 1) % menu_options.length;
			repaint();
			game.getSound().play("blip");
			break;
		case KeyEvent.VK_ENTER:
			game.getSound().stop("menu-theme");
			optionSelected(selectedIndex);
			break;
		}
	}

	protected void optionSelected(int selectedIndex) {
		switch (selectedIndex) {
		case 0:
			game.startGame();
			break;
		case 1:
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void reset() {
		game.getSound().stop("game-theme");
		game.getSound().loop("menu-theme");
		repaint();
	}
}
