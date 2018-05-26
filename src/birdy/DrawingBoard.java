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
	private static BufferedImage finalImage;
	public static Image backgroundImage = new ImageIcon(Coordinator.class.getResource(Coordinator.picturePath + "background.png")).getImage();
	private int screenX = 0;
	private int multiplier = 10;
	private int dx = 10;
	private Image bird = new ImageIcon(getClass().getResource(Coordinator.picturePath + "bird.png")).getImage();
	private Timer gameTimer = new Timer();
	private Graphics gameDrawer;
	
	public DrawingBoard(int _w, int _h) {
		finalImage = new BufferedImage(Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Coordinator c = new Coordinator();
		gameDrawer = finalImage.getGraphics();
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
	public void paint(Graphics g) {
		g.drawImage(finalImage, 0, 0, Coordinator.board);

	}
	public void clear(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);

	}

	public void addListener(Bird bird) {
		frame.addKeyListener(bird);
		frame.addMouseListener(bird);
		frame.addMouseMotionListener(bird);
		frame.setTitle(this.GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(Coordinator.board);
	}
	protected void paintComponent(Graphics g) {
		Bird birdObj = new Bird(20, 20);

	
		screenX = screenX + 90;
		
		if (screenX < 0) {
			screenX = Math.abs(screenX);
		} else if (screenX > (13000/2)) {
			screenX = 100;
		} else if (screenX == 0) {
			screenX = screenX + 20;
		}
	
		for (Coordinator.currentPos = - (int)screenX; Coordinator.currentPos < Coordinator.SCREEN_WIDTH; Coordinator.currentPos += (Coordinator.SCREEN_WIDTH)) {
			// gets run here
			
			gameDrawer.drawImage(backgroundImage, Coordinator.currentPos, 0, Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT, Coordinator.board);
		
		}
		gameTimer.pause(30);
		g.drawImage(bird, birdObj.getX(), birdObj.getY(), birdObj.getWidth() + 22, birdObj.getHeight() + 13, Coordinator.board);
		
		
	
	}
	
	private Graphics getGameDrawer() {
		return gameDrawer;
	}
	/*
	 * 	screenX = ((screenX + dx) + (multiplier));

				if (screenX < 0) {
					screenX = Math.abs(screenX);
				} else if (screenX > 13000) {
					screenX = 100;
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
	
	//^^ should be handled somewhere in DrawingBoard; not Coordinator();
	 
}
