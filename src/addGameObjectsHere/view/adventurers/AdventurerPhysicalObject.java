package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.model.characters.Adventurer;
import addGameObjectsHere.view.innObjects.ServingTablePhysicalObject;
import jGameFramework.collections.SetHelper;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.PhysicalObject;

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

    /**
     * Constructor
     */
    public AdventurerPhysicalObject(int x, int y, DisplayableDepth depth, Adventurer adventurer) {
        super(x, y, depth, getAdventurerImageID(adventurer));

        currentAction = CurrentAction.findingASeat;
    }

    private static AdventurerImageID getAdventurerImageID(Adventurer adventurer) {
        return AdventurerImageID.getID(adventurer.getAdventurerClass());
    }

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        TreeSet<PhysicalObject> updateObjects = new TreeSet<>();
        SetHelper.addAllIfNotNull(updateObjects, super.update(surroundings));

        switch (currentAction) {
            case idle:
                return updateObjects;

            case findingASeat:
                System.out.println("didn't find a seat..." + getPosition());
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
}
