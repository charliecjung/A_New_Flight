package birdy;

import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Coordinator extends Canvas {

	public static final int SCREEN_WIDTH = 900;
	public static final int SCREEN_HEIGHT = 900;
	public static final int startingCoordinate = 0;
	public static boolean GAME_OVER = false;
	public static boolean ifPaused = false;
	private static int sizeOfPipeVector = 20;
	public static String picturePath = "/picturesAndSounds/";
	//public static Image backgroundImage = null;
	public final static int firstCoordinate = startingCoordinate;
	public final static int secondCoordinate = SCREEN_WIDTH;
	public final static int thirdCoordinate = SCREEN_HEIGHT;
	public final static int fourthCoordinate = SCREEN_WIDTH + SCREEN_HEIGHT;
	private static Vector<Clouds> clouds = new Vector<Clouds>(20);
	private static Vector<Pipes> pipes = new Vector<Pipes>(20);
	public static final Image cloud = new ImageIcon(Coordinator.class.getResource(picturePath + "cloud.png"))
			.getImage();
	public static final Image pipe = new ImageIcon(Coordinator.class.getResource(picturePath + "pipe.png")).getImage();

	private static int officialTracker;
	private static int tempX = 0;
	private static int screenWidth;
	public static int screenX;
	private static int numOfTiles = 0;
	private static int dx = 0;
	private static float t = 10;
	public static int currentPos;
	private static int position;
	public static boolean didPictureGetChanged = false;
	private static boolean isFlag = true;
	private static Pipes temp;
	private static boolean flaggy;
	public static int chooser = 1;
	public static boolean flipImage = false;
	public static boolean didAnimationPlay = true;
	public static double realTime = System.currentTimeMillis();
	public static double totalTime = 0;
	private static int multiplier;
	public static final String backgroundMusic = Coordinator.class.getResource(picturePath + "background.wav")
			.toString();
	private static Graphics Drawer;
	private static Graphics2D bIG;
	private static BufferedImage bI;
	private static JFrame frame;
	private static String privatePath;
	private static DrawingBoard board;

	public void setJFrame(JFrame _jframe) {
		frame = _jframe;
	}

	public void ifBirdCollided() {

	}

	public int getScreenX() {
		return screenX;
	}

	public Image getPipe() {
		return pipe;
	}

	public Image getCloud() {
		return cloud;
	}

	public void setMultiplier(int _multiplier) {
		multiplier = _multiplier;
	}

	public void setBI(BufferedImage _bI) {
		bI = _bI;
	}

	public Coordinator() {

	}

	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) (img);
		}

		BufferedImage tempImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D tempImage2D = tempImage.createGraphics();
		tempImage2D.drawImage(img, 0, 0, null);
		tempImage2D.dispose();
		return tempImage;
	}

	public static void main(String[] args) {

		//backgroundImage = new ImageIcon(Coordinator.class.getResource(picturePath + "background.png")).getImage();
		Coordinator c = new Coordinator();
		board = new DrawingBoard(SCREEN_WIDTH, SCREEN_HEIGHT);
		board.setDrawingBoard(board);
		bI = board.getBI();
		bIG = (Graphics2D) bI.getGraphics();
		board.setBIG(bIG);

		Vector<Image> BackgroundManager = new Vector<Image>(1);
		Timer timer = new Timer(1);
		Bird bird = new Bird(100, 100);
		board.addListener(bird);
		bird.setDrawingBoard(board);
		bird.setCanvas(bIG);
		Clouds cloudManager = new Clouds();

		cloudManager.setCanvas(bIG);
		cloudManager.setCloudManager(clouds);
		cloudManager.setDrawingBoard(board);
		cloudManager.setCloud(cloud);
		Pipes pipeManager = new Pipes();

		pipeManager.setCanvas(bIG);
		pipeManager.setPipeManager(pipes);
		pipeManager.setDrawingBoard(board);
		pipeManager.setPipe(pipe);
		bird.setPiped(pipeManager);
		SoundManager sm = new SoundManager();
		sm.start();
		dx = (int) Math.ceil((double) ((t / 1000f) * t) * 10);
		if (pipes.isEmpty()) {
			for (int j = 0; j < 60; j++) {
				pipes.add(j, new Pipes());
			}
		}

		if (clouds.isEmpty()) {
			for (int l = 0; l < 21; l++) {
				clouds.add(new Clouds());
			}

		}
		// Logic formed by mathbits :D
		while (isFlag) {
			isFlag = false;

			for (int i = 0; i < pipes.size() - 1; i++) {
				if (pipes.get(i).getX() > pipes.get(i + 1).getX()) {
					temp = pipes.get(i + 1);
					pipes.setElementAt(pipes.get(i), i + 1);
					pipes.setElementAt(temp, i);
					isFlag = true;
				}
			}
		}

		for (int j = 0; j < pipes.size(); j++) {
			Pipes x = pipes.get(j);
			
		}

		Pipes last_upper = null, last_lower = null;
		for (int j = 0; j < pipes.size(); j++) {

			if (flaggy == true) {
				j = j - 1;
				flaggy = false;

			}

			Pipes x = pipes.get(j);
			Pipes y = null;

			if (x.getY() == 0) {
				y = last_upper;
				last_upper = x;
			} else {
				y = last_lower;
				last_lower = x;
			}

			if (y != null) {
				if (y.getX() + y.getWidth() < x.getX()) {
					; // no overlap
				} else {
					// They are overlapping!!!

					x.setX(y.getX() + y.getWidth());
		
				}
			}

		}

		for (int i = 0; i < pipes.size(); i++) {
			bird.setPipes(pipes.get(i));
		}

		while (true) {

			// Pipe collision logic

		
			for (int i = 0; i < pipes.size() - 2; i++) {
				if (pipes.get(i).getX() + (pipes.get(i).getWidth() * 4) > pipes.get(i + 1).getX()) {

					int tempX = pipes.get(i + 1).getX();
					int tempY = pipes.get(i + 1).getY();
					pipes.remove(i + 1);
					pipes.add(i + 1, new Pipes(tempX, tempY));
				}
			}

		

			if (GAME_OVER == true) {
				System.out.println("Game Over...");
				break;
			}

			for (int i = 0; i < pipes.size(); i++) {

				bird.ifHit(i);
			}

			bird.didBirdCollide();
			if (bird.isScreenToggleOn == true) {

			} else if (bird.isScreenToggleOn == false) {
				bird.fall(bIG);
			}
			bird.changePictureIfNeeded(didPictureGetChanged, bIG);

			if (bird.isScreenToggleOn == true) {

			} else if (bird.isScreenToggleOn == false) {

				screenX = ((screenX + dx) + (multiplier));

				if (screenX < 0) {
					screenX = Math.abs(screenX);
				} else if (screenX > 13000) {
					screenX = 100;
				}

			}
			/*
			for (Coordinator.currentPos = -(int) screenX
					% Coordinator.SCREEN_WIDTH; Coordinator.currentPos < Coordinator.SCREEN_WIDTH; Coordinator.currentPos += Coordinator.SCREEN_WIDTH) {
				// gets run here
				
				bIG.drawImage(backgroundImage, Coordinator.currentPos, 0, Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT, board);
			
			}
			*/
			
			//c.update(bIG);
			
			/*
			for (int i = 0; i < pipes.size(); i++) {

				pipeManager.draw(pipes.get(i).getX() - ((int) screenX), pipes.get(i).getY(), pipes.get(i).getWidth(),
						pipes.get(i).getHeight(), bIG, i);
				

			}
			

			for (int i = 0; i < 4; i++) {

				cloudManager.draw(clouds.get(i).getX() - ((int) (screenX)), clouds.get(i).getY(), bIG);
				
			}
			*/
			board.paintComponent(bIG);

			double endTime = System.currentTimeMillis();
			totalTime = ((endTime - Coordinator.realTime) / 1000) / 2;
			//bird.draw(bIG);
			if (bird.isPortalToggled == true) {
				if (bird.getY() + bird.getHeight() + 13 > Coordinator.SCREEN_HEIGHT) {
					bird.setY(Coordinator.SCREEN_HEIGHT - 800);
				}

				bird.drawPortal(bIG);
			} else if (bird.isPortalToggled == false) {

			}
			Drawer = bIG.create();
			board.setDrawer(Drawer);
			board.setPrimaryDisplay(bI);
			board.repaint();
			timer.pause(10);
		}

	}
}
