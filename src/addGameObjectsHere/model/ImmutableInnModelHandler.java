package addGameObjectsHere.model;

import addGameObjectsHere.view.threadInn.inn.InnId;

/**
 * An InnModelHandler that cannot be modified.
 *
 * @author Mia Beaudoin-Dion
 */
public interface ImmutableInnModelHandler {

    int getCostOfDrinks();

    InnId getCurrentInnID();

}
