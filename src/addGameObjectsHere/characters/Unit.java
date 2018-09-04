package addGameObjectsHere.characters;

import addGameObjectsHere.characters.fight.CharacterDamage;
import addGameObjectsHere.characters.fight.CharacterLine;
import addGameObjectsHere.characters.fight.CharacterStats;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;

import java.util.List;

/**
 * A character is a person who can embark on adventures. A character has stats attributed to it.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class Unit extends PhysicalObject {

    private CharacterStats characterStats;
    private String name;

    /**
     * Constructors
     */
    public Unit(CharacterStats characterStats) {
        super(getBasicArea(), false);

        this.characterStats = characterStats;
    }

    private static BoundingArea getBasicArea() {
        return new BoundingArea(0, 0, 50, 50);
    }

    public abstract String getName();

    /**
     * Characters do not return actions in general
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    /**
     * The characters will be disposed of in specific situation using different methods from basic objects.
     */
    @Override
    public boolean dispose() {
        return false;
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
}
