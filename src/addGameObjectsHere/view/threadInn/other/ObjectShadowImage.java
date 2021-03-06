package addGameObjectsHere.view.threadInn.other;

import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.*;


/**
 * The way this game will handle collisions is by having the actual PhysicalObjects have
 * a BoundingArea representing their shadows.
 *
 * Their actual image will be an ObjectShadowImage which will follow them around for the player
 * to have the real representation of the adventurer.
 *
 * @author Mia Beaudoin-Dion
 */
public abstract class ObjectShadowImage extends PhysicalObject {

    /**
     * Constructor
     */
    public ObjectShadowImage(PhysicalObject parentObject, Position size, Position offset,  DisplayableDepth depth) {
        super(getBoundingArea(parentObject, size, offset), false, depth);
    }

    private static BoundingArea getBoundingArea(PhysicalObject parentObject, Position size, Position offset) {
        Position actualPosition = parentObject.getPosition().add(offset);

        return new BoundingArea(actualPosition.getX(), actualPosition.getY(), size.getX(), size.getY());
    }

}
