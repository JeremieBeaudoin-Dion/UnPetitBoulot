package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.model.characters.Adventurer;
import jGameFramework.display.DisplayableDepth;

/**
 * Adventurers roaming around in the inn. They have a MODEL object which represent
 * their name, price, etc.
 *
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
        return AdventurerImageID.getID(adventurer.getAdventurerClass());
    }
}
