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
	public static Image backgroundImage = new ImageIcon(
			Coordinator.class.getResource(Coordinator.picturePath + "background.png")).getImage();
	private int screenX = 0;
	private int multiplier = 10;
	private int dx = 10;
	private Image birdImage = new ImageIcon(getClass().getResource(Coordinator.picturePath + "bird.png")).getImage();
	private Timer gameTimer = new Timer();
	private static Graphics gameDrawer;
	private Bird bird;
	CloudManager cloudManager;
	PipeManager pipeManager;

	public DrawingBoard(int _w, int _h) {
		finalImage = new BufferedImage(Coordinator.SCREEN_WIDTH, Coordinator.SCREEN_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		gameDrawer = finalImage.getGraphics();
		cloudManager = new CloudManager(4);
		pipeManager = new PipeManager(5);
		bird = new Bird(100, 100);
		
		
		w = _w;
		h = _h;
		frame = new JFrame();
		panel = new JPanel();
		this.addListener(bird);
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
		frame.setTitle(this.GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected void paintComponent(Graphics g) {
		updateScreenPosition();

		for (Coordinator.currentPos = -(int) screenX; Coordinator.currentPos < Coordinator.SCREEN_WIDTH; Coordinator.currentPos += (Coordinator.SCREEN_WIDTH)) {
			
			gameDrawer.drawImage(backgroundImage, Coordinator.currentPos, 0, Coordinator.SCREEN_WIDTH,
					Coordinator.SCREEN_HEIGHT, Coordinator.board);
			
		}
		
		//g.drawImage(birdImage, bird.getX(), bird.getY(), bird.getWidth() + 22, bird.getHeight() + 13,
		//		Coordinator.board);
		bird.draw(g);
		bird.fall();
		g.setColor(Color.RED);

		g.drawLine(0, 0, bird.getX(), bird.getY());
		drawClouds(g);
		drawPipes(g);
		gameTimer.pause(30);
		Coordinator.board.repaint();
		
	

	}
	
	private void updateScreenPosition() {
		screenX = ((screenX + dx)+(multiplier));

		if (screenX < 0) {
			screenX = Math.abs(screenX);
		} else if (screenX > (13000)) {
			screenX = 100;
		} else if (screenX == 0) {
			screenX += 10;
		}
	}

	private void drawPipes(Graphics g) {
		
		
	
		for (int i = 0; i < pipeManager.getPipes().size(); i++) {
			/*
			Color originalColor = g.getColor();
			g.setColor(Color.BLUE);
			g.drawLine(0, 0, pipeManager.getPipes().get(i).getX()-((int)screenX), pipeManager.getPipes().get(i).getY());
			g.setColor(Color.BLACK);
			g.drawString("Position " + i, pipeManager.getPipes().get(i).getX()-((int)screenX), pipeManager.getPipes().get(i).getY());
			g.setColor(originalColor);
			System.out.println("Pipe X: " + (pipeManager.getPipes().get(i).getX()-((int)screenX)));
			System.out.println("Pipe Y: " + pipeManager.getPipes().get(i).getY());
			*/
			
			pipeManager.getPipes().get(i).draw(pipeManager.getPipes().get(i).getX()-((int)screenX), pipeManager.getPipes().get(i).getY(), pipeManager.getPipes().get(i).getWidth(), pipeManager.getPipes().get(i).getHeight(), gameDrawer);
		
			
		}
		
		
	}
	private void drawClouds(Graphics g) {
		for (int i = 0; i < cloudManager.getClouds().size(); i++) {
			
			cloudManager.getClouds().get(i).draw(cloudManager.getClouds().get(i).getX()-((int)(screenX)), cloudManager.getClouds().get(i).getY(), gameDrawer);
		}
	}


}
