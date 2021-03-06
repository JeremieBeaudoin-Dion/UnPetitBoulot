package addGameObjectsHere.view.threadConversation;

import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;

public abstract class DecisionUI extends PhysicalObject{

    /**
     * Constructor
     */
    public DecisionUI(BoundingArea boundingArea, boolean isObstacle, DisplayableDepth depth) {
        super(boundingArea, isObstacle, depth);
    }

    public abstract Enum getCurrentAction();
    public abstract void next();
    public abstract void previous();

}
