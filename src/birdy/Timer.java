package birdy;

public class Timer {

	public void pause(int duration) {
		try {
			
			Thread.currentThread();
			Thread.sleep(duration);
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public Timer() {

	}

}
