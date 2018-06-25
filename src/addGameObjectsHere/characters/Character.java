package addGameObjectsHere.characters;

import jGameFramework.coreActions.GameEvent;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;

import java.util.List;

/**
 * A character is a person who can embark on adventures. A character has stats attributed to it.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class Character extends PhysicalObject {

    private CharacterStat characterStat;
    private String name;

    /**
     * Constructors
     */
    public Character(CharacterStat characterStat) {
        super(getBasicArea(), false);

        this.characterStat = characterStat;
    }

    private static BoundingArea getBasicArea() {
        return new BoundingArea(0, 0, 50, 50);
    }

    /**
     * Returns the base stats of the character
     */
    protected CharacterStat getBaseStats() {
        return characterStat;
    }

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
}
