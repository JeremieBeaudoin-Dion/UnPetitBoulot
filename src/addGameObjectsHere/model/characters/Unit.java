package addGameObjectsHere.model.characters;

import addGameObjectsHere.model.characters.fight.CharacterDamage;
import addGameObjectsHere.model.characters.fight.CharacterLine;
import addGameObjectsHere.model.characters.fight.CharacterStats;
import addGameObjectsHere.model.characters.fight.ImmutableCharacterStats;
import jGameFramework.core.ObjectWithID;

/**
 * A Unit is an in-game person who has statistics representing current health,
 * line, etc.
 *
 * Units can be friendly (Adventurer) or not.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class Unit extends ObjectWithID {

    private CharacterStats characterStats;

    /**
     * Constructors
     */
    public Unit(CharacterStats characterStats) {
        this.characterStats = characterStats;
    }

    public abstract String getName();

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

        if (objectWithID instanceof Unit) {
            if (!getName().equals(((Unit) objectWithID).getName())) {
                return getName().compareTo(((Unit) objectWithID).getName());
            }
        }

        return super.compareTo(objectWithID);
    }
}
