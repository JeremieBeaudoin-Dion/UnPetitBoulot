package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.model.characters.Adventurer;
import addGameObjectsHere.view.gameObjects.AdventurerObjectMovingWithShadow;
import jGameFramework.display.DisplayableDepth;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerPhysicalObject extends AdventurerObjectMovingWithShadow {

    /**
     * Constructor
     */
    public AdventurerPhysicalObject(int x, int y, DisplayableDepth depth, Adventurer adventurer) {
        super(x, y, depth, getAdventurerImageID(adventurer));
    }

    private static AdventurerImageID getAdventurerImageID(Adventurer adventurer) {
        return null;
    }
}
