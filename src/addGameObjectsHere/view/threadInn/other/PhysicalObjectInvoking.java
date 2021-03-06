package addGameObjectsHere.view.threadInn.other;

import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;

/**
 * @author Mia Beaudoin-Dion
 */
public abstract class PhysicalObjectInvoking<E> extends PhysicalObject {

    private GameEvent<E> actionToInvoke;
    private E objectToDoActionOn;

    public PhysicalObjectInvoking(BoundingArea boundingArea, boolean isObstacle, DisplayableDepth depth,
                                  GameEvent<E> actionToInvoke, E objectToDoActionOn) {
        super(boundingArea, isObstacle, depth);
        this.objectToDoActionOn = objectToDoActionOn;
        this.actionToInvoke = actionToInvoke;
    }

    public void invokeAction() {
        actionToInvoke.doAction(objectToDoActionOn);
    }

}
