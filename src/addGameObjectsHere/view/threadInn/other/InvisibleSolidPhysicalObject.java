package addGameObjectsHere.view.threadInn.other;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * Physical object that does not have an image representation.
 *
 * Can be used for an invisible wall, for example.
 *
 * @author Mia Beaudoin-Dion
 */
public class InvisibleSolidPhysicalObject extends PhysicalObject {

    private static final boolean VISIBLE = false; // Set this to true for testing purposes

    /**
     * Constructor
     */
    public InvisibleSolidPhysicalObject(BoundingArea boundingArea) {
        super(boundingArea, true, new DisplayableDepth(DisplayableDepth.BACKGROUND));
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        if (!VISIBLE) {
            return null;
        }

        TreeSet<Displayable> image = new TreeSet<>();

        image.add(new DisplayableShapeFilled(this, cameraPosition, new Color(255, 50, 255)));

        return image;
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
