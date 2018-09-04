package addGameObjectsHere.characters.fight;

/**
 * When displaying characters, they must show their stats.
 *
 * This interface assures that they will not be changed while being displayed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface DisplayableCharacterStats {

    DisplayableCharacterHealth getHealth();

    CharacterLine getLine();



}
