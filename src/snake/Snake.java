package snake;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Snake {
	public JFrame jframe;
	public Toolkit toolkit;

	public Snake() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		toolkit = Toolkit.getDefaultToolkit();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(800, 700);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new Snake();
	}

}
