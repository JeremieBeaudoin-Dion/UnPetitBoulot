package addResourceLoaderHere;

import jGameFramework.display.DisplayableDepth;

/**
 * An object which is used to create depths for all PhysicalObjects.
 *
 * This helps centralize things and ease the use of depths.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DepthHandler {

    public static DisplayableDepth BACKGROUND_DEPTH = new DisplayableDepth(DisplayableDepth.BACKGROUND);
    public static DisplayableDepth ADVENTURER_DEPTH = new DisplayableDepth(200);
    public static DisplayableDepth PLAYER_DEPTH = new DisplayableDepth(500);

    private int numberOfAdventurersCreated;

    public DepthHandler() {
        numberOfAdventurersCreated = 0;
    }

    public DisplayableDepth getAdventurerDepth() {
        //numberOfAdventurersCreated++;
        return PLAYER_DEPTH;
    }


}
