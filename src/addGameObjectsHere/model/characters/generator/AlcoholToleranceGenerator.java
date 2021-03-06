package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.ClientID;

/**
 * Generates tolerance of alcohol of the adventurers.
 *
 * @author Mia Beaudoin-Dion
 */
public class AlcoholToleranceGenerator extends RandomGenerator {

    private static final int BASE_ALCOHOL_TOLERANCE = 3;

    int getTolerance(ClientID clientID) {
        return getRndInt(2, 6);
    }

}
