package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.adventurers.stats.CharacterStats;
import jGameFramework.core.ObjectWithID;

/**
 * A Unit is an in-game person who has statistics representing current health,
 * line, etc.
 *
 * Units can be friendly (ClientPhysicalObject) or not.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Unit extends ObjectWithID {

    private CharacterStats characterStats;

    /**
     * Constructors
     */
    public Unit(CharacterStats characterStats) {
        this.characterStats = characterStats;
    }

    public void startFight() {
        characterStats.startFight();
    }

    public boolean isDead() {
        return characterStats.isDead();
    }

    public CharacterStats getStats() {
        return characterStats;
    }

    @Override
    public int compareTo(ObjectWithID objectWithID) {
        return super.compareTo(objectWithID);
    }
}
