package addGameObjectsHere.model.quests.challenges;

import addGameObjectsHere.model.characters.adventurers.Unit;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * A trap will do a certain amount of damage to the party
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Trap implements Challenge {

    public Trap() {
    }

    @Override
    public void doChallenge(List<Unit> adventurerUnits) {
        throw new NotImplementedException();
    }
}
