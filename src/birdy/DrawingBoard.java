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
	//Make a drawingBoard
	private JFrame frame;
	//private Graphics2D canvas;
	private int w, h;
	private String GAME_TITLE = "Flappy Bird";
	public static BufferedImage bI;
	private static Image birdyImage;
	private static Graphics Drawer;
	private static BufferedImage finalImage;
	private static DrawingBoard board;
	private Graphics2D bIG;
	
	
	//Bi has everything
	
	public DrawingBoard(int _w, int _h ) {
		bI = new BufferedImage(Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		finalImage = new BufferedImage(Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Coordinator c = new Coordinator();
		//Bird bird = new Bird(1, 1);
		//birdyImage = bird.getBird();
		
		w = _w;
		h = _h;
		frame = new JFrame();
		
		frame.setSize(w, h);
		frame.setVisible(true);
		
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
	/*
	public void setBIG(Graphics2D _canvas) {
		Drawer = _canvas;
	}
	public Graphics2D getCanvas() {
		return bIG;
	}
	*/
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
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set launcher icon below
		//frame.setIconImage(temp);
	}

}
