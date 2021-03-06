package addGameObjectsHere.model.characters.generator;

import java.util.Random;

/**
 * Helper class to generate random numbers;
 */
public class RandomGenerator {

    private Random random;

    public RandomGenerator() {
        random = new Random();
    }

    /**
     * Gets a random integer between two bounds.
     */
    protected int getRndInt(int lowerBound, int highBound) {
        int difference = highBound - lowerBound;

        return random.nextInt(difference) + lowerBound;
    }

}
