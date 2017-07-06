package snake;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author Porter Reynolds
 */
public class Snake {

	private ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

	private int direction = DOWN, tailLength = 14;

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

	public boolean act() {
		getSnakeParts().add(new Point(getHead().x, getHead().y));

		if (getDirection() == UP) {
			if (getHead().y - 1 >= 0 && noTailAt(getHead().x, getHead().y - 1)) {
				setHead(new Point(getHead().x, getHead().y - 1));
			} else
				return true;
		}

		if (getDirection() == DOWN) {
			if (getHead().y + 1 < 67 && noTailAt(getHead().x, getHead().y + 1)) {
				setHead(new Point(getHead().x, getHead().y + 1));
			} else
				return true;
		}

		if (getDirection() == LEFT) {
			if (getHead().x - 1 >= 0 && noTailAt(getHead().x - 1, getHead().y)) {
				setHead(new Point(getHead().x - 1, getHead().y));
			} else
				return true;

		}

		if (getDirection() == RIGHT) {
			if (getHead().x + 1 < 80 && noTailAt(getHead().x + 1, getHead().y)) {
				setHead(new Point(getHead().x + 1, getHead().y));
			} else
				return true;
		}

		if (getSnakeParts().size() > getTailLength()) {
			getSnakeParts().remove(0);

		}
		// not sure whether to return true or false here
		return false;
	}

	public boolean noTailAt(int x, int y) {
		for (Point point : getSnakeParts()) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
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

	public void setSnakeParts(ArrayList<Point> snakeParts) {
		this.snakeParts = snakeParts;
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