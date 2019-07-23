package addGameObjectsHere.model.quests.fight;

import addGameObjectsHere.model.characters.adventurers.Unit;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Characters in a fight are ordered by initative, and do damage in that order.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InitiativeQueue {

    private Set<Unit> allCharacters;
    private Iterator<Unit> iterator;

    public InitiativeQueue(List<Unit> friendlyCharacters, List<Unit> enemyCharacters) {

        allCharacters = new TreeSet<>();

        allCharacters.addAll(friendlyCharacters);
        allCharacters.addAll(enemyCharacters);

        iterator = allCharacters.iterator();
    }

    public CharacterDamage getNext() {
        if (!iterator.hasNext()) {
            iterator = allCharacters.iterator();
        }

        Unit nextCharacter = iterator.next();

        if (nextCharacter.isOut()){
            return getNext();
        }

        return nextCharacter.getDamage();
    }

}
