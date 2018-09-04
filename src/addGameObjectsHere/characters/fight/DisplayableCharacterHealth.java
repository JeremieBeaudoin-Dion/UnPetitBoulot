package addGameObjectsHere.characters.fight;

/**
 * When displaying characters, they must show their health.
 *
 * This interface assures that they will not be changed while being displayed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface DisplayableCharacterHealth {

    int getMaxHp();

    int getCurrentHP();

}
