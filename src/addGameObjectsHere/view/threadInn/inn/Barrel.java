package addGameObjectsHere.view.threadInn.inn;

import addGameObjectsHere.view.threadInn.characters.ClientPhysicalObject;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * The barrel fills the beer of the player as well as resets the drinks of all adventurers.
 *
 * @author Mia Beaudoin-Dion
 */
public class Barrel extends PhysicalObjectUpdating {

    private static final boolean TESTING = false;

    private boolean doReset;

    /**
     * Constructor
     */
    public Barrel(BoundingArea area) {
        super(area, true, new VisionAll(), new DisplayableDepth(DisplayableDepth.HIGHEST));
        doReset = false;
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {

        if (doReset) {
            resetAllAdventurers(surroundings);
            doReset = false;
        }

        return null;
    }

    private void resetAllAdventurers(TreeSet<PhysicalObject> surroundings) {
        for (PhysicalObject obj : surroundings) {
            if (obj instanceof ClientPhysicalObject) {
                ((ClientPhysicalObject) obj).resetDrink();
            }
        }
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        if (!TESTING) {
            return null;
        }

        TreeSet<Displayable> setToReturn = new TreeSet<>();

        setToReturn.add(new DisplayableShapeFilled(this, cameraPosition, Color.RED));

        return setToReturn;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }

    public void fill() {
        doReset = true;
    }
}
