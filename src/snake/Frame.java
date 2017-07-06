package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Frame implements ActionListener, KeyListener {
	private static final int SCALE=10;
	public static Snake snake;

	private JFrame jframe;

	private RenderPanel renderPanel;

	private Timer timer = new Timer(20, this);

	private Dimension dim;

	private int ticks, score, time;
	private boolean over = false, paused;

	private Point cherry;
	
	private Random random;

	public Frame() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		snake = new Snake();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(805, 700);
		jframe.setResizable(false);
		jframe.setLocation(getDim().width / 2 - jframe.getWidth() / 2, getDim().height / 2 - jframe.getHeight() / 2);
		jframe.add(renderPanel = new RenderPanel(this, snake));
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);		
		startGame();
	}

	private void startGame() {
		setOver(false);
		setPaused(false);
		setTime(0);
		setScore(0);
		random = new Random();		
		timer.start();
		setCherry(new Point(random.nextInt(79), random.nextInt(66)));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		renderPanel.repaint();
		ticks++;

		if (ticks % 2 == 0 && snake.getHead() != null && !isOver() && !isPaused()) {
			setTime(getTime() + 1);
			setOver(snake.act());

			if (getCherry() != null) {
				if (snake.getHead().equals(getCherry())) {
					setScore(getScore() + 10);
					snake.incTailLength();					
					getCherry().setLocation(random.nextInt(79), random.nextInt(66));
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		snake.keyPress(i);

		if (i == KeyEvent.VK_SPACE) {
			if (isOver()) {
				startGame();
			} else {
				setPaused(!isPaused());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	public static void main(String[] args) {
		new Frame();
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isOver() {
		return over;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Point getCherry() {
		return cherry;
	}

	public void setCherry(Point cherry) {
		this.cherry = cherry;
	}
	
	public static int getScale() {
		return SCALE;
	}
}
