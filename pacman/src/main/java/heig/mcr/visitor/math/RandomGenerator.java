package heig.mcr.visitor.math;

import java.util.Random;

/**
 * A random generator, to be used as a singleton.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public final class RandomGenerator {
    private static Random instance;

    public static Random getInstance() {
        if (instance == null) {
            instance = new Random();
        }

        return instance;
    }
}
