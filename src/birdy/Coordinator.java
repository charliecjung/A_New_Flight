package birdy;

import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
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
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SCREEN_WIDTH = (int)screenSize.getWidth();
	public static final int SCREEN_HEIGHT = (int)screenSize.getHeight();
	public static boolean GAME_OVER = false;
	public static String picturePath = "/picturesAndSounds/";
	public static final String backgroundMusic = Coordinator.class.getResource(picturePath + "background.wav").toString();
	public static final DrawingBoard board = new DrawingBoard(SCREEN_WIDTH, SCREEN_HEIGHT);
	public static int currentPos = 0;


	public static void main(String[] args) {
		SoundManager sm = new SoundManager();
		sm.start();
		
		

		while (true) {
			if (GAME_OVER == true) {
				System.out.println("Game Over...");
				break;
			}
			board.paintComponent(Coordinator.board.getGraphics());
			board.repaint();
		}

	}
}
