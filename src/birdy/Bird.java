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

		if (this.ifCheatMenuIsOpen == true) {
			Color original = g.getColor();
			Color tempColor = new Color(0, 0, 153);
			g.setColor(tempColor);
			g.drawString("Flappy Bird v0.0.4: Early DEVELOPMENTAL BUILD: Distributed to beta testers", 0, 20);
			g.drawString("Use at your own risk... Many bugs!!!", 0, 40);
			g.drawString("Report Bugs at: <email coming soon>", 0, 60);
			g.drawString("Cheat Codes: In Early Beta", 0, 80);
			g.drawString("Press M to close/open menu", 0, 100);
			g.drawString("Press F to float", 0, 120);
			g.drawString("(1,2,3,4) Current Bird Picture Path: " + birdPath, 0, 140);
			g.drawString("Press 6 to stop time: " + this.isScreenToggleOn, 0, 160);
			g.drawString("Press O for superspeed: ", 0, 180);
			g.drawString("Press G for godmode: ", 0, 200);
			g.drawString("Press R for reverse: ", 0, 220);

			g.setColor(Color.RED);
			g.drawLine(0, 0, x, y);

			g.setColor(original);
		}

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

	public void changePictureIfNeeded(Boolean _didBirdPictureGetChanged, Graphics g) {

		if (_didBirdPictureGetChanged == true) {

			bird = new ImageIcon(birdPath).getImage();
			g.drawImage(bird, x, y, birdWidth, birdHeight, board);
			_didBirdPictureGetChanged = false;

		}

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

	public void PauseResetPosition(int _y, int _y2) {
		int tempY = _y2 - _y;
		int finalTempY = y - tempY;
		this.setY(finalTempY);
	}

	public void ifHit(int k) {
		screenX = coordinator.getScreenX();
		coordinator.setMultiplier(multiplier);

		if (godMode == false) {

			int temporaryY = pipes.get(k).getY();

		}
	}

	public void didBirdCollide() {
		if (ifCollidedWithObstacle == true) {
			Coordinator.GAME_OVER = true;
			ifCollidedWithObstacle = false;

		}
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			v = f;

		} else if (e.getKeyCode() == KeyEvent.VK_0) {

			ifCollidedWithObstacle = true;

		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			v = 0.1;
			System.out.println("Floating...");
		} else if (e.getKeyCode() == KeyEvent.VK_1) {

			birdPath = Coordinator.class.getResource(picturePath + "bird.png");
			Coordinator.didPictureGetChanged = true;
		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			birdPath = Coordinator.class.getResource(picturePath + "nightbird.png");
			Coordinator.didPictureGetChanged = true;
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			birdPath = Coordinator.class.getResource(picturePath + "backupbird.png");
			Coordinator.didPictureGetChanged = true;
		} else if (e.getKeyCode() == KeyEvent.VK_4) {
			birdPath = Coordinator.class.getResource(picturePath + "backupclouds.png");
			Coordinator.didPictureGetChanged = true;
		} else if (e.getKeyCode() == KeyEvent.VK_5) {
			birdPath = Coordinator.class.getResource(picturePath + "pipe.png");
			Coordinator.didPictureGetChanged = true;
		} else if (e.getKeyCode() == KeyEvent.VK_6) {
			birdPath = Coordinator.class.getResource(picturePath + "portal.png");
			Coordinator.didPictureGetChanged = true;
		}

		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (this.isScreenToggleOn == false) {
				this.isScreenToggleOn = true;
				by = this.y;
			} else if (this.isScreenToggleOn == true) {
				this.PauseResetPosition(by, y);

				this.isScreenToggleOn = false;

				bx = 0;
				by = 0;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_M) {
			if (this.ifCheatMenuIsOpen == false) {
				this.ifCheatMenuIsOpen = true;
			} else if (this.ifCheatMenuIsOpen == true) {
				this.ifCheatMenuIsOpen = false;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_O) {
			if (multiplier == 25) {
				multiplier = 1;
			} else if (multiplier == 1) {
				multiplier = 25;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_P) {
			if (godMode == true) {
				birdPath = Coordinator.class.getResource(picturePath + "bird.png");
				Coordinator.didPictureGetChanged = true;
				godMode = false;
			} else if (godMode == false) {
				birdPath = Coordinator.class.getResource(picturePath + "godmodebird.png");
				Coordinator.didPictureGetChanged = true;
				godMode = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_R) {

			if (x < -1000) {
				x = 900;
			}
			if (multiplier == -2) {
				multiplier = 1;
			} else if (multiplier == 1) {
				multiplier = -2;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			if (isPortalToggled == true) {

				isPortalToggled = false;
				bird = birdImageTemp;
				Coordinator.didPictureGetChanged = true;
			} else if (isPortalToggled == false) {

				birdImageTemp = this.getBird();

				isPortalToggled = true;
			}

		} else if (e.getKeyCode() == KeyEvent.VK_B) {
			if (BatManMode == false) {
				DrawingBoard.backgroundImage = new ImageIcon(
						Coordinator.class.getResource(picturePath + "batmanBackground.jpg")).getImage();
				birdPath = Coordinator.class.getResource(picturePath + "nightbird.png");
				Coordinator.didPictureGetChanged = true;
				BatManMode = true;
				godMode = true;
			} else if (BatManMode == true) {
				birdPath = Coordinator.class.getResource(picturePath + "bird.png");
				DrawingBoard.backgroundImage = new ImageIcon(
						Coordinator.class.getResource(picturePath + "background.png")).getImage();
				Coordinator.didPictureGetChanged = true;
				BatManMode = false;
				godMode = false;

			}
		} else if (e.getKeyCode() == KeyEvent.VK_8) {
			screenX = 0;
		}

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void run() {

	}

}
