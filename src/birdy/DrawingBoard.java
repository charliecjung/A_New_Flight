package birdy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingBoard extends JPanel {
	private JFrame frame;
	private JPanel panel;
	private int w, h;
	private String GAME_TITLE = "Flappy Bird";
	public static BufferedImage bI;
	private static Image birdyImage;
	private static Graphics Drawer;
	private static BufferedImage finalImage;
	private static DrawingBoard board;
	private Graphics2D bIG;
	public static Image backgroundImage = new ImageIcon(Coordinator.class.getResource(Coordinator.picturePath + "background.png")).getImage();
	private int screenX = 0;
	private int multiplier = 10;
	private int dx = 10;
	private Image bird = new ImageIcon(getClass().getResource(Coordinator.picturePath + "bird.png")).getImage();

	public DrawingBoard(int _w, int _h) {
		bI = new BufferedImage(Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		finalImage = new BufferedImage(Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Coordinator c = new Coordinator();
		w = _w;
		h = _h;
		frame = new JFrame();
		panel = new JPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(w, h);

		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setDrawingBoard(DrawingBoard _board) {
		board = _board;
	}

	public BufferedImage getBI() {
		return bI;
	}

	public void setBIG(Graphics2D _bIG) {
		bIG = _bIG;
	}

	public void paint(Graphics g) {

		g.drawImage(finalImage, 0, 0, board);

	}

	public void setBI(BufferedImage _bI) {
		bI = _bI;
	}

	public void setDrawer(Graphics _Drawer) {
		Drawer = _Drawer;
	}

	public void clear(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);

	}

	public void setPrimaryDisplay(BufferedImage _bI) {
		finalImage = _bI;
	}

	public void addListener(Bird bird) {
		frame.addKeyListener(bird);
		frame.addMouseListener(bird);
		frame.addMouseMotionListener(bird);
		frame.setTitle(this.GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board);
	}
	protected void paintComponent(Graphics g) {
		Timer gameTimer = new Timer(1);
		Bird birdObj = new Bird(20, 20);
		Color temp = g.getColor();
		g.setColor(Color.BLUE);
		g.drawLine(0,  0, 200, 200);
		g.setColor(temp);

		//screenX = ((screenX + dx) + (multiplier));
		screenX = screenX + 90;
		
		if (screenX < 0) {
			screenX = Math.abs(screenX);
		} else if (screenX > (13000/2)) {
			screenX = 100;
		} else if (screenX == 0) {
			screenX = screenX + 20;
		}
		System.out.println("screenx: " + screenX);
		for (Coordinator.currentPos = - (int)screenX; Coordinator.currentPos < Coordinator.SCREEN_WIDTH; Coordinator.currentPos += (Coordinator.SCREEN_WIDTH)) {
			// gets run here
			gameTimer.pause(100);
			bIG.drawImage(backgroundImage, Coordinator.currentPos, 0, Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT, board);
		
		}
		
		g.drawImage(bird, birdObj.getX(), birdObj.getY(), birdObj.getWidth() + 22, birdObj.getHeight() + 13, board);
		
		
	
	}
}
