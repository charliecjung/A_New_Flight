package birdy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Clouds {
	private Image cloudImage;
	private int x, y;
	private int w = 90;
	private int h = 90;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public Clouds() {

		x = Coordinator.SCREEN_WIDTH + (int) ((Math.random() + 0.1) * 800);
		y = 50 + (int) ((Math.random() + 0.1) * 350);

	}

	public void draw(Graphics g) {
			cloudImage = new ImageIcon(Coordinator.class.getResource(Coordinator.picturePath + "cloud.png")).getImage();
			g.drawImage(cloudImage, x, y, w, h, Coordinator.board);
		

	}


}
