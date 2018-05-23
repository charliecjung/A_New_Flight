package cc.flappyBird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Clouds {
	private Image cloud;
	private String picturePath = Coordinator.picturePath;
	private DrawingBoard board;
	private Graphics2D bIG;
	private int tracker = 100;;
	private Vector <Image> clouds = new Vector <Image>(20);
	private int x, y;
	private int w = 90;
	private int h = 90;
	private Bird bird;

	
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
		//Mission Success:
		
		x = Coordinator.SCREEN_WIDTH + (int)((Math.random()+0.1)*800);
		y = (Coordinator.startingCoordinate+50) + (int)((Math.random()+0.1)*350);
		
		
	}
	public void draw(int _x, int _y, Graphics g) {
		if(Bird.BatManMode == true) {
			cloud = new ImageIcon(Coordinator.class.getResource(picturePath + "turtle.png")).getImage();
			g.drawImage(cloud, _x, _y, this.w, this.h, this.board);
		}else if(Bird.BatManMode == false) {
			cloud = new ImageIcon(Coordinator.class.getResource(picturePath + "cloud.png")).getImage();
			g.drawImage(cloud, _x, _y, this.w, this.h, this.board);
		}
		
		
		
	}
	public void setTracker(int _tracker) {
		tracker = _tracker;
	}
	public void setCanvas(Graphics2D _bIG) {
		bIG= _bIG;
	}
	public void setCloud(Image _cloud) {
		cloud = _cloud;
	}
	public void setPicturePath(String _picturePath) {
		picturePath = _picturePath;
	}
	public void setCloudManager(Vector _clouds) {
		clouds = _clouds;
	}
	public void setDrawingBoard(DrawingBoard _board) {
		board = _board;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
