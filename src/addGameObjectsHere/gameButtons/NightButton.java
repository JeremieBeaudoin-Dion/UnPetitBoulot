package addGameObjectsHere.gameButtons;

import addResourceLoaderHere.GameInformation;
import jGameFramework.physicalObjects.BoundingArea;

/**
 * The night button is a Quit button which has a specific Bounding Area.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class NightButton extends GameButtonQuit {

    private static final int START_WIDTH = 192;
    private static final int START_HEIGHT = 108;

    public NightButton() {
        super(ButtonID.Night, new BoundingArea(GameInformation.WINDOW_WIDTH - 20 - START_WIDTH,
                GameInformation.WINDOW_HEIGHT - 20 - START_HEIGHT, START_WIDTH, START_HEIGHT));
    }
}
