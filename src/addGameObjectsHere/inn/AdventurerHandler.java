package addGameObjectsHere.inn;

import addGameObjectsHere.characters.Adventurer;

/**
 * Adventurer handlers can receive adventurers
 *
 * An AdventurerHandleException is thrown if the adventurer cannot be added.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface AdventurerHandler {

    void AddAdventurer(Adventurer adventurer);

}
