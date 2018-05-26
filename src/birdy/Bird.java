package birdy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class Bird implements KeyListener, MouseListener, MouseMotionListener, Runnable {

	private static int x, y;
	private Image birdImage = new ImageIcon(getClass().getResource(Coordinator.picturePath + "bird.png")).getImage();
	private double a, b, v, g, f, j = 0;
	private static int birdHeight, birdWidth;
	private int dy;
	private int bx, by;
	private long currentTime;
	private long lastRecordedTime;
	
	public void draw(Graphics g) {
		g.drawImage(birdImage, x, y, birdWidth + 22, birdHeight + 13, Coordinator.board);
	}

	public void setVelocity(int _f) {
		f = _f;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return birdHeight;
	}

	public int getWidth() {
		return birdWidth;
	}

	public void setX(int _x) {
		x = _x;
	}

	public void setY(int _y) {
		y = _y;
	}

	public Image getBirdImage() {
		return birdImage;
	}
	public void fall(Graphics _g) {

		currentTime = System.currentTimeMillis();


		int dt = (int) (currentTime - lastRecordedTime);

		if (a > g)

			a = g;
		// velocity calculates how far it was from original position

		v = v + (a * dt);
		dy = (int) (v * dt);

		y = y + dy;
		lastRecordedTime = System.currentTimeMillis();
	}



	public Bird(int _x, int _y) {

		this.x = _x;
		this.y = _y;
		
		birdWidth = 75 - 22;
		birdHeight = 75 - 13;

		// gravity
		g = 0.001;
		// Distance of how much going in air(half of its body)
		double deltaDistance = 100;
		// How long is airtime(1 second)
		double deltaTime = 1000;
		// acceleration becomes the same as gravity
		a = g;

		f = (deltaDistance - g * deltaTime * deltaTime / 2.0) / deltaTime;

		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
