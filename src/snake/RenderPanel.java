
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * @author Porter Reynolds
 */
public class RenderPanel extends JPanel {

	public static final Color GREEN = new Color(1666073);
	private final int SCALE;
	
	private Frame frame;
	private Snake snake;
	
	public RenderPanel(Frame frame, Snake snake){
		this.frame=frame;
		this.snake=snake;
		this.SCALE=Frame.getScale();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		
	

		g.setColor(GREEN);

		g.fillRect(0, 0, 800, 700);

		g.setColor(Color.BLUE);

		for (Point point : snake.getSnakeParts()) {
			g.fillRect(point.x * SCALE, point.y * SCALE, SCALE, SCALE);
		}

		g.fillRect(snake.getHead().x * SCALE, snake.getHead().y * SCALE, SCALE,
				SCALE);

		g.setColor(Color.RED);

		g.fillRect(frame.getCherry().x * SCALE, frame.getCherry().y * SCALE, SCALE,
				SCALE);

		String string = "Score: " + frame.getScore() + ", Length: " + snake.getTailLength() + ", Time: "
				+ frame.getTime() / 20;

		g.setColor(Color.white);

		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

		string = "Game Over!";

		if (frame.isOver()) {
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) frame.getDim().getHeight() / 4);
		}

		string = "Paused!";

		if (frame.isPaused() && !frame.isOver()) {
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) frame.getDim().getHeight() / 4);
		}
	}
}
