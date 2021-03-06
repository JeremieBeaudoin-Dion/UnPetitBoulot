package addGameObjectsHere.view.threadInn.other;

import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;


/**
 * The way this game will handle collisions is by having the actual PhysicalObjects have
 * a BoundingArea representing their shadows.
 *
 * Their actual image will be an ObjectShadowImage which will follow them around for the player
 * to have the real representation of the adventurer.
 *
 * @author Mia Beaudoin-Dion
 */
public abstract class ObjectShadowImageMoving extends PhysicalObject {

    private PhysicalObject parentObject;
    private Position offset;

    /**
     * Constructor
     */
    public ObjectShadowImageMoving(PhysicalObject parentObject, Position size, Position offset, DisplayableDepth depth) {
        super(getBoundingArea(parentObject, size, offset), false, depth);

        this.parentObject = parentObject;
        this.offset = offset;
    }

    private static BoundingArea getBoundingArea(PhysicalObject parentObject, Position size, Position offset) {
        Position actualPosition = parentObject.getPosition().add(offset);

        return new BoundingArea(actualPosition.getX(), actualPosition.getY(), size.getX(), size.getY());
    }

    /**
     * Method called every frame
     */
    public void update() {
        Position newPosition = parentObject.getPosition().add(offset);

        this.setPositionTo(newPosition);
    }

    public PhysicalObject getParentObject() {
        return parentObject;
    }
}
