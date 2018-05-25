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
	private boolean isDead = false;
	private int coinCounter = 0;
	public static String picturePath = Coordinator.picturePath;
	public static URL birdPath = Coordinator.class.getResource(picturePath + "bird.png");
	private Image bird = new ImageIcon(getClass().getResource(picturePath + "bird.png")).getImage();
	private DrawingBoard board;
	private double a, b, v, g, f;
	private static long currentTime;
	private static int birdHeight, birdWidth;
	private static boolean isPaused = false;
	private static boolean ifCollidedWithObstacle = false;
	private Pipes piped;
	private Clouds cloud;
	public static Vector<Pipes> pipes = new Vector<Pipes>(20);
	public static int j = 0;
	public int topOfBird;
	public int bottomOfBird;
	public int middleOfBird = x + (birdWidth / 2);
	public static int timeCounter = 0;
	private Timer timer = new Timer(4);
	private static int topOfPipe;
	private boolean isInHit = true;
	private static float pipeX;
	private static long millisecond;
	private static int screenX;
	private boolean drawOnlyOnce = true;
	public static boolean isScreenToggleOn = false;
	private static boolean ifCheatMenuIsOpen = false;
	private static int multiplier = 1;
	private static boolean godMode;
	private static Image portal;
	public static boolean isPortalToggled;
	public static boolean BatManMode;
	private static Graphics2D bIG;
	private Coordinator coordinator = new Coordinator();
	private static boolean NeverEndingSkinChanger;
	private int director = 0;
	private static String[] skins = new String[5];
	private int imageWidth;
	public static Image birdImageTemp;
	private int dy;
	private int bx, by;
	private long ct;
	private double temp;

	public static void setPicturePath(String _picturePath) {
		picturePath = _picturePath;
	}

	public void draw(Graphics g) {
		g.drawImage(bird, x, y, birdWidth + 22, birdHeight + 13, board);
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

	public void setPipes(Pipes _pipe) {

		pipes.add(_pipe);

	}

	public void setPiped(Pipes _pipe) {
		piped = _pipe;
	}

	public Image getBird() {
		return bird;
	}

	public void drawPortal(Graphics g) {
		g.drawImage(portal, 100, 0, 100, 100, board);

	}

	public void pauseResetPosition(int _y, int _y2) {
		int tempY = _y2 - _y;
		int finalTempY = y - tempY;
		this.setY(finalTempY);
	}
	public void fall(Graphics _g) {

		if (ct == System.currentTimeMillis()) {

		} else if (ct != System.currentTimeMillis()) {
			ct = System.currentTimeMillis();

		}

		int dt = (int) (ct - currentTime);
		currentTime = ct;
		a = g;

		if (a > g)

			a = g;
		// velocity calculates how far it was from original position

		v = v + a * dt;
		dy = (int) (v * dt);

		y = y + dy;

	}

	public void setDrawingBoard(DrawingBoard _board) {
		board = _board;
	}

	public void setCanvas(Graphics2D _canvas) {
		bIG = _canvas;
	}

	public Bird(int _x1, int _y1) {

		skins[0] = Coordinator.class.getResource(picturePath + "bird.png").toString();
		x = _x1;
		y = _y1;
		bottomOfBird = x + birdWidth + birdHeight;

		topOfBird = x + birdWidth;
		currentTime = System.currentTimeMillis();
		a = 0;
		b = 0;
		v = 0;
		birdWidth = 75 - 22;
		birdHeight = 75 - 13;

		// gravity
		g = 0.001;
		// Distance of how much going in air(half of its body)
		double dd = 100;
		// How long is airtime(1 second)
		double dt = 1000;
		// acceleration becomes the same as gravity
		a = g;

		f = (dd - g * dt * dt / 2.0) / dt;

		
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void run() {

	}

}
