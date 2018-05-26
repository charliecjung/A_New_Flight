package birdy;

import java.awt.Color;
import java.awt.CompositeContext;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Pipes {
	private Image pipeImage;
	private int x, y, w, h, thickness;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}
	public int getThickness()
	{
		return thickness;
	}
	public void draw(int _x, int _y, int _w, int _h, Graphics g) {
		pipeImage = new ImageIcon(Coordinator.class.getResource(Coordinator.picturePath + "pipe.png")).getImage();
		g.drawImage(pipeImage, _x, _y, _w, _h, Coordinator.board);

	}

	public Pipes() {

		x = Coordinator.SCREEN_WIDTH + (int) ((Math.random()) * 5000);
		y = (int) (Math.random() * 2);

		w = 120;
		h = Coordinator.SCREEN_HEIGHT - 220;
		if (y == 0) {
			y = 0;
			
		} else if (y == 1) {
			y = Coordinator.SCREEN_HEIGHT - (this.h);
			

		}
	}

	public Pipes(int _x, int _y) {
		x = (_x + (int) ((Math.random()) * 500)) + (int) ((Math.random()) * 400);
		y = _y;

		w = 120;
		h = Coordinator.SCREEN_HEIGHT - 220;
	}
}
