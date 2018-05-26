package birdy;

import java.util.Vector;

public class CloudManager {
	private Vector<Clouds> clouds;

	private void createClouds(int numOfClouds) {
		clouds = new Vector<Clouds>(numOfClouds);
			for (int l = 0; l < 21; l++) {
				clouds.add(new Clouds());
			}
	}
}
