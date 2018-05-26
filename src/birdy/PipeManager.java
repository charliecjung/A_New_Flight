package birdy;

import java.util.Vector;

public class PipeManager {
	private Vector<Pipes> pipes;

	private void createPipes(int numOfPipes) {
		pipes = new Vector<Pipes>(numOfPipes);
		for (int l = 0; l < 21; l++) {
			pipes.add(new Pipes());
		}
	}

}
