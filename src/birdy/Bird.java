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

public class Bird implements KeyListener {

	private static int x, y;
	private Image birdImage = new ImageIcon(getClass().getResource(Coordinator.picturePath + "bird.png")).getImage();
	private double acceleration, velocity, gravity, force = 0;
	private static int birdHeight, birdWidth;
	private int dy;
	private int bx, by;
	private long currentTime = 0;
	private long lastRecordedTime = 0;

	public void draw(Graphics g) {
		g.drawImage(birdImage, x, y, birdWidth + 22, birdHeight + 13, Coordinator.board);
	}

	public void setVelocity(int _force) {
		force = _force;
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

	public void fall() {

		if (currentTime == TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())) {

		} else if (currentTime != TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())) {
			currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		}
		currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		int dt = (int) ((currentTime - lastRecordedTime));
		if (currentTime != lastRecordedTime) {

		}

		if (acceleration >= gravity) {

			acceleration = gravity;

			// velocity calculates how far it was from original position

			velocity = velocity + (acceleration * dt);
			dy = (int) (velocity * dt);
			y = y + dy;

		}

	}

	public Bird(int _x, int _y) {

		this.x = _x;
		this.y = _y;

		birdWidth = 75 - 22;
		birdHeight = 75 - 13;
		lastRecordedTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

		
		gravity = 0.001;
		
		// Distance of how much of the bird is going into the air(half of its body)
		
		double deltaDistance = 100;
		
		// How long is airtime(1 second)
		
		double deltaTime = 1000;
		
		// acceleration becomes the same as gravity
		
		acceleration = gravity;
		force = (deltaDistance - gravity * deltaTime * deltaTime / 2.0) / deltaTime;

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			velocity = force;
		}

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
