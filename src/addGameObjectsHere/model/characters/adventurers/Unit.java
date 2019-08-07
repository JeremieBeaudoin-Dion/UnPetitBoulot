package addGameObjectsHere.model.characters.adventurers;

import addGameObjectsHere.model.quests.fight.CharacterDamage;
import addGameObjectsHere.model.quests.fight.CharacterLine;
import addGameObjectsHere.model.quests.fight.CharacterStats;
import addGameObjectsHere.model.quests.fight.ImmutableCharacterStats;
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

    public void doDamage(CharacterDamage damage) {
        int amount = damage.getAmount();

        amount /= damage.getNumberOfAffectedCharacters();

        characterStats.doDamage(amount);
    }

    public CharacterDamage getDamage() {
        return characterStats.getDamage();
    }

    public CharacterLine getLine() {
        return characterStats.getLine();
    }

    public boolean isOut() {
        return characterStats.isOut() || characterStats.isDead();
    }

    public boolean isDead() {
        return characterStats.isDead();
    }

    public ImmutableCharacterStats getStats() {
        return characterStats;
    }

    @Override
    public int compareTo(ObjectWithID objectWithID) {
        return super.compareTo(objectWithID);
    }
}
