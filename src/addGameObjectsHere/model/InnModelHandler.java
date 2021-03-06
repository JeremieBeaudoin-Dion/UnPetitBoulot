package addGameObjectsHere.model;

import addGameObjectsHere.view.threadInn.inn.InnId;

/**
 * Handles the inn as a model object.
 *
 * This helps with getting the reputation, buying upgrades to the inn, etc.
 *
 * @author Mia Beaudoin-Dion
 */
public class InnModelHandler implements ImmutableInnModelHandler {

    public static final int BASE_COST_OF_DRINKS = 4;

    private int costOfDrinks;
    private int comfort;
    private InnId currentInnID;

    public InnModelHandler() {
        costOfDrinks = BASE_COST_OF_DRINKS;
        comfort = 10; // Temporary for testing
        currentInnID = InnId.floor1; // Temporary for testing
    }

    public int getNumberOfAdventurersToCreate() {
        return this.comfort; // This will change...
    }

    public int getCostOfDrinks() {
        return costOfDrinks;
    }

    public InnId getCurrentInnID() {
        return currentInnID;
    }
}
