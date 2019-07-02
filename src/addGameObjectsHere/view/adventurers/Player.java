package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.view.gameObjects.LoadingBar;
import addGameObjectsHere.view.innObjects.ServingTablePhysicalObject;
import jGameFramework.collections.SetHelper;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

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
            // Get closest thing
            PhysicalObject closestObject = getClosestObject(surroundings);

            // interact with the thing
            if (closestObject instanceof ServingTablePhysicalObject) {

            }

            if (currentLoadingBar == null) {
                currentLoadingBar = new LoadingBar(this.getPosition().add(LOADING_BAR_OFFSET),
                        this.getDepth().add(1), TIME_IN_MILLIS_TO_INTERACT);
                updateObjects.add(currentLoadingBar);
            } else {
                if (this.isMoving()) {
                    // Player cannot move and interact at the same time
                    deleteLoadingBar();

                } else {
                    if (currentLoadingBar.isDone()) {
                        // TODO: give mead to adventurers
                        deleteLoadingBar();
                    }
                }
            }
        }

        return updateObjects;
    }

    private void addAllIfNotNull(TreeSet<PhysicalObject> desiredSet, TreeSet<PhysicalObject> setToAdd){
        if (setToAdd != null){
            desiredSet.addAll(setToAdd);
        }
    }

    public void setInteract() {
        interact = !interact;

        if (!interact) {
            deleteLoadingBar();
        }
    }

    private void deleteLoadingBar() {
        currentLoadingBar.interrupt();
        currentLoadingBar = null;
        interact = false;
    }

}
