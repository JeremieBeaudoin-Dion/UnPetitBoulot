package addGameObjectsHere.model;

import addGameObjectsHere.view.threadInn.characters.PlayerImageID;

/**
 * A PlayerModelHandler that cannot be changed.
 *
 * @author Mia Beaudoin-Dion
 */
public interface ImmutablePlayerModelHandler {

    PlayerImageID getImageID();

}
