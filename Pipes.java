package birdy;

import java.awt.Color;
import java.awt.CompositeContext;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Pipes {
	private String picturePath = Coordinator.picturePath;
	private DrawingBoard board;
	private Graphics2D bIG;
	private Vector<Pipes> pipes = new Vector<Pipes>(20);
	private Image pipe;
	private int tempScreenX;
	private int x, y, w, h, thickness;
	private static BufferedImage pipeBuffered;
	public static boolean isPipeDeleted;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
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

	public void draw(int _x, int _y, int _w, int _h, Graphics g, int k) {
		g.drawImage(pipe, _x, _y, _w, _h, board);
	}

	public Pipes() {

		x = Coordinator.SCREEN_WIDTH + (int) ((Math.random()) * 40000);
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
		if (y == 0) {
			y = 0;
		} else if (y == 1) {
			y = Coordinator.SCREEN_HEIGHT - (this.h);

		}
	}

	public void setCanvas(Graphics2D _canvas) {
		bIG = _canvas;
	}

	public void setPipe(Image _pipe) {
		pipe = _pipe;
	}

	public void setPicturePath(String _picturePath) {
		picturePath = _picturePath;
	}

	public void setPipeManager(Vector _pipes) {
		pipes = _pipes;
	}

	public void setDrawingBoard(DrawingBoard _board) {
		board = _board;
	}
}
