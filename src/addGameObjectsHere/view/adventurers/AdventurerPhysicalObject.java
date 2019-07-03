package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.model.characters.Adventurer;
import addGameObjectsHere.view.images.IconImageID;
import addGameObjectsHere.view.innObjects.ServingTablePhysicalObject;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.collections.SetHelper;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.TreeSet;

/**
 * Adventurers roaming around in the inn. They have a MODEL object which represent
 * their name, price, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerPhysicalObject extends AdventurerObjectMovingWithShadow {

    private enum CurrentAction {
        findingASeat, idle
    }

    private CurrentAction currentAction;

    private boolean isServed;

    /**
     * Constructor
     */
    public AdventurerPhysicalObject(int x, int y, DisplayableDepth depth, Adventurer adventurer) {
        super(x, y, depth, getAdventurerImageID(adventurer));

        currentAction = CurrentAction.findingASeat;
        isServed = false;
    }

    private static AdventurerImageID getAdventurerImageID(Adventurer adventurer) {
        return AdventurerImageID.getID(adventurer.getAdventurerClass());
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setToReturn = new TreeSet<>();

        setToReturn.addAll(super.getImageObjects(cameraPosition, imageLoader));

        if (isServed) {
            setToReturn.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition).add(
                    new Position(getWidth(), -getHeight())), getDepth().add(1), imageLoader.getImageFromID(IconImageID.beer)));
        }

        return setToReturn;
    }

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        TreeSet<PhysicalObject> updateObjects = new TreeSet<>();
        SetHelper.addAllIfNotNull(updateObjects, super.update(surroundings));

        switch (currentAction) {
            case idle:
                return updateObjects;

            case findingASeat:
                //System.out.println("didn't find a seat..." + getPosition());
                findASeat(surroundings);
        }

        return updateObjects;
    }

    private void findASeat(TreeSet<PhysicalObject> surroundings) {
        for (PhysicalObject obj: surroundings) {
            if (obj instanceof ServingTablePhysicalObject) {
                ((ServingTablePhysicalObject) obj).sit(this);
                currentAction = CurrentAction.idle;
                return;
            }
        }

        // Move around and find a seat -> Todo: have an adventurer helper to get next direction to table.
    }

    public boolean isServed() {
        return isServed;
    }

    public void giveDrink() {
        isServed = true;

        //TODO: Add drink to adventurer...
    }

    public void resetDrink() {
        isServed = false;
    }
}
