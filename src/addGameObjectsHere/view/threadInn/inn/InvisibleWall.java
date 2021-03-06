package addGameObjectsHere.view.threadInn.inn;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * An object with the sole purpose to be a wall.
 *
 * @author Mia Beaudoin-Dion
 */
public class InvisibleWall extends PhysicalObject {

    /**
     * Constructor
     */
    public InvisibleWall(BoundingArea boundingArea) {
        super(boundingArea, true, new DisplayableDepth(DisplayableDepth.BACKGROUND));
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        return null;
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
}
