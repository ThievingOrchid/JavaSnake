package snake;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Adapted from an online tutorial by Jaryt Bustard
 * https://www.youtube.com/watch?v=S_n3lryyGZM
 * https://github.com/Jaryt/SnakeTutorial
 * 
 * @author Porter Reynolds
 */
public class Snake {

	private ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final short UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	private final int SCALEHEIGHT = Frame.getHeight() / SCALE;
	private final int SCALEWIDTH = Frame.getWidth() / SCALE;

	private int direction, tailLength;

	private Point head;

	public Snake() {
		setDirection(DOWN);
		setHead(new Point(0, 0));
		setTailLength(14);
	}

	public void startGame() {
		setDirection(DOWN);
		setHead(new Point(0, 0));
		getSnakeParts().clear();
		setTailLength(14);

	}

	/**
	 * moves the snake
	 * 
	 * @return true if the snake was able to move, false if not
	 */
	public boolean act() {
		getSnakeParts().add(new Point(getHead().x, getHead().y));
		switch (getDirection()) {
		case UP:
			setHead(new Point(getHead().x, getHead().y - 1));
			break;
		case DOWN:
			setHead(new Point(getHead().x, (getHead().y + 1) % SCALEHEIGHT));
			break;
		case LEFT:
			setHead(new Point(getHead().x - 1, getHead().y));
			break;
		case RIGHT:
			setHead(new Point((getHead().x + 1) % SCALEWIDTH, getHead().y));
			break;
		}
		boundsCheck();
		if (getSnakeParts().size() > getTailLength())// keep the snake at the
														// correct size
			getSnakeParts().remove(0);
		return !(getSnakeParts().contains(getHead()));

	}

	private void boundsCheck() {
		if (getHead().y < 0)
			setHead(new Point(getHead().x, SCALEHEIGHT));
		else if (getHead().x < 0)
			setHead(new Point(SCALEWIDTH, getHead().y));

	}

	public int getTailLength() {
		return tailLength;
	}

	public void setTailLength(int tailLength) {
		this.tailLength = tailLength;
	}

	public ArrayList<Point> getSnakeParts() {
		return snakeParts;
	}

	public Point getHead() {
		return head;
	}

	public void setHead(Point head) {
		this.head = head;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void keyPress(int i) {
		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT)
			direction = LEFT;
		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT)
			direction = RIGHT;
		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN)
			direction = UP;
		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP)
			direction = DOWN;

	}

	public void incTailLength() {
		tailLength++;
	}

}