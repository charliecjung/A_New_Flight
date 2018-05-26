package birdy;

import java.util.Vector;

public class CloudManager {
	private Vector<Clouds> clouds;

	public CloudManager(int numOfClouds) {
		clouds = new Vector<Clouds>(numOfClouds);
			for (int l = 0; l < numOfClouds; l++) {
				clouds.add(new Clouds());
			}
	}
	protected Vector<Clouds> getClouds() {
		return clouds;
	}
}
