package addGameObjectsHere.model.quests.fight;

/**
 * When displaying adventurers, they must show their stats.
 *
 * This interface assures that they will not be changed while being displayed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface ImmutableCharacterStats {

    ImmutableCharacterHealth getHealth();

    CharacterLine getLine();



}
