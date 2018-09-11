package addGameObjectsHere.gameButtons;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.GameThreadID;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventNew;
import jGameFramework.physicalObjects.BoundingArea;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DayButton extends GameButton {

    private static final int START_WIDTH = 192;
    private static final int START_HEIGHT = 108;

    /**
     * Constructor
     */
    public DayButton() {
        super(ButtonID.Day, new BoundingArea(GameInformation.WINDOW_WIDTH - 20 - START_WIDTH,
                GameInformation.WINDOW_HEIGHT - 20 - START_HEIGHT, START_WIDTH, START_HEIGHT));
    }

    @Override
    protected List<GameEvent> getMenuAction() {
        List<GameEvent> listOfEvents = new LinkedList<>();

        listOfEvents.add(new GameThreadEventNew(GameThreadID.Night));

        return listOfEvents;
    }
}
