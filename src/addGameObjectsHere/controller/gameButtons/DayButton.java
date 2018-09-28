package addGameObjectsHere.controller.gameButtons;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventAddNew;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DayButton extends IconGameButton {

    private static final int START_WIDTH = 192;
    private static final int START_HEIGHT = 108;

    private List<DayInteractingObject> objectsToUpdate;

    /**
     * Constructor
     */
    public DayButton(List<DayInteractingObject> dayInteractingObjects) {
        super(ButtonID.Day, new BoundingArea(GameInformation.WINDOW_WIDTH - 20 - START_WIDTH,
                GameInformation.WINDOW_HEIGHT - 20 - START_HEIGHT, START_WIDTH, START_HEIGHT), getMenuAction(),
                new DisplayableDepth(DisplayableDepth.HIGHEST));

        objectsToUpdate = dayInteractingObjects;
    }

    private static List<GameEvent> getMenuAction() {
        List<GameEvent> listOfEvents = new LinkedList<>();

        listOfEvents.add(new GameThreadEventAddNew(GameThreadID.Night));

        return listOfEvents;
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        for (DayInteractingObject objectToUpdate: objectsToUpdate) {
            objectToUpdate.isNight();
        }

        super.doLeftMouseReleased(mousePositionCollidingWithObject);
    }
}
