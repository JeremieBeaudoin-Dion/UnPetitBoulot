package addGameObjectsHere.model.quests.challenges;

import addGameObjectsHere.model.characters.adventurers.Unit;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * A fight is a type of challenge in which the adventurers will need to
 * overcome enemies.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Fight implements Challenge {

    /**
     * Constructor
     */
    public Fight() {
    }

    @Override
    public void doChallenge(List<Unit> adventurerUnits) {
        throw new NotImplementedException();
    }
}
