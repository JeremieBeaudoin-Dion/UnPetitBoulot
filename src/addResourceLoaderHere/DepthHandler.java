package addResourceLoaderHere;

import jGameFramework.display.DisplayableDepth;

/**
 * An object which is used to create depths for all PhysicalObjects.
 *
 * This helps centralize things and ease the use of depths.
 *
 * @author Mia Beaudoin-Dion
 */
public class DepthHandler {

    public static DisplayableDepth BACKGROUND_DEPTH = new DisplayableDepth(DisplayableDepth.BACKGROUND);
    public static DisplayableDepth INN_OBJECT_DEPTH = new DisplayableDepth(100);
    public static DisplayableDepth ADVENTURER_DEPTH = new DisplayableDepth(200);
    public static DisplayableDepth ADVENTURER_SHADOW_DEPTH = new DisplayableDepth(210);
    public static DisplayableDepth UI_BASE_DEPTH = new DisplayableDepth(5000);

    public DepthHandler() {
    }


}
