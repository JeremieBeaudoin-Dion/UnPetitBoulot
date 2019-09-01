package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.ClientID;

/**
 * Generates tolerance of alcohol of the adventurers.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AlcoholToleranceGenerator {

    private static final int BASE_ALCOHOL_TOLERANCE = 3;

    int getTolerance(ClientID clientID) {
        switch (clientID) {
            case Warrior:
                return 5;
            case Rogue:
                return 3;
            case Ranger:
                return 3;
            case Man:
                return 2;
            case OldWoman:
                return 4;
        }

        return BASE_ALCOHOL_TOLERANCE;
    }

}
