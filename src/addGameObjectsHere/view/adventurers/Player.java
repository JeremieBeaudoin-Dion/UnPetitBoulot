package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.view.gameObjects.AdventurerObjectMovingWithShadow;
import jGameFramework.display.DisplayableDepth;

/**
 * The player requires its own PhysicalObject in order to be able to recieve
 * the moving actions when pressing keys. (See ActionLoader)
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Player extends AdventurerObjectMovingWithShadow {

    /**
     * Constructor
     */
    public Player(int x, int y, DisplayableDepth depth) {
        super(x, y, depth, PlayerImageID.InnKeeperMan);
    }
}
