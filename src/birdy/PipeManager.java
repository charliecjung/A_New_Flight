package birdy;

import java.util.Vector;

public class PipeManager {
	private Vector<Pipes> pipes;

	public PipeManager(int numOfPipes) {
		pipes = new Vector<Pipes>(numOfPipes);
		for (int l = 0; l < numOfPipes; l++) {
			pipes.add(new Pipes());
		}
	}
	
	protected Vector<Pipes> getPipes() {
		return pipes;
	}
}
