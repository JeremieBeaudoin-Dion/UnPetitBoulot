package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.view.gameObjects.LoadingBar;
import addGameObjectsHere.view.innObjects.ServingTablePhysicalObject;
import jGameFramework.collections.SetHelper;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The player requires its own PhysicalObject in order to be able to receive
 * the moving actions when pressing keys. (See ActionLoader)
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Player extends AdventurerObjectMovingWithShadow {

    private static final int TIME_IN_MILLIS_TO_INTERACT = 1000;

    private static final Position LOADING_BAR_OFFSET = new Position(-35, 20);

    private boolean interact;

    private LoadingBar currentLoadingBar;
    private ServingTablePhysicalObject currentServingTable;

    /**
     * Constructor
     */
    public Player(int x, int y, DisplayableDepth depth) {
        super(x, y, depth, PlayerImageID.InnKeeperMan);

        interact = false;
    }

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {

        TreeSet<PhysicalObject> updateObjects = new TreeSet<>();
        SetHelper.addAllIfNotNull(updateObjects, super.update(surroundings));

        if (interact) {

            if (isLoading()) {
                checkLoading();

            } else {
                SetHelper.addAllIfNotNull(updateObjects,
                        handleClosestObject(getClosestObject(trimSurroundings(surroundings))));
            }

        } else {
            if (currentLoadingBar != null) {
                deleteLoadingBar();
            }
        }

        return updateObjects;
    }

    private boolean isLoading() {
        return currentLoadingBar != null;
    }

    private void checkLoading() {
        if (this.isMoving()) {
            // Player cannot move and interact at the same time
            deleteLoadingBar();

        } else {
            if (currentLoadingBar.isDone()) {
                serveAdventurers();
                deleteLoadingBar();
            }
        }
    }

    private TreeSet<PhysicalObject> trimSurroundings(TreeSet<PhysicalObject> surroundings) {
        TreeSet<PhysicalObject> interactiveSurroundings = new TreeSet<>();

        for (PhysicalObject object : surroundings) {
            if (object instanceof ServingTablePhysicalObject) {
                interactiveSurroundings.add(object);

            } else if (object instanceof AdventurerPhysicalObject){
                interactiveSurroundings.add(object);
            }
        }

        return interactiveSurroundings;
    }

    private Set<PhysicalObject> handleClosestObject(PhysicalObject closestObject) {

        System.out.println(closestObject);

        if (closestObject instanceof ServingTablePhysicalObject) {
            return setTableForLoading((ServingTablePhysicalObject) closestObject);
        }

        return null;
    }

    private Set<PhysicalObject> setTableForLoading(ServingTablePhysicalObject table) {

        Set<PhysicalObject> objectsToAdd = new TreeSet<>();

        currentServingTable = table;
        currentLoadingBar = getLoadingBar();
        objectsToAdd.add(currentLoadingBar);

        return objectsToAdd;
    }

    private LoadingBar getLoadingBar() {
        return new LoadingBar(this.getPosition().add(LOADING_BAR_OFFSET), this.getDepth().add(1),
                TIME_IN_MILLIS_TO_INTERACT);
    }

    private void deleteLoadingBar() {
        currentLoadingBar.interrupt();
        currentLoadingBar = null;
        interact = false;
    }

    private void serveAdventurers() {
        for (AdventurerPhysicalObject adventurer : currentServingTable.getSeatedAdventurers()) {
            if (!adventurer.isServed()) {
                adventurer.giveDrink();
                // Todo: remove alcohol from tray
            }
        }
    }

    /**
     * Public method to toggle interact with other objects
     */
    public void setInteract() {
        interact = !interact;
    }

}
