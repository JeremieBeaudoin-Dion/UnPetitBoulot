package addGameObjectsHere.model.quests.challenges;

import addGameObjectsHere.model.characters.adventurers.Unit;

import java.util.List;

/**
 * A challenge is an encounter that the Party will have to overcome.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface Challenge {

    void doChallenge(List<Unit> adventurerUnits);

}
