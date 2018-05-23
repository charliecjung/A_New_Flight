package cc.flappyBird;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager extends Thread {

		
		
		
		public void run() {
			try {
				
				Clip clip = AudioSystem.getClip();
				//Coordinator.class.getResource(picturePath + "pipe.png").toString();
				//URL url = Thread.currentThread().getContextClassLoader().getResource("background.wav");
				URL url = Coordinator.class.getResource(Coordinator.picturePath + "background.wav");
				AudioInputStream ais = AudioSystem.getAudioInputStream(url);
				clip.open(ais);
				clip.start();
				clip.loop(clip.LOOP_CONTINUOUSLY);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
	
	
	
	
	
}







