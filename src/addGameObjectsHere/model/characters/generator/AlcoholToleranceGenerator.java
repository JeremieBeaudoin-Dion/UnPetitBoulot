package addGameObjectsHere.model.characters.generator;

/**
 * Generates tolerance of alcohol of the adventurers.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AlcoholToleranceGenerator {

    private int testTolerance;

    public AlcoholToleranceGenerator() {
        testTolerance = 3;
    }

    int getTolerance() {
        return testTolerance;
    }

}
