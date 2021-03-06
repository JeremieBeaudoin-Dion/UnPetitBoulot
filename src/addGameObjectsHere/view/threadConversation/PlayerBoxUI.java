package addGameObjectsHere.view.threadConversation;

import addResourceLoaderHere.ModelObjectLoader;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;

public class PlayerBoxUI extends ImageBoxUI {

    /**
     * Constructor
     */
    public PlayerBoxUI(BoundingArea boundingArea, boolean isObstacle, DisplayableDepth depth,
                       ModelObjectLoader modelObjectLoader) {
        super(boundingArea, isObstacle, depth, modelObjectLoader.getPlayerHandler().getImageID());
    }
}
