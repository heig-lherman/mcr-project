package heig.mcr.visitor.math;

import java.util.Random;

public final class RandomGenerator {
	private static Random instance;
	
	public static Random getInstance() {
		if(instance == null)
			instance = new Random();
		return instance;
	}
}
