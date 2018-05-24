package birdy;

public class Timer {
	
	public void pause(int duration) {
		try {
			Thread.currentThread().sleep(duration);
		}
		catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	public Timer(int _number) {
		
	}
	
	

}
