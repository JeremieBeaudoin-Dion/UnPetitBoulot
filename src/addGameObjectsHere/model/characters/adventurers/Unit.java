package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.characters.adventurers.stats.CharacterStats;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Perk;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Trigger;
import jGameFramework.core.ObjectWithID;

/**
 * A Unit is an in-game person who has statistics representing current health,
 * line, etc.
 *
 * Units can be friendly (ClientPhysicalObject) or not.
 *
 * @author Mia Beaudoin-Dion
 */
public class Unit extends ObjectWithID {

    private CharacterStats characterStats;

    /**
     * Constructors
     */
    public Unit(CharacterStats characterStats) {
        this.characterStats = characterStats;
    }

    public void startQuest() {

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

    public void doTrigger(Trigger trigger) {
        for (Perk perk : this.characterStats.getAllPerks()) {
            perk.doTrigger(trigger);
        }
    }

    @Override
    public int compareTo(ObjectWithID objectWithID) {
        return super.compareTo(objectWithID);
    }
}
